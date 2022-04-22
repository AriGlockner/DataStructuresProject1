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

	/**
	 * @param from starting point
	 * @param to   end point
	 * @return the second-shortest path between nodes from and to. Only returns one path in the case of multiple
	 * equivalent results
	 */
	public String[] secondShortestPath(String from, String to)
	{
		// Get the shortest path. Will be used to compare alternate routes from the shortest path
		final String[] shortestPath = shortestPath(from, to);

		// If there exists no path, return an empty array
		if (Arrays.equals(shortestPath, new String[0]))
			return new String[0];

		// Set the 2nd shortest path as an empty String array
		String[] secondShortestPath = new String[0];
		int secondShortestWeight = 0;

		// Compare deviations off the shortest path
		for (int i = 0; i < shortestPath.length - 1; i++)
		{
			// Current vertex in the shortest path
			Vertex current = getVertex(shortestPath[i]);
			String[] currentPath = Arrays.copyOf(shortestPath, i + 1);

			// Compare deviations off the current vertex
			for (Vertex next : current.getChildren())
			{
				// Check if next vertex is not the next vertex in the shortest path
				if (!next.toString().equals(shortestPath[i + 1]))
				{
					String[] possiblePath = shortestPath(next.toString(), to);

					// Make sure possiblePath is a successful path
					if (possiblePath.length > 0 && containsNoDuplicates(currentPath, possiblePath))
					{
						// Get full possible path and calculate its weight
						possiblePath = combineArrays(currentPath, possiblePath);
						int weight = calculatePathWeight(possiblePath);

						// If the possible path is the new 2nd shortest path, set it as the 2nd shortest path
						if (!Arrays.equals(shortestPath, new String[0]) || secondShortestWeight > weight)
						{
							secondShortestPath = possiblePath;
							secondShortestWeight = weight;
						}
					}
				}
			}
		}
		return secondShortestPath;
	}

	/**
	 * Helper method for 2nd shortest path class
	 *
	 * @param path array of vertices to calculate the weight of
	 * @return weight from start to end of array of vertices path
	 */
	private int calculatePathWeight(String[] path)
	{
		int weight = 0;

		for (int i = 0; i < path.length - 1; i++)
			weight += getVertex(path[i]).getWeightedEdge(getVertex(path[i + 1])).weight;

		return weight;
	}

	/**
	 * Helper method that combines two arrays into one
	 *
	 * @param a first array
	 * @param b second array
	 * @return a + b as 1 array
	 */
	private static String[] combineArrays(String[] a, String[] b)
	{
		if (a == null)
			return b;
		if (b == null)
			return a;

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
		System.out.println("Demo:");
		WeightedGraph g = WeightedGraph.readWeightedGraph("weightedgraph.txt");
		System.out.println("""
				Read:
				Expected:
				A 2 B 1 D
				B 3 D 10 E
				C 4 A 5 F
				D 2 C 2 E 8 F 4 G
				E 6 G
				F
				G 1 F
				Actual:""");
		g.printWeightedGraph();

		// Shortest Path
		System.out.println("\nShortest Path:");
		System.out.println("Expected:\n[A, D, G, F]\nActual:\n" + Arrays.toString(g.shortestPath("A", "F")));
		System.out.println("Expected:\n[D, C, A, B]\nActual:\n" + Arrays.toString(g.shortestPath("D", "B")));
		System.out.println("Expected:\n[C, F]\nActual:\n" + Arrays.toString(g.shortestPath("C", "F")));
		System.out.println("Expected:\n[B, D, E]\nActual:\n" + Arrays.toString(g.shortestPath("B", "E")));

		// 2nd Shortest Path
		System.out.println("\nSecond Shortest Path:");
		System.out.println("Expected:\n[A, D, F]\nActual:\n" + Arrays.toString(g.secondShortestPath("A", "F")));
		System.out.println("Expected:\n[]\nActual:\n" + Arrays.toString(g.secondShortestPath("D", "B")));
		System.out.println("Expected:\n[C, A, D, G, F]\nActual:\n" + Arrays.toString(g.secondShortestPath("C", "F")));
		System.out.println("Expected:\n[B, E]\nActual:\n" + Arrays.toString(g.secondShortestPath("B", "E")));

		// DFS alphabetical
		System.out.println("\nDFS Alphabetical:");
		System.out.println("Expected:\n[A, B, D, C, F]\nActual:\n" + Arrays.toString(g.DFS("A", "F", "alphabetical")));
		System.out.println("Expected:\n[D, C, A, B]\nActual:\n" + Arrays.toString(g.DFS("D", "B", "alphabetical")));
		System.out.println("Expected:\n[C, A, B, D, E, G, F]\nActual:\n" + Arrays.toString(g.DFS("C", "F", "alphabetical")));
		System.out.println("Expected:\n[B, D, E]\nActual:\n" + Arrays.toString(g.DFS("B", "E", "alphabetical")));

		// DFS reverse
		System.out.println("\nDFS Reverse:");
		System.out.println("Expected:\n[A, D, G, F]\nActual:\n" + Arrays.toString(g.DFS("A", "F", "reverse")));
		System.out.println("Expected:\n[D, C, A, B]\nActual:\n" + Arrays.toString(g.DFS("D", "B", "reverse")));
		System.out.println("Expected:\n[C, F]\nActual:\n" + Arrays.toString(g.DFS("C", "F", "reverse")));
		System.out.println("Expected:\n[B, E]\nActual:\n" + Arrays.toString(g.DFS("B", "E", "reverse")));

		// BFS alphabetical
		System.out.println("\nBFS Alphabetical:");
		System.out.println("Expected:\n[A, D, F]\nActual:\n" + Arrays.toString(g.BFS("A", "F", "alphabetical")));
		System.out.println("Expected:\n[D, C, A, B]\nActual:\n" + Arrays.toString(g.BFS("D", "B", "alphabetical")));
		System.out.println("Expected:\n[C, F]\nActual:\n" + Arrays.toString(g.BFS("C", "F", "alphabetical")));
		System.out.println("Expected:\n[B, E]\nActual:\n" + Arrays.toString(g.BFS("B", "E", "alphabetical")));

		// BFS reverse
		System.out.println("\nBFS Reverse:");
		System.out.println("Expected:\n[A, D, F]\nActual:\n" + Arrays.toString(g.BFS("A", "F", "reverse")));
		System.out.println("Expected:\n[D, C, A, B]\nActual:\n" + Arrays.toString(g.BFS("D", "B", "reverse")));
		System.out.println("Expected:\n[C, F]\nActual:\n" + Arrays.toString(g.BFS("C", "F", "reverse")));
		System.out.println("Expected:\n[B, E]\nActual:\n" + Arrays.toString(g.BFS("B", "E", "reverse")));

		// Add Node
		System.out.println("\nAdd Node:");
		System.out.println("Expected:\nfalse\nActual:\n" + g.addNode("B"));
		System.out.println("Expected:\ntrue\nActual:\n" + g.addNode("H"));

		// Add Nodes
		System.out.println("\nAdd Nodes:");
		System.out.println("Expected:\ntrue\nActual:\n" + g.addNodes(new String[] {"I", "J", "K"}));
		System.out.println("Expected:\nfalse\nActual:\n" + g.addNodes(new String[] {"A", "B", "C", "L", "I", "K", "E"}));

		// Add Weighted Edge
		System.out.println("\nAdd Weighted Edge:");
		System.out.println("Expected:\ntrue\nActual:\n" + g.addWeightedEdge("I", "J", 1));
		System.out.println("Expected:\ntrue\nActual:\n" + g.addWeightedEdge("J", "K", 2));
		System.out.println("Expected:\ntrue\nActual:\n" + g.addWeightedEdge("K", "F", 4));
		System.out.println("Expected:\nfalse\nActual:\n" + g.addWeightedEdge("K", "F", 4));
		System.out.println("Expected:\nfalse\nActual:\n" + g.addWeightedEdge("K", "F", 2));
		System.out.println("Expected:\ntrue\nActual:\n" + g.addWeightedEdge("A", "L", 1));
		System.out.println("Expected:\ntrue\nActual:\n" + g.addWeightedEdge("D", "L", 2));
		System.out.println("Expected:\ntrue\nActual:\n" + g.addWeightedEdge("G", "L", 3));
		System.out.println("Expected:\ntrue\nActual:\n" + g.addWeightedEdge("F", "L", 4));

		// Add Weighted Edges
		System.out.println("\nAdd Weighted Edges:");
		System.out.println("Expected:\ntrue\nActual:\n" + g.addWeightedEdges("L", new String[]{"A", "D", "G", "F"}, new int[] {1, 2, 3, 4}));
		System.out.println("Expected:\nfalse\nActual:\n" + g.addWeightedEdges("L", new String[]{"A", "D", "G", "F"}, new int[] {0, 0, 0, 0}));
		System.out.println("Expected:\ntrue\nActual:\n" + g.addWeightedEdges("H", new String[]{"K", "A"}, new int[] {5, 25}));

		// print graph
		System.out.println("\nPost Adding:");
		System.out.println("""
				Expected:
				A 2 B 1 D 1 L
				B 3 D 10 E
				C 4 A 5 F
				D 2 C 2 E 8 F 4 G 2 L
				E 6 G
				F 4 L
				G 1 F 3 L
				H 5 K 25 A
				I 1 J
				J 2 K
				K 4 F
				L 1 A 2 D 3 G 4 F
				Actual:""");
		g.printWeightedGraph();

		// Remove Nodes
		System.out.println("\nRemove Nodes:");
		System.out.println("Expected:\ntrue\nActual:\n" + g.removeNodes(new String[]{"L", "I", "J"}));
		System.out.println("Expected:\nfalse\nActual:\n" + g.removeNodes(new String[]{"K", "J"}));

		// Remove Node
		System.out.println("\nRemove Node:");
		System.out.println("Expected:\ntrue\nActual:\n" + g.removeNode("H"));
		System.out.println("Expected:\nfalse\nActual:\n" + g.removeNode("H"));
		System.out.println("Expected:\nfalse\nActual:\n" + g.removeNode("K"));

		// Post Removing
		System.out.println("""
				
				Post Removing:
				Expected:
				A 2 B 1 D
				B 3 D 10 E
				C 4 A 5 F
				D 2 C 2 E 8 F 4 G
				E 6 G
				F
				G 1 F
				Actual:""");
		g.printWeightedGraph();
	}
}
