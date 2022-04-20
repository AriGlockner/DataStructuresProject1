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

		ArrayList<Vertex> encountered = new ArrayList<>();


		// Remove the vertex that has the shortest distance to get to from the starting point in the heap. Set the
		// distances to its possible paths. Repeat this process until there are no more vertices to be reaching
		while (!vertices.isEmpty())
		{
			// Remove the smallest distance to start from the heap
			Vertex current = vertices.remove();

			// Only check to add vertices / look for vertices if they aren't already searched for
			if (!encountered.contains(current))
			{
				encountered.add(current);

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
							next.path = new String[] {from, next.toString()};
						}
						// If the path to the node already exists
						else
							next.path = combineArrays(current.path, new String[] {next.toString()});
					}
				}
			}
		}

		// Return the path to get to the end. If it doesn't exist, return an empty array
		if (end.path == null)
			return new String[0];
		return end.path;
	}

	//TODO:
	// Figure out error in shortestPath
	// Maybe remove this
	private int calculatePathWeight(String[] path)
	{
		int weight = 0;

		for (int i = 0; i < path.length - 1; i++)
		{
			/*
			System.out.println(getVertex(path[i]) + " " + getVertex(path[i + 1]));
			System.out.println(getVertex(path[i]).getEdges());
			System.out.println(getVertex(path[i]).getWeightedEdge(getVertex(path[i + 1])));
			 */
			weight += getVertex(path[i]).getWeightedEdge(getVertex(path[i + 1])).weight;
		}

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
		final String[] shortestPath = shortestPath(from, to);

		if (Arrays.equals(shortestPath, new String[0]))
			return new String[0];

		String[] secondShortestPath = new String[0];
		int secondShortestWeight = 0;

		//
		/*
		for (int i = 0; i < shortestPath.length; i++)
		{
			// Current
			Vertex current = getVertex(shortestPath[i]);
			if (current.toString().equals(to))
				break;
			String[] currentPath = shortestPath(from, current.toString());
			System.out.println(i + 1 + " " + Arrays.toString(shortestPath(from, current.toString())) + " " + Arrays.toString(secondShortestPath));

			String[] successfulPath = new String[0];
			int successfulPathWeight = 0;

			for (Vertex next : current.getChildren())
			{
				// Make sure vertex isn't the next vertex in the shortest path
				if (!next.toString().equals(shortestPath[i + 1]))
				{
					// Calculate the new possible path
					String[] possiblePath = shortestPath(next.toString(), to);
					System.out.println(Arrays.toString(currentPath) + " " + Arrays.toString(possiblePath));

					// Make sure possible path is successful
					if (possiblePath.length > 0 && !Arrays.equals(possiblePath, shortestPath))
					{
						//System.out.println(current + " " + next + " " + Arrays.toString(possiblePath));
						int possibleWeight = calculatePathWeight(possiblePath);
						// Set as successful path if it's shorter than the current successful path

						if (Arrays.equals(successfulPath, new String[0]) || successfulPathWeight > possibleWeight)
						{
							successfulPath = possiblePath;
							successfulPathWeight = possibleWeight;
						}
					}
				}
			}

			//System.out.println(Arrays.toString(successfulPath) + " " + successfulPathWeight);
			successfulPath = combineArrays(current.path, successfulPath);

			successfulPathWeight = calculatePathWeight(shortestPath);

			//
			if (Arrays.equals(secondShortestPath, new String[0]) || secondShortestWeight > successfulPathWeight)
			{
				secondShortestPath = successfulPath;
				secondShortestWeight = successfulPathWeight;
			}
		}

		*/

		System.out.println("Testing: " + from + " " + to + " " + Arrays.toString(shortestPath));
		for (int i = 0; i < shortestPath.length - 1; i++)
		{
			// Current vertex in the shortest path
			Vertex current = getVertex(shortestPath[i]);
			String[] currentPath = Arrays.copyOf(shortestPath, i + 1);
			//System.out.println(current + " " + Arrays.toString(currentPath));

			for (Vertex next : current.getChildren())
			{
				// Check if next vertex is not the next vertex in the shortest path
				if (!next.toString().equals(shortestPath[i + 1]))
				{
					String[] possiblePath = shortestPath(next.toString(), to);
					//System.out.println("Test: " + Arrays.toString(possiblePath));

					//TODO: Everything works above this. Check below this

					// Make sure possiblePath is a succesful path
					if (possiblePath.length > 0)
					{
						//System.out.println(Arrays.toString(currentPath) + " " + Arrays.toString(possiblePath));
						String[] successfulPath = combineArrays(currentPath, possiblePath);
						//System.out.println(Arrays.toString(successfulPath));
						int weight = calculatePathWeight(successfulPath);
						//System.out.println(weight);

						//
						if (!Arrays.equals(shortestPath, new String[0]) || secondShortestWeight > weight)
						{
							secondShortestPath = successfulPath;
							secondShortestWeight = weight;
						}
					}
				}
			}
		}

		return secondShortestPath;
	}

	private static String[] combineArrays(String[] a, String[] b)
	{
		if (a == null)
			return b;

		String[] newArray = new String[a.length + b.length];

		System.arraycopy(a, 0, newArray, 0, a.length);
		System.arraycopy(b, 0, newArray, a.length, b.length);

		return newArray;
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
		System.out.println();

		String[] secondShortestPath = g.secondShortestPath("A", "F");
		System.out.println(Arrays.toString(secondShortestPath));
		System.out.println(g.calculatePathWeight(secondShortestPath));

		/*
		System.out.println(Arrays.toString(g.shortestPath("B", "G")));
		System.out.println(Arrays.toString(g.shortestPath("A", "F")));
		System.out.println(Arrays.toString(g.shortestPath("A", "B")));
		System.out.println(Arrays.toString(g.shortestPath("C", "E")));
		*/

		/*
		System.out.println(Arrays.toString(g.secondShortestPath("B", "G")));
		System.out.println(Arrays.toString(g.secondShortestPath("A", "F")));
		System.out.println(Arrays.toString(g.secondShortestPath("A", "B")));
		System.out.println(Arrays.toString(g.secondShortestPath("C", "E")));
		*/

		System.out.println(Arrays.equals(shortestPath, secondShortestPath));
	}
}
