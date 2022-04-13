import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WeightedGraph extends Graph
{
	public WeightedGraph(int maximum)
	{
		super(maximum);
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
				for (Vertex next : v.getChildren())
					sb.append(next).append(" ");
				// print everything out
				System.out.println(sb.substring(0, sb.length() - 1));
			}
		}
		
		 */
	}

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

		WeightedGraph graph = new WeightedGraph(lines.size());

		for (String s : lines)
		{
			// Split into nodes
			String[] nodes = s.split(" ");

			// Add nodes
			/*
			for (String n : nodes)
				graph.addNode(n);
			*/
			for (int i = 0; i < nodes.length; i++)
				if (i % 2 == 0)
					graph.addNode(nodes[i]);

			// Add edges
			//graph.addEdges(nodes[0], Arrays.copyOfRange(nodes, 1, nodes.length));
			for (int i = 2; i < nodes.length; i+=2)
			{
				graph.addWeightedEdge(nodes[0], nodes[i], Integer.parseInt(nodes[i - 1]));

			}
		}
		return graph;
	}

	public static void main(String[] args)
	{
		WeightedGraph g = WeightedGraph.readWeightedGraph("weightedgraph.txt");
		g.printWeightedGraph();
	}
}
