import java.util.*;

/**
 *
 *
 * @author ari
 */
public class Graph
{
	private Node[] nodes;
	private int numNodes;
	private int maxNum;

	public Graph(int maximum)
	{
		maxNum = maximum;
		nodes = new Node[maximum];
		numNodes = 0;
	}

	private boolean contains(String name)
	{
		for (Node n : nodes)
			if (n.getName().equals(name))
				return true;
		return false;
	}


	/**
	 * Adds a node to the graph and checks for duplicates
	 *
	 * @param name of the node created
	 * @return true if successful, false otherwise
	 */
	boolean addNode(String name)
	{
		if (List.of(nodes).contains(name) || numNodes + 1 == maxNum)
			return false;

		nodes[numNodes++] = new Node(name);
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
	 * @param from
	 * @param to
	 * @return true if successful, false otherwise
	 */
	public boolean addEdge(String from, String to)
	{
		Edge newEdge = new Edge();

		return true;
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
		return true;
	}

	/**
	 * Removes each node in nodelist and their edges from the  graph
	 *
	 * @param nodelist
	 * @return true if successful, false otherwise
	 */
	public boolean removeNodes(String[] nodelist)
	{
		return true;
	}

	/**
	 * Prints the graph in the same adjacency list format in the read method. The nodes and their neighbors and their
	 * neighbors should be in listed in alphabetical order
	 */
	public void printGraph()
	{
		for (Node n : nodes)
			if (n != null)
			{
				System.out.print("<" + n.getName() + "> ");
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
		Scanner scanner = new Scanner(filename);
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
				if (!graph.contains(n))
					graph.addNode(n);

			// Add edges
			graph.addEdges(nodes[0], Arrays.copyOfRange(nodes, 1, nodes.length));
		}

		return graph;
	}

	/* Part 3 */
	/**
	 * Visits a vertex, then make recursive calls on all of its yet-to-be-visited neighbors
	 *
	 * @param from starting point
	 * @param to end point
	 * @param neighborOrder
	 * @return the path or a list of node names, of depth-first search between nodes from and to. The order in which
	 * neighbors are considered is specified by neighborOrder, which can be "alphabetical" or "reverse" for reverse
	 * alphabetical order. It should return an empty String if no path exists
	 */
	public String[] DFS(String from, String to, String neighborOrder)
	{
		return null;
	}

	/**
	 * @param from starting point
	 * @param to end point
	 * @param neighborOrder
	 * @return the path or a list of node names, of breath-first search between nodes from and to. The order in which
	 * neighbors are considered is specified by neighborOrder, which can be "alphabetical" or "reverse" for reverse
	 * alphabetical order. It should return an empty String if no path exists
	 */
	public String[] BFS(String from, String to, String neighborOrder)
	{
		return null;
	}

	/**
	 * @param from starting point
	 * @param to end point
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
		Graph g = new Graph(0).read("graph.txt");
		g.printGraph();
	}
}
