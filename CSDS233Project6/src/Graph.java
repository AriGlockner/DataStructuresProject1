import java.util.LinkedList;

/**
 *
 *
 * @author ari
 */
public class Graph
{
	static class Node {
		private String name;

		// Matrix Representation
		private Edge edgeHead; // adjacency list
		/*
		// List Representation
		private LinkedList<Edge> edges; // adjacency list
		*/

		private boolean encountered;
		private boolean done;
		private Node parent;
		//private double cost;

		public Node(String name)
		{
			this.name = name;
		}
	}

	static class Edge {
		private int endNode;
		//private double cost;
		private Edge next; // Matrix Representation
	}

	private Node[] nodes;
	private int numNodes;
	private int maxNum;

	public Graph(int maximum)
	{
		maxNum = maximum;
		nodes = new Node[maximum];
		numNodes = 0;
	}

	/**
	 * Adds a node to the graph and checks for duplicates
	 *
	 * @param name of the node created
	 * @return true if successful, false otherwise
	 */
	boolean addNode(String name)
	{
		if (numNodes + 1 == maxNum)
			return false;

		nodes[numNodes++] = new Node(name);
		return true;
	}

	/**
	 * Adds a list of nodes to the graph and checks for duplicates
	 *
	 * @param nodes
	 * @return true if successful, false otherwise
	 */
	public boolean addNodes(String[] nodes)
	{
		boolean successful = true;

		for (String n : nodes)
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
		return null;
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

	}
}
