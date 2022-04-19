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
		if (vertices.contains(name))
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
		return sb.substring(0, sb.length() - 1);
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
	public String[] DFS(String from, String to, String neighborOrder)
	{
		Vertex start = vertices.get(from); //getVertex(from);
		Vertex end = vertices.get(to); //getVertex(to);

		// Either from or to does not exist in graph
		if (start == null || end == null)
			return new String[0];

		// Case start is the same as end
		if (start == end)
			return new String[] {from};

		/*
		proceed as far as possible along a given path (via a neighbor)
		before going to the next neighbor
		 */

		// Get children
		LinkedList<Vertex> children = start.getChildren();

		// Set children in order
		if (neighborOrder.toLowerCase(Locale.ROOT).trim().equals("alphabetical"))
			Collections.sort(children);
		else if (neighborOrder.toLowerCase(Locale.ROOT).trim().equals("reverse"))
			children.sort(Collections.reverseOrder());

		// Search through all children in the order determined by parameter neighborOrder
		for (Vertex v : children)
			if (v != null)
			{
				// Search for this child's path for String to
				String[] path = BFS(v.toString(), to, neighborOrder);
				if (path[path.length - 1].equals(to))
				{
					// If to is found, return this method's from + the array from the path called recursively
					String[] newPath = new String[path.length + 1];
					newPath[0] = from;
					System.arraycopy(path, 0, newPath, 1, path.length);
					return newPath;
				}
			}

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

		// Get children
		List<Vertex> children = start.getChildren();

		// search if to is in edge of current vertex
		for (Vertex c : children)
			if (c.toString().equals(to))
				return new String[] {from, to};

		// sort children according to alphabetical or reverse order
		if (neighborOrder.toLowerCase(Locale.ROOT).trim().equals("alphabetical"))
			Collections.sort(children);
		else if (neighborOrder.toLowerCase(Locale.ROOT).trim().equals("reverse"))
			children.sort(Collections.reverseOrder());

		// Recursively call to find to
		for (Vertex c : children)
		{
			String[] path = BFS(c.toString(), to, neighborOrder);
			if (path[path.length - 1].equals(to))
			{
				String[] newPath = new String[path.length + 1];
				newPath[0] = from;
				System.arraycopy(path, 0, newPath, 1, path.length);
				return newPath;
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
	//TODO: Write Method
	public String[] secondShortestPath(String from, String to)
	{
		/*
		String[] shortestPath = BFS(from, to, "alphabetical");


		// Get fastest path
		String[] path = BFS(from, to, "alphabetical");


		return path;

		 */
		return getSecondShortestPath(from, to, false); //BFS(from, to, "alphabetical"));
	}

	private String[] getSecondShortestPath(String from, String to, boolean foundShortestPath)
	{
		Vertex start = getVertex(from);
		Vertex end = getVertex(to);

		// Either from or to does not exist in graph
		if (start == null || end == null)
			return new String[0];

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
				}
				else
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
		}
	}

	/**
	 * Demo
	 *
	 * @param args arguments
	 */
	public static void main(String[] args)
	{

		/*
		Graph g1 = Graph.read("graph.txt");
		g1.printGraph();
		System.out.println();
		*/
		Graph g2 = new Graph();
		g2.addNodes(new String[] {"A", "B", "C", "D"});
		g2.addEdges("A", new String[] {"B", "C"});
		g2.addEdges("B", new String[] {"D"});
		g2.addEdges("C", new String[] {"B", "D"});
		g2.printGraph();
		System.out.println();

		// BFS
		System.out.println(Arrays.toString(g2.BFS("A", "D", "alphabetical")));
		System.out.println(Arrays.toString(g2.BFS("D", "A", "alphabetical")));
		System.out.println(Arrays.toString(g2.BFS("DoesNotExist", "DoesNotExist", "alphabetical")));
		System.out.println(Arrays.toString(g2.BFS("A", "D", "reverse")));
		System.out.println();

		// BDS
		System.out.println(Arrays.toString(g2.DFS("A", "D", "alphabetical")));
		System.out.println(Arrays.toString(g2.DFS("D", "A", "alphabetical")));
		System.out.println(Arrays.toString(g2.DFS("DoesNotExist", "DoesNotExist", "alphabetical")));
		System.out.println(Arrays.toString(g2.DFS("A", "D", "reverse")));
		System.out.println();

		// 2nd Shortest Path
		System.out.println(Arrays.toString(g2.secondShortestPath("A", "D")));
		System.out.println(Arrays.toString(g2.BFS("A", "D", "alphabetical")));
		System.out.println();
		// Remove
		/*
		System.out.println();
		g2.removeNode("B");
		g2.printGraph();
		 */
	}
}
