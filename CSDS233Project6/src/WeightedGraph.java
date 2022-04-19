import java.io.*;
import java.util.*;

/**
 * The WeightedGraph class works for both weighted and directed graphs. The weight applies a cost to the transversal
 * from one vertex to another vertex
 *
 * @author ari
 */
public class WeightedGraph extends Graph
{
	/**
	 * Adds an weighted edge from node from to
	 * node to. Note that for simplicity, we will only consider integer weights.
	 *
	 * @return true if successful, false otherwise
	 */
	public boolean addWeightedEdge(String from, String to, int weight)
	{
		// Get Vertex to add to
		Vertex start = getVertex(from);
		Vertex destination = getVertex(to);

		// Return false if either vertex does not exist does not exist
		if (start == null || destination == null)
			return false;

		// Add edge in Vertex Class
		return start.addWeightedEdge(destination, weight);
	}

	/**
	 * Adds a weighted edge from node from to each node in toList with a weight from the weightList
	 *
	 * @param from       starting node
	 * @param toList     destination node
	 * @param weightList weight associated with transversal
	 * @return true if successful, false otherwise
	 */
	public boolean addWeightedEdges(String from, String[] toList, int[] weightList)
	{
		boolean successful = true;

		for (int i = 0; i < toList.length; i++)
			successful = addWeightedEdge(from, toList[i], weightList[i]) && successful;

		return successful;
	}

	/**
	 * Prints the graph as follows:
	 * <nodename1> <weight> <neighbor1> <weight> <neighbor2> ...
	 * <nodename2> <weight> <neighbor1> <weight> <neighbor2> ...
	 * ...
	 */
	public void printWeightedGraph()
	{
		printGraph();
	}

	/**
	 * Reads and constructs a Weighted Graph with the following format:
	 * <nodename1> <weight> <neighbor1> <weight> <neighbor2> ...
	 * <nodename2> <weight> <neighbor1> <weight> <neighbor2> ...
	 * ...
	 *
	 * @param filename file path to document to read
	 * @return constructed weighted graph
	 */
	public static WeightedGraph readWeightedGraph(String filename)
	{
		// Create Scanner to read file
		Scanner scanner;
		try
		{
			scanner = new Scanner(new FileInputStream(filename));
		} catch (FileNotFoundException e)
		{
			scanner = new Scanner(filename);
			e.printStackTrace();
		}
		// Create list of lines
		ArrayList<String> lines = new ArrayList<>();

		// Add everything from file into lines ArrayList
		while (scanner.hasNextLine())
			lines.add(scanner.nextLine());

		// Create graph
		WeightedGraph graph = new WeightedGraph();

		// Create list of vertices
		String[][] vertices = new String[lines.size()][];

		// Separate vertices and weights
		for (int i = 0; i < lines.size(); i++)
			vertices[i] = lines.get(i).split(" ");

		// Add vertices to Weighted Graph
		for (String[] strArray : vertices)
			graph.addNode(strArray[0]);

		// Add Edges
		for (String[] strArray : vertices)
			for (int i = 2; i < strArray.length; i += 2)
				graph.addWeightedEdge(strArray[0], strArray[i], Integer.parseInt(strArray[i - 1]));

		// return the newly created Weighted Graph
		return graph;
	}

	/**
	 * Uses Dijkstra’s algorithm to find the shortest path from node from to node to. If there are multiple paths of
	 * equivalent length, you only need to return one of them. If the path does not exist, return an empty array.
	 *
	 * @param from starting point
	 * @param to   end point
	 * @return shortest path if it exists, otherwise return an empty array
	 */
	public String[] shortestPath(String from, String to)
	{
		// Get first and last vertices
		Vertex start = getVertex(from);
		Vertex end = getVertex(to);

		// Make sure that the first and last nodes actually exists
		if (getVertex(from) == null || getVertex(to) == null)
			return new String[0];
		if (from.equals(to))
			return new String[] {from};

		// Mark vertices as unvisited and set distance from start as max integer value
		setVisitedFalse();

		// Set start as visited and distance as 0
		start.distanceFromStart = 0;

		// Create Min Heap of vertices that is sorted by distance from start
		PriorityQueue<Vertex> vertices = new PriorityQueue<>(Vertex::compareByDistance);
		// Add the starting vertex into the heap
		vertices.add(start);

		// Remove the vertex that has the shortest distance to get to from the starting point in the heap. Set the
		// distances to its possible paths. Repeat this process until there are no more vertices to be reaching
		while (!vertices.isEmpty())
		{
			// Remove the smallest distance to start from the heap
			Vertex current = vertices.remove();

			// Iterate through all possible paths of current, calculate their weight, and if their weight is less than
			// the weight of the prior path to them, set the new path
			for (Vertex next : current.getChildren())
			{
				// Get distance
				int newDistance = current.getWeight(next);
				// If the new distance to get to the next vertex is less than the current distance, change the path
				if (newDistance < next.distanceFromStart)
				{
					// Add vertex to heap after setting its distance
					next.distanceFromStart = newDistance;
					vertices.add(next);
					// If there is not yet a path to the node
					if (current.path == null)
					{
						current.path = new String[] {from};
						next.path = new String[] {from};
					}
					// If the path to the node already exists
					else
					{
						next.path = new String[current.path.length + 1];
						System.arraycopy(current.path, 0, next.path, 0, current.path.length);
						next.path[next.path.length - 1] = next.toString();
					}
				}
			}
		}

		// Return the path to get to the end. If it doesn't exist, return an empty array
		if (end.path == null)
			return new String[0];
		return end.path;
	}

	//TODO: Maybe remove this
	private int calculatePathWeight(String[] path)
	{
		int weight = 0;

		for (int i = 0; i < path.length - 1; i++)
			weight += getVertex(path[i]).getWeightedEdge(getVertex(path[i + 1])).weight;

		return weight;
	}

	/**
	 * @param from starting point
	 * @param to   end point
	 * @return the second-shortest path between nodes from and to. Only returns one path in the case of multiple
	 * equivalent results
	 */
	//TODO: Write method
	public String[] secondShortestPath(String from, String to)
	{
		String[] shortestPath = shortestPath(from, to);
		String[] secondShortestPath = BFS(from, to, "alphabetical");

		return shortestPath;
	}

	/**
	 * Demo
	 *
	 * @param args arguments
	 */
	public static void main(String[] args)
	{
		WeightedGraph g = WeightedGraph.readWeightedGraph("weightedgraph.txt");
		g.printWeightedGraph();
		System.out.println();

		String[] shortestPath = g.shortestPath("A", "F");
		System.out.println(Arrays.toString(shortestPath));
		System.out.println(g.calculatePathWeight(shortestPath));
		String[] secondShortestPath = g.secondShortestPath("A", "F");
		System.out.println(Arrays.toString(secondShortestPath));
		System.out.println(g.calculatePathWeight(secondShortestPath));

	}
}
