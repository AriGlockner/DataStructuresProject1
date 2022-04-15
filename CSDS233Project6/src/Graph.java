import java.io.*;
import java.util.*;

/**
 * This class is a collection of vertices and edges forming a graph. 
 *
 * @author ari
 */
public class Graph
{
	private Hashtable<String, Vertex> vertices;
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
		//orderCreated.put(vertexOrderCreated++, name);
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
		// Iterate through each vertex by order created
		//for (int i = 0; i < vertexOrderCreated; i++)
		for (String s : order)
		{
			// Get current vertex
			Vertex v = vertices.get(s); //orderCreated.get(i));
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
	}

	/* Part 2 */

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

		String[][] nodes = new String[lines.size()][];

		Graph graph = new Graph();

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

	/* Part 3 */

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
		Vertex start = vertices.get(from); //getVertex(from);
		Vertex end = vertices.get(to); //getVertex(to);
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

	void setVisited(boolean visited)
	{
		for (String name : order)
			vertices.get(name).visited = visited;
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
		Vertex start = vertices.get(from);
		Vertex end = vertices.get(to);

		// Start or end do not exist
		if (start == null || end == null)
			return new String[0];

		// start and end are same
		if (from.equals(to))
			return new String[] {to};

		// Calculate path

		// Path does not exist
		return new String[0];
	}

	/**
	 * @param from starting point
	 * @param to   end point
	 * @return the second-shortest path between nodes from and to. Only returns one path in the case of multiple
	 * equivalent results
	 */
	public String[] secondShortestPath(String from, String to)
	{
		return new String[0];
	}

	/**
	 * Demo
	 *
	 * @param args arguments
	 */
	public static void main(String[] args)
	{
		/*
		Graph g1 = new Graph(0).read("graph.txt");
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

		// Remove
		/*
		System.out.println();
		g2.removeNode("B");
		g2.printGraph();
		 */
	}
}
