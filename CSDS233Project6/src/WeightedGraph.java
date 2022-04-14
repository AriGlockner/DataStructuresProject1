import java.io.*;
import java.util.*;

/**
 * The WeightedGraph class works for both weighted and directed graphs. The weight applies a value
 *
 * @author ari
 */
public class WeightedGraph extends Graph
{
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
	//TODO: Fix stack overflow error when path forms a circle
	public String[] shortestPath(String from, String to)
	{
		Vertex start = getVertex(from);
		Vertex end = getVertex(to);

		// Start or end do not exist
		if (start == null || end == null)
			return new String[0];

		// start and end are same
		if (from.equals(to))
			return new String[] {to};

		// Calculate path

		// Check shortest path
		List<WeightedEdge> paths = start.getWeightedEdges();
		// Remove paths that loop back to from
		paths.removeIf(we -> we.getTo().toString().equals(from));
		// Error due to path forming a circle. Have to add in encountered variable
		Collections.sort(paths);

		String[] shortestPath = new String[0];
		int weight = 0;
		PriorityQueue<PathWeights> successfulPaths = new PriorityQueue<>(Collections.reverseOrder());

		for (WeightedEdge path : paths)
		{
			String[] potentialPath = shortestPath(paths.get(0).getTo().toString(), to);

			// Add paths
			if (!Arrays.equals(potentialPath, new String[0]) && potentialPath[potentialPath.length - 1].equals(to))
				successfulPaths.add(new PathWeights(potentialPath, calculatePathWeight(potentialPath)));
		}

		// Path does not exist
		if (successfulPaths.isEmpty())
			return new String[0];
		// Return shortest path
		return successfulPaths.remove().path;
	}

	private int calculatePathWeight(String[] path)
	{
		int weight = 0;

		for (int i = 0; i < path.length - 1; i++)
		{
			Vertex start = getVertex(path[i]);
			WeightedEdge weightedEdge = start.getWeightedEdge(getVertex(path[i + 1]));
			weight += weightedEdge.getWeight();
		}

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
		//TODO: Implement like shortestPath. Use a heap to store all paths and get the 2nd smallest min
		return new String[0];
	}

	static class PathWeights implements Comparable<PathWeights>
	{
		private final String[] path;
		private final double weight;

		public PathWeights(String[] path, double weight)
		{
			this.path = path;
			this.weight = weight;
		}

		/**
		 * Compares this object with the specified object for order.  Returns a
		 * negative integer, zero, or a positive integer as this object is less
		 * than, equal to, or greater than the specified object.
		 *
		 * <p>The implementor must ensure {@link Integer#signum
		 * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
		 * all {@code x} and {@code y}.  (This implies that {@code
		 * x.compareTo(y)} must throw an exception if and only if {@code
		 * y.compareTo(x)} throws an exception.)
		 *
		 * <p>The implementor must also ensure that the relation is transitive:
		 * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
		 * {@code x.compareTo(z) > 0}.
		 *
		 * <p>Finally, the implementor must ensure that {@code
		 * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
		 * == signum(y.compareTo(z))}, for all {@code z}.
		 *
		 * @param o the object to be compared.
		 * @return a negative integer, zero, or a positive integer as this object
		 * is less than, equal to, or greater than the specified object.
		 * @throws NullPointerException if the specified object is null
		 * @throws ClassCastException   if the specified object's type prevents it
		 *                              from being compared to this object.
		 * @apiNote It is strongly recommended, but <i>not</i> strictly required that
		 * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
		 * class that implements the {@code Comparable} interface and violates
		 * this condition should clearly indicate this fact.  The recommended
		 * language is "Note: this class has a natural ordering that is
		 * inconsistent with equals."
		 */
		@Override
		public int compareTo(PathWeights o)
		{
			return Double.compare(weight, o.weight);
		}
	}

	public static void main(String[] args)
	{
		WeightedGraph g = WeightedGraph.readWeightedGraph("weightedgraph.txt");
		g.printWeightedGraph();
		System.out.println(Arrays.toString(g.shortestPath("A", "F")));
	}
}
