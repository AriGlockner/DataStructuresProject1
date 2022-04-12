import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileInputStream;

/**
 * @author ari
 */
public class Graph
{
	//private Node[] nodes;
	private ArrayList<Vertex> verticies;
	private int numNodes;
	private int maxNum;

	public Graph(int maximum)
	{
		maxNum = maximum;
		verticies = new ArrayList<>(maximum);
		numNodes = 0;
	}

	/**
	 * @param name to find vertex of
	 * @return vertex belonging to name or null if name does not exist in Graph
	 */
	Vertex getVertex(String name)
	{
		// Find the vertex
		for (Vertex v : verticies)
			if (v.getName().equals(name))
				return v;
		// Return null if name does not exist
		return null;
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
		if (getVertex(name) != null)
			return false;
		// Add a new vertex
		verticies.add(new Vertex(name));
		return true;
	}

	/**
	 * Adds a list of nodes to the graph and checks for duplicates
	 *
	 * @param names
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
		Vertex start = getVertex(from);
		Vertex destination = getVertex(to);

		// Return false if either vertex does not exist does not exist
		if (start == null || destination == null)
			return false;

		// Add edge in Vertex Class
		return start.addEdge(destination);
	}

	/**
	 * Adds an undirected edge from node from to each node in tolist
	 *
	 * @param from
	 * @param tolist
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
	 * @param name
	 * @return true if successful, false otherwise
	 */
	public boolean removeNode(String name)
	{
		Vertex toRemove = getVertex(name);
		// Vertex to remove does not exist
		if (toRemove == null)
			return false;

		// Remove toRemove's edges
		LinkedList<Vertex> parents = toRemove.getParents();
		for (Vertex p : parents)
			p.removeEdge(toRemove);

		// Remove toRemove
		return verticies.remove(toRemove);
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
		for (Vertex v : verticies)
			if (v != null)
			{
				StringBuilder sb = new StringBuilder(v + " ");

				//for (Edge e : v.getEdges())
					//sb.append(e.getTo()).append(" ");
				for (Vertex n : v.getChildren())
					sb.append(n).append(" ");

				System.out.println(sb.substring(0, sb.length() - 1));
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
	public Graph read(String filename)
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

		Graph graph = new Graph(lines.size());

		for (String s : lines)
		{
			// Split into nodes
			String[] nodes = s.split(" ");

			// Add nodes
			for (String n : nodes)
				graph.addNode(n);

			// Add edges
			graph.addEdges(nodes[0], Arrays.copyOfRange(nodes, 1, nodes.length));
		}
		System.out.println(lines);
		return graph;
	}

	/* Part 3 */
	/**
	 * Visits a vertex, then make recursive calls on all of its yet-to-be-visited neighbors
	 *
	 * @param from          starting point
	 * @param to            end point
	 * @param neighborOrder
	 * @return the path or a list of node names, of depth-first search between nodes from and to. The order in which
	 * neighbors are considered is specified by neighborOrder, which can be "alphabetical" or "reverse" for reverse
	 * alphabetical order. It should return an empty String if no path exists
	 */
	public String[] DFS(String from, String to, String neighborOrder)
	{
		Vertex start = getVertex(from);
		Vertex end = getVertex(to);

		// Either from or to does not exist in graph
		if (start == null || end == null)
			return null;

		// Case start is the same as end
		if (start == end)
			return new String[] { from };

		/*
		proceed as far as possible along a given path (via a neighbor)
		before going to the next neighbor
		 */

		String[] children = (String[]) start.getChildren().toArray();
		return DFS(children[0], to, neighborOrder);
	}



	/**
	 * @param from          starting point
	 * @param to            end point
	 * @param neighborOrder
	 * @return the path or a list of node names, of breath-first search between nodes from and to. The order in which
	 * neighbors are considered is specified by neighborOrder, which can be "alphabetical" or "reverse" for reverse
	 * alphabetical order. It should return an empty String if no path exists
	 */
	public String[] BFS(String from, String to, String neighborOrder)
	{
		Vertex start = getVertex(from);
		Vertex end = getVertex(to);
		// Either from or to does not exist in graph
		if (start == null || end == null)
			return null;

		// Case start is the same as end
		if (start.equals(end))
			return new String[] { from };

		/*
		visit a vertex
		visit all of its neighbors
		visit all unvisited vertices 2 edges away
		visit all unvisited vertices 3 edges away, etc
		 */
		// Get children
		List<Vertex> children = start.getChildren();

		// search if to is in edge of current vertex
		for (Vertex c : children)
			if (c.getName().equals(to))
				return new String[] { from, to };

		// sort children according to alphabetical or reverse order
		if (neighborOrder.toLowerCase(Locale.ROOT).trim().equals("alphabetical"))
			Collections.sort(children);
		else if (neighborOrder.toLowerCase(Locale.ROOT).trim().equals("reverse"))
			children.sort(Collections.reverseOrder());

		// Recursively call to find to
		for (Vertex c : children)
		{
			String[] path = BFS(c.getName(), to, neighborOrder);
			if (path[path.length - 1].equals(to))
			{
				String[] newPath = new String[path.length + 1];
				newPath[0] = from;
				System.arraycopy(path, 0, newPath, 1, path.length);
				return newPath;
			}
		}

		//String[] children = (String[]) start.getChildren().toArray();


		//return DFS(children[0], to, neighborOrder);
		return null;
	}

	/**
	 * @param from starting point
	 * @param to   end point
	 * @return the second-shortest path between nodes from and to. Only returns one path in the case of multiple
	 * equivalent results
	 */
	public String[] secondShortestPath(String from, String to)
	{
		return null;
	}

	/**
	 * Demo
	 *
	 * @param args arguments
	 */
	public static void main(String[] args)
	{
		Graph g1 = new Graph(0).read("graph.txt");
		g1.printGraph();

		System.out.println();

		Graph g2 = new Graph(10);
		g2.addNodes(new String[] {"A", "B", "C", "D"});
		g2.addEdges("A", new String[] {"B", "C", "D"});
		g2.addEdges("B", new String[] {"A", "C"});
		g2.addEdges("C", new String[] {"A", "B"});
		g2.addEdge("D", "A");
		g2.printGraph();
		System.out.println();

		g2.removeNode("A");
		g2.printGraph();
		System.out.println();
		System.out.print(Arrays.toString(g2.BFS("A", "A", "alphabetical")));
	}
}
