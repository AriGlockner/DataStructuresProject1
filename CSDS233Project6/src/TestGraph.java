import org.junit.*;

import java.util.Arrays;

/**
 * Tests the graph class
 *
 * @author ari
 */
public class TestGraph
{

	/**
	 * Tests the Graph read method
	 */
	@Test
	public void testRead()
	{
		Graph graph = Graph.read("graph.txt");
		Assert.assertEquals("A B C D\nB A C\nC A B\nD A", graph.toString());
		graph.printGraph();
	}

	/**
	 * Tests the graph for all test cases except read
	 */
	@Test
	public void test()
	{
		Graph graph = new Graph();
		Assert.assertTrue(graph.addNodes(new String[] {"A", "B", "C", "D", "E", "F"}));
		Assert.assertTrue(graph.addEdges("A", new String[] {"B", "C", "D"}));
		Assert.assertTrue(graph.addEdge("B", "E"));
		Assert.assertTrue(graph.addEdge("C", "F"));
		Assert.assertTrue(graph.addEdge("D", "E"));
		Assert.assertTrue(graph.addEdge("E", "F"));
		Assert.assertTrue(graph.addEdges("F", new String[] {"A", "C", "E"}));
		Assert.assertFalse(graph.addNode("D"));
		Assert.assertFalse(graph.addEdge("C", "F"));
		// Print Graph
		graph.printGraph();
		System.out.println();

		// DFS
		Assert.assertEquals("[A, B, E, F]", Arrays.toString(graph.DFS("A", "F", "alphabetical")));
		Assert.assertEquals("[A, D, E, F]", Arrays.toString(graph.DFS("A", "F", "reverse")));
		Assert.assertEquals("[A, D]", Arrays.toString(graph.DFS("A", "D", "alphabetical")));
		Assert.assertEquals("[A, D]", Arrays.toString(graph.DFS("A", "D", "reverse")));
		Assert.assertEquals("[A, B, E, F, C]", Arrays.toString(graph.DFS("A", "C", "alphabetical")));
		Assert.assertEquals("[A, D, E, F, C]", Arrays.toString(graph.DFS("A", "C", "reverse")));

		// BFS
		Assert.assertEquals("[A, C, F]", Arrays.toString(graph.BFS("A", "F", "alphabetical")));
		Assert.assertEquals("[A, C, F]", Arrays.toString(graph.BFS("A", "F", "reverse")));
		Assert.assertEquals("[A, D]", Arrays.toString(graph.BFS("A", "D", "alphabetical")));
		Assert.assertEquals("[A, D]", Arrays.toString(graph.BFS("A", "D", "reverse")));
		Assert.assertEquals("[A, C]", Arrays.toString(graph.BFS("A", "C", "alphabetical")));
		Assert.assertEquals("[A, C]", Arrays.toString(graph.BFS("A", "C", "reverse")));

		// 2nd shortest path
		Assert.assertEquals("[A, B, E, F]", Arrays.toString(graph.secondShortestPath("A", "F")));
		Assert.assertEquals("[]", Arrays.toString(graph.secondShortestPath("B", "F")));
		Assert.assertEquals("[F, A, B, E]", Arrays.toString(graph.secondShortestPath("F", "E")));

		// Add Node/Nodes -> add nodes calls add node, so no need to test separately
		Assert.assertTrue(graph.addNodes(new String[] {"G", "H"}));
		Assert.assertFalse(graph.addNodes(new String[] {"G", "H", "I"}));

		// Add edges
		Assert.assertTrue(graph.addEdge("E", "G"));
		Assert.assertTrue(graph.addEdges("G", new String[] {"H", "I"}));
		Assert.assertTrue(graph.addEdge("H", "I"));
		Assert.assertTrue(graph.addEdges("I", new String[] {"B", "G"}));
		Assert.assertFalse(graph.addEdge("H", "I"));

		// Print graph
		graph.printGraph();
		System.out.println();

		// Remove
		Assert.assertTrue(graph.removeNodes(new String[] {"G", "H"}));
		Assert.assertFalse(graph.removeNodes(new String[] {"G", "H", "I"}));

		// Print graph
		graph.printGraph();
		System.out.println();

		// DFS
		Assert.assertEquals("[A, B, E, F]", Arrays.toString(graph.DFS("A", "F", "alphabetical")));
		Assert.assertEquals("[A, D, E, F]", Arrays.toString(graph.DFS("A", "F", "reverse")));

		// BFS
		Assert.assertEquals("[A, C, F]", Arrays.toString(graph.BFS("A", "F", "alphabetical")));
		Assert.assertEquals("[A, C, F]", Arrays.toString(graph.BFS("A", "F", "reverse")));

		// 2nd shortest path
		Assert.assertEquals("[A, B, E, F]", Arrays.toString(graph.secondShortestPath("A", "F")));
		Assert.assertEquals("[]", Arrays.toString(graph.secondShortestPath("B", "F")));
		Assert.assertEquals("[F, A, B, E]", Arrays.toString(graph.secondShortestPath("F", "E")));
	}
}
