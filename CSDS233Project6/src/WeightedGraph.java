import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * The WeightedGraph class works for both weighted and directed graphs. The weight applies a value
 *
 * @author ari
 */
public class WeightedGraph extends Graph
{
	private ArrayList<Vertex> encountered = new ArrayList<>();
	int count = 0;

	public WeightedGraph()
	{
		super();
	}

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
		/*
		// Iterate through each vertex by order created
		for (int i = 0; i < vertexOrderCreated; i++)
		{
			// Get current vertex
			Vertex v = vertices.get(orderCreated.get(i));
			// Do nothing if vertex does not exist
			if (v != null)
			{
				// Add parents
				StringBuilder sb = new StringBuilder(v + " ");
				// Add children
				for (Edge next : v.getEdges())
					sb.append(next).append(" ");
				// print everything out
				System.out.println(sb.substring(0, sb.length() - 1));
			}
		}
		 */
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
		Scanner scanner;
		try
		{
			scanner = new Scanner(new FileInputStream(filename));
		} catch (FileNotFoundException e)
		{
			scanner = new Scanner(filename);
			e.printStackTrace();
		}
		ArrayList<String> lines = new ArrayList<>();

		while (scanner.hasNextLine())
			lines.add(scanner.nextLine());

		WeightedGraph graph = new WeightedGraph();

		String[][] nodes = new String[lines.size()][];

		for (int i = 0; i < lines.size(); i++)
			nodes[i] = lines.get(i).split(" ");

		// Add Nodes
		for (String[] strArray : nodes)
			graph.addNode(strArray[0]);

		// Add Edges
		for (String[] strArray : nodes)
			for (int i = 2; i < strArray.length; i += 2) //String[] strArray : nodes)
				graph.addWeightedEdge(strArray[0], strArray[i], Integer.parseInt(strArray[i - 1]));

		return graph;
	}

	/* Part 2 */

	/**
	 * Uses Dijkstra’s algorithm to find the shortest path from node from to node to. If there are multiple paths of
	 * equivalent length, you only need to return one of them. If the path does not exist, return an empty array.
	 *
	 * @param from starting point
	 * @param to   end point
	 * @return shortest path if it exists, otherwise return an empty array
	 */
	//TODO
	public String[] shortestPath(String from, String to)
	{
		/*
		Steps:
		1. Mark Vertices as unvisited
		2. All nodes must be initialized with "infinite" distance.
		3. Mark Starting node as current node
		4. From current node, analyze all of its neighbors that are not visited yet, and compute their distances by
		adding the weight of the edge, which establishes the connection between the current node and neighbor node to
		the current distance of the current node
		5. Now compare the recently computed distance with the distance allotted to the neighboring node and neighbor
		node to the neighboring, and treat it as the current distance of the neighboring node,
		6. After that, the surrounding neighbors of the current node, which has not been visited, are considered, and
		the current nodes are marked as visited.
		7. When the ending node is marked as visited, then the algorithm has done its job, otherwise pick the unvisited
		node which has been allotted the minimum distance and treat it as the new current node. After that, start again
		from step 4
		 */

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

		//
		PriorityQueue<Vertex> vertices = new PriorityQueue<>(Vertex::compareByDistance);
		vertices.add(start);

		while (!vertices.isEmpty())
		{
			Vertex current = vertices.remove();

			for (Vertex next : current.getChildren())
			{
				int newWeight = current.getWeight(next);
				if (newWeight < next.distanceFromStart)
				{
					next.distanceFromStart = newWeight;
					vertices.add(next);
					if (current.path == null)
					{
						current.path = new String[] {from};
						next.path = new String[] {from, current.toString()};
					}
					else
					{
						next.path = new String[current.path.length + 1];
						System.arraycopy(current.path, 0, next.path, 0, current.path.length);
						next.path[next.path.length - 1] = next.toString();
					}
				}
			}
		}

		return end.path;
	}


	private List<String[]> getPaths(String from, String to)
	{
		ArrayList<String[]> successfulPaths = new ArrayList<>();

		// Get from/to vertices
		Vertex start = getVertex(from);
		Vertex end = getVertex(to);

		// Either from or to does not exist in graph
		if (start == null || end == null)
			return successfulPaths;

		// Case start is the same as end
		if (start == end)
		{
			successfulPaths.add(new String[] {from});
			return successfulPaths;
		}



		/*
		if (!encountered.contains(getVertex(from)))
			encountered.add(getVertex(from));

		List<Vertex> nextVertices = start.getChildren();

		for (Vertex next : nextVertices)
		{
			if (next != null && !encountered.contains(next))
			{
				String[] possiblePath = BFS(next.toString(), to, "alphabetical");
				if (possiblePath != null && possiblePath[possiblePath.length - 1].equals(to))
				{
					String[] path = new String[possiblePath.length + 1];
					path[0] = from;
					System.arraycopy(possiblePath, 0, path, 1, possiblePath.length);
					successfulPaths.add(path);
					for (int i = count; i < encountered.size(); i++)
						encountered.remove(i--);
				}

				//

			}
		}

		/*
		proceed as far as possible along a given path (via a neighbor)
		before going to the next neighbor
		 *

		// Get children
		LinkedList<Vertex> children = start.getChildren();

		// Set children in order
		Collections.sort(children);
		// Search through all children in the order determined by parameter neighborOrder
		for (Vertex v : children)
			if (v != null)
			{
				// Search for this child's path for String to
				String[] path = getPaths(v.toString(), to);
				if (path[path.length - 1].equals(to))
				{
					// If to is found, return this method's from + the array from the path called recursively
					String[] newPath = new String[path.length + 1];
					newPath[0] = from;
					System.arraycopy(path, 0, newPath, 1, path.length);
					return newPath;
				}
			}
		*/

		//count++;
		return successfulPaths;
	}

	private String[] path(ArrayList<String> currentPath, String from, String to)
	{
		Vertex current = getVertex(from);

		if (current == null)
			return new String[0];

		if (from.equals(to))
			return new String[] {from};

		LinkedList<Vertex> children = current.getChildren();
		for (Vertex v : children)
		{
			if (v != null)
			{

			}
		}

		/*
		for (Vertex v : current.getChildren())
			if (currentPath == null || !currentPath.contains(v.toString()))
			{
				// check child
				if (currentPath == null)
					currentPath = new ArrayList<>();

				currentPath.add(from);
				String[] newPath = path(currentPath, v.toString(), to);
			}
		*/
		return new String[0];
	}

	private int calculatePathWeight(String[] path)
	{
		int weight = 0;

		for (int i = 0; i < path.length - 1; i++)
			weight += getVertex(path[i]).getWeightedEdge(getVertex(path[i + 1])).getWeight();

		return weight;
	}

	/**
	 * @param from starting point
	 * @param to   end point
	 * @return the second-shortest path between nodes from and to. Only returns one path in the case of multiple
	 * equivalent results
	 */
	public String[] secondShortestPath(String from, String to)
	{
		/*
		Vertex start = getVertex(from);
		Vertex end = getVertex(to);

		// Start or end do not exist
		if (start == null || end == null)
			return new String[0];

		// start and end are same
		if (from.equals(to))
			return new String[] {to};

		encountered.clear();

		// Create a queue of successful paths
		PriorityQueue<PathWeights> successfulPaths = new PriorityQueue<>(Collections.reverseOrder());
		// Get each possible path and add it to the queue of successful paths
		for (String[] path : getPaths(from, to))
			successfulPaths.add(new PathWeights(path, calculatePathWeight(path)));

		printPaths(successfulPaths);

		// If queue does not contain 2 or more possible paths, return an empty String array
		if (successfulPaths.size() < 2)
			return new String[0];

		// Return 2nd shortest path
		successfulPaths.remove();
		return successfulPaths.remove().path;

		 */
		return BFS(from, to, "alphabetical");
	}

	/*
	ABDCF
	ABDEGF
	ABDF
	ABEGF
	ADCF
	ADEGF
	ADGF
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
