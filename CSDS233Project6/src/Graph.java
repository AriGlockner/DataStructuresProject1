import java.io.*;
import java.util.*;

/**
 * This class is a collection of vertices and edges forming a graph.
 *
 * @author ari
 */
public class Graph
{
	// All Vertices in Graph
	private Hashtable<String, Vertex> vertices;
	// Order that the vertices were placed
	private ArrayList<String> order;

	/**
	 * Initializes a new Empty Graph
	 */
	public Graph()
	{
		vertices = new Hashtable<>();
		order = new ArrayList<>();
	}

	/**
	 * @param name to find vertex of
	 * @return vertex belonging to name or null if name does not exist in Graph
	 */
	Vertex getVertex(String name)
	{
		return vertices.get(name);
	}

	/**
	 * Adds a node to the graph and checks for duplicates
	 *
	 * @param name of the node created
	 * @return true if successful, false otherwise
	 */
	boolean addNode(String name)
	{
		// Can't add new vertex
		if (order.contains(name))
			return false;
		// Add a new vertex
		vertices.put(name, new Vertex(name));
		order.add(name);
		return true;
	}

	/**
	 * Adds a list of nodes to the graph and checks for duplicates
	 *
	 * @param names list of nodes to add
	 * @return true if successful, false otherwise
	 */
	public boolean addNodes(String[] names)
	{
		boolean successful = true;

		for (String n : names)
			if (!addNode(n) && successful)
				successful = false;

		return successful;
	}

	/**
	 * Adds an edge from node from to node to
	 *
	 * @param from starting point
	 * @param to   destination to add
	 * @return true if successful, false otherwise
	 */
	public boolean addEdge(String from, String to)
	{
		if (from == null || to == null)
			return false;

		// Get Vertex to add to
		Vertex start = vertices.get(from);
		Vertex destination = vertices.get(to);

		// Return false if either vertex does not exist does not exist
		if (start == null || destination == null)
			return false;

		// Add edge in Vertex Class
		return start.addEdge(destination);
	}

	/**
	 * Adds an undirected edge from node from to each node in tolist
	 *
	 * @param from   starting point
	 * @param tolist list of endpoints
	 * @return true if successful, false otherwise
	 */
	public boolean addEdges(String from, String[] tolist)
	{
		boolean successful = true;

		for (String to : tolist)
			if (!addEdge(from, to) && successful)
				successful = false;

		return successful;
	}

	/**
	 * Removes a node from the graph along with all connected edges
	 *
	 * @param name name of node to remove
	 * @return true if successful, false otherwise
	 */
	public boolean removeNode(String name)
	{
		// Get vertex to remove
		Vertex toRemove = vertices.get(name);
		// Vertex to remove does not exist
		if (toRemove == null)
			return false;

		// Get Vertices to remove edge from
		LinkedList<Vertex> parents = toRemove.getParents();
		// Remove each edge
		for (Vertex p : parents)
			p.removeEdge(toRemove);

		// Remove toRemove
		order.remove(name);
		return vertices.remove(name, toRemove);
	}

	/**
	 * Removes each node in nodelist and their edges from the  graph
	 *
	 * @param nodelist remove each node in list
	 * @return true if successful, false otherwise
	 */
	public boolean removeNodes(String[] nodelist)
	{
		boolean successful = true;
		for (String vertex : nodelist)
			successful = removeNode(vertex) && successful;
		return successful;
	}

	/**
	 * Prints the graph in the same adjacency list format in the read method. The nodes and their neighbors and their
	 * neighbors should be in listed in alphabetical order
	 */
	public void printGraph()
	{
		System.out.println(this);
	}

	/**
	 * @return graph as a String in the same format as the read method
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		// Iterate through each vertex by order created
		for (String s : order)
		{
			// Get current vertex
			Vertex v = vertices.get(s); //orderCreated.get(i));

			// Do nothing if vertex does not exist
			if (v != null)
			{
				// Add current vertex
				sb.append(v).append(" ");

				// Add vertices that this vertex can travel to
				for (Edge next : v.getEdges())
					sb.append(next).append(" ");

				// Remove space at end of line
				sb.deleteCharAt(sb.length() - 1);
				// Go to next line
				sb.append("\n");
			}
		}

		return sb.substring(0, Math.max(0, sb.length() - 1));
	}

	/**
	 * Constructs a graph from a text file using the following format:
	 * <nodename1> <nodename1> <nodename2> ...
	 * <nodename2> <nodename1> <nodename2> ...
	 * ...
	 *
	 * @param filename filepath to create nodes from
	 * @return a new Graph created from the filename
	 */
	public static Graph read(String filename)
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

		Graph graph = new Graph();

		String[][] nodes = new String[lines.size()][];

		for (int i = 0; i < lines.size(); i++)
			nodes[i] = lines.get(i).split(" ");

		// Add Nodes
		for (String[] strArray : nodes)
			graph.addNode(strArray[0]);

		// Add Edges
		for (String[] strArray : nodes)
			graph.addEdges(strArray[0], Arrays.copyOfRange(strArray, 1, nodes.length));
		return graph;
	}

	/**
	 * Visits a vertex, then make recursive calls on all of its yet-to-be-visited neighbors
	 *
	 * @param from          starting point
	 * @param to            end point
	 * @param neighborOrder either alphabetical or reverse order
	 * @return the path or a list of node names, of depth-first search between nodes from and to. The order in which
	 * neighbors are considered is specified by neighborOrder, which can be "alphabetical" or "reverse" for reverse
	 * alphabetical order. It should return an empty String if no path exists
	 */
	//TODO: Fix
	public String[] DFS(String from, String to, String neighborOrder)
	{
		Vertex start = vertices.get(from);
		Vertex end = vertices.get(to);

		// Either from or to does not exist in graph
		if (start == null || end == null)
			return new String[0];

		// Case start is the same as end
		if (start == end)
			return new String[] {from};

		// Set all vertices encountered as false
		setVisitedFalse();

		// Get return path if it exists, otherwise an empty array
		return helpDFS(from, to, !(neighborOrder.trim().equalsIgnoreCase("reverse")));
	}

	/**
	 * Helper method that does the recursive part of DFS. This method is used so that vertices encountered is set to
	 * false only at the start of the algorithm, not at each recursive call.
	 *
	 * @param from          starting point
	 * @param to            end point
	 * @param order either alphabetical or reverse order
	 * @return the path or a list of node names, of depth-first search between nodes from and to. The order in which
	 * neighbors are considered is specified by neighborOrder, which can be "alphabetical" or "reverse" for reverse
	 * alphabetical order. It should return an empty String if no path exists
	 */
	private String[] helpDFS(String from, String to, boolean order)
	{
		Vertex start = vertices.get(from);

		// Return an empty array if start or end does not exist
		if (start == null ||  vertices.get(to) == null)
			return new String[0];

		if (from.equals(to))
			return new String[] {from};

		// Set start as encountered
		start.encountered = true;
		// 
		LinkedList<Vertex> vertices = start.getChildren();

		// Sort order according to parameter order
		if (order)
			vertices.sort(Comparator.comparing(Vertex::toString));
		else
			vertices.sort(Comparator.comparing(Vertex::toString).reversed());

		for (Vertex next : vertices)
		{
			// Check to see that vertex has not been encountered yet
			if (!next.encountered)
			{
				// Set vertex as encountered and get path from vertex to destination
				next.encountered = true;
				String[] path = helpDFS(next.toString(), to, order);

				// Return path if it exists
				if (path.length > 0 && path[path.length - 1].equals(to))
					return combineArrays(new String[] {from}, path);
			}
		}

		// Default Condition: Return an empty String array
		return new String[0];
	}

	/**
	 * Breath-first search finds verticies by:
	 * 1) visit a vertex
	 * 2) visit all of its neighbors
	 * 3) visit all unvisited vertices 2 edges away
	 * 4) visit all unvisited vertices 3 edges away,
	 * etc.
	 *
	 * @param from          starting point
	 * @param to            end point
	 * @param neighborOrder either alphabetical or reverse order
	 * @return the path or a list of node names, of breath-first search between nodes from and to. The order in which
	 * neighbors are considered is specified by neighborOrder, which can be "alphabetical" or "reverse" for reverse
	 * alphabetical order. It should return an empty String if no path exists.
	 */
	public String[] BFS(String from, String to, String neighborOrder)
	{
		Vertex start = getVertex(from);
		Vertex end = getVertex(to);

		// Either from or to does not exist in graph
		if (start == null || end == null)
			return new String[0];

		// Case start is the same as end
		if (start.equals(end))
			return new String[] {from};

		// sort children according to alphabetical or reverse order
		boolean sortReverseOrder = neighborOrder.toLowerCase(Locale.ROOT).trim().equals("reverse");

		// Set encountered as false and reset the path for all vertices
		setVisitedFalse();

		// Order to search the vertices in
		Queue<Vertex> bfsVertexOrder = new ArrayDeque<>();

		// Add start vertex to the queue
		bfsVertexOrder.add(start);
		start.encountered = true;
		start.path = new String[] {from};

		// Remove the top vertex from the queue. If a possible path is the destination, return the path to get to it
		// Otherwise, add its possible paths that have yet to be encountered.
		while (!bfsVertexOrder.isEmpty())
		{
			Vertex current = bfsVertexOrder.remove();

			// Get vertices to add
			LinkedList<Vertex> nextList = current.getChildren();

			// Sort next nodes to add according to neighborOrder
			if (sortReverseOrder)
				nextList.sort(Collections.reverseOrder());
			else
				Collections.sort(nextList);

			// Add all vertices that haven't been encountered
			for (Vertex next : nextList)
			{
				// If vertex has not been encountered yet, add it to the queue
				if (!next.encountered)
				{
					// Set path
					String[] path = new String[current.path.length + 1];
					System.arraycopy(current.path, 0, path, 0, current.path.length);
					path[path.length - 1] = next.toString();
					next.path = path;

					// If the vertex to add is the destination, return that vertex's path
					if (next.toString().equals(to))
						return path;

					// Set as encountered
					next.encountered = true;

					// Add next to the path
					bfsVertexOrder.add(next);
				}
			}
		}

		// Return an empty String array if to does not exist in this path
		return new String[0];
	}

	/**
	 * @param from starting node
	 * @param to   destination node
	 * @return 2nd shortest path between nodes from and to. Returns one path in the case of multiple equivalent results.
	 */
	public String[] secondShortestPath(String from, String to)
	{
		// Return an empty array if to/from does not exist
		if (getVertex(from) == null || getVertex(to) == null)
			return new String[0];

		return getSecondShortestPath(from, to, false);
	}

	/**
	 * Helper method for secondShortestPath that calculates the 2nd shortest path
	 *
	 * @param from              starting node
	 * @param to                destination node
	 * @param foundShortestPath boolean to determine if the shortest path has already been found
	 * @return 2nd shortest path
	 */
	//TODO: Fix
	private String[] getSecondShortestPath(String from, String to, boolean foundShortestPath)
	{
		Vertex start = getVertex(from);
		Vertex end = getVertex(to);

		// Case start is the same as end
		if (start.equals(end))
			return new String[] {from};

		// Get children
		List<Vertex> children = start.getChildren();

		// search if to is in edge of current vertex
		for (Vertex c : children)
			if (c.toString().equals(to))
				return new String[] {from, to};

		// sort children according to order
		Collections.sort(children);

		// Recursively call to find to
		for (Vertex c : children)
		{
			String[] path = getSecondShortestPath(c.toString(), to, foundShortestPath);
			if (path[path.length - 1].equals(to))
			{
				if (foundShortestPath)
				{
					String[] newPath = new String[path.length + 1];
					newPath[0] = from;
					System.arraycopy(path, 0, newPath, 1, path.length);
					return newPath;
				} else
					foundShortestPath = true;
			}
		}

		// Return an empty String array if to does not exist in this path
		return new String[0];
	}

	/**
	 * Helper method for Graph Transversal Methods in Weighted Graph class that sets the distances from start to
	 * Integer.MAX_VALUE and sets their paths to null
	 */
	void setVisitedFalse()
	{
		for (String name : order)
		{
			Vertex v = vertices.get(name);
			v.distanceFromStart = Integer.MAX_VALUE;
			v.path = null;
			v.encountered = false;
		}
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
		// Read Graph
		Graph graph = Graph.read("graph.txt");
		System.out.println("""
				Expected:
				A B C D
				B A C
				C A B
				D A
				Actual:""");
		graph.printGraph();

		// DFS
		System.out.println("\nDFS:");
		System.out.println("Expected:\n[A, D]\nActual:\n" + Arrays.toString(graph.DFS("A", "D", "alphabetical")));
		System.out.println("Expected:\n[A, D]\nActual:\n" + Arrays.toString(graph.DFS("A", "D", "reverse")));
		System.out.println("Expected:\n[A, B, C]\nActual:\n" + Arrays.toString(graph.DFS("A", "C", "alphabetical")));
		System.out.println("Expected:\n[A, C]\nActual:\n" + Arrays.toString(graph.DFS("A", "C", "reverse")));
		/*
		// BFS
		System.out.println("\nBFS:");
		System.out.println("Expected:\n[A, D]\nActual:\n" + Arrays.toString(graph.DFS("A", "D", "alphabetical")));
		System.out.println("Expected:\n[A, D]\nActual:\n" + Arrays.toString(graph.DFS("A", "D", "alphabetical")));

		// 2nd Shortest Path
		System.out.println("\n2nd Shortest Path:");
		System.out.println("Expected:\n\nActual\n" + Arrays.toString(graph.secondShortestPath("A", "D")));

		// Add Node
		System.out.println("\nAdd Node:");
		System.out.println("Expected:\ntrue\nActual:\n" + graph.addNode("E"));
		System.out.println("Expected:\nfalse\nActual:\n" + graph.addNode("E"));

		// Add Nodes
		System.out.println("\nAdd Nodes:");
		System.out.println("Expected:\ntrue\nActual:\n" + graph.addNodes(new String[]{"F", "G"}));
		System.out.println("Expected:\nfalse\nActual:\n" + graph.addNodes(new String[]{"D", "H"}));

		// Add Edge
		System.out.println("\nAdd Edge:");
		System.out.println("Expected:\ntrue\nActual:\n" + graph.addEdge("D", "E"));
		System.out.println("Expected:\nfalse\nActual:\n" + graph.addEdge("A", "D"));

		// Add Edges
		System.out.println("\nAdd Edges:");
		System.out.println("Expected:\ntrue\nActual:\n" + graph.addEdges("E", new String[] {"F", "G", "H"}));
		System.out.println("Expected:\nfalse\nActual:\n" + graph.addEdges("H", new String[] {"A", "G", "A"}));

		// Print out graph to show added nodes/edges
		System.out.println("\nGraph:");
		graph.printGraph();

		// Remove Node
		System.out.println("\nRemove Node:");
		System.out.println("Expected:\ntrue\nActual:\n" + graph.removeNode("H"));
		System.out.println("Expected:\nfalse\nActual:\n" + graph.removeNode("H"));

		// Remove Nodes
		System.out.println("\nRemove Nodes:");
		System.out.println("Expected:\ntrue\nActual:\n" + graph.removeNodes(new String[]{"F", "G"}));
		System.out.println("Expected:\nfalse\nActual:\n" + graph.removeNodes(new String[]{"E", "G"}));

		// Print out graph to show removed nodes
		System.out.println("\nGraph:");
		graph.printGraph();
		*/
	}
}
