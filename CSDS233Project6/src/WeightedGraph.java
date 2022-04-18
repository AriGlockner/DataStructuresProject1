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
	//TODO: Fix stack overflow error when path forms a circle
	public String[] shortestPath(String from, String to)
	{
		String[] path = BFS(from, to, "alphabetical");
		int weight = calculatePathWeight(path);


		return path;

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

		// If queue does not contain 2 or more possible paths, return an empty String array
		if (successfulPaths.isEmpty())
			return new String[0];
		return successfulPaths.remove().path;
		 */
	}

	private List<String[]> getPaths(String from, String to)
	{
		ArrayList<String[]> successfulPaths = new ArrayList<>();
		//successfulPaths.add(BFS(from, to, "alphabetical"));

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

		count++;
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
	}

	private void printPaths(PriorityQueue<PathWeights> successfulPaths)
	{
		System.out.println("\nPaths:");
		while (!successfulPaths.isEmpty())
			System.out.println(successfulPaths.remove());
		System.out.println();
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

	static class PathWeights implements Comparable<PathWeights>
	{
		private final String[] path;
		private final int weight;

		public PathWeights(String[] path, int weight)
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
			return -1 * Double.compare(weight, o.weight);
		}

		/**
		 * @return weight and path separated by a single space
		 */
		@Override
		public String toString()
		{
			return weight + " " + Arrays.toString(path);
		}
	}

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
