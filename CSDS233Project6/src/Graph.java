/**
 *
 *
 * @author ari
 */
public class Graph
{

	/* Part 1 */

	/**
	 * Adds a node to the graph and checks for duplicates
	 *
	 * @param name
	 * @return true if successful, false otherwise
	 */
	boolean addNode(String name)
	{
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
		return true;
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
		return true;
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
	 * @param from
	 * @param to
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
	 * @param from
	 * @param to
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
	 * @param from
	 * @param to
	 * @return the second shortest path between nodes from and to. Only returns one path in the case of multiple
	 * equivalent results
	 */
	public String[] secondShortestPath(String from, String to)
	{
		return null;
	}
}
