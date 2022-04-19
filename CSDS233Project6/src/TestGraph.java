import org.junit.*;

import java.io.PrintStream;
import java.util.Arrays;

public class TestGraph
{


	@Test
	public void testFile()
	{
		Graph graph = Graph.read("graph.txt");
		Assert.assertEquals("A B C D\nB A C\nC A B\nD A", graph.toString());
		graph.printGraph();

		//String s = "A B C D\nB A C\nC A B\nD A";

		//Assert.assertEquals("<A> <B> C D>\nB A C\nC A B\nD A", ps); //graph.printGraph());
		Assert.assertEquals("[B, A, C]", Arrays.toString(graph.DFS("B", "C", "alphabetical")));
		Assert.assertEquals("[B, C]", Arrays.toString(graph.BFS("B", "C", "alphabetical")));
		Assert.assertEquals("[]", Arrays.toString(graph.secondShortestPath("D", "C")));
		System.out.println(Arrays.toString(graph.secondShortestPath("B", "C")));
	}

	@Test
	public void testEmpty()
	{
		Graph graph = new Graph();
		Assert.assertTrue(graph.addNodes(new String[]{"A", "B", "C", "D", "E", "F"}));
		Assert.assertTrue(graph.addEdges("A", new String[] {"B", "C", "D"}));
		Assert.assertTrue(graph.addEdge("B", "E"));
		Assert.assertTrue(graph.addEdge("C", "F"));
		Assert.assertTrue(graph.addEdge("D", "E"));
		Assert.assertTrue(graph.addEdge("E", "F"));
		Assert.assertTrue(graph.addEdges("F", new String[] {"A", "C", "E"}));
		Assert.assertFalse(graph.addNode("D"));
		Assert.assertFalse(graph.addEdge("C", "F"));
		graph.printGraph();
		//TODO: Fix. BFS and DFS should not produce the same result
		Assert.assertEquals("[A, B, E, F]", Arrays.toString(graph.BFS("A", "F", "alphabetical")));
		Assert.assertEquals("[A, D, E, F]", Arrays.toString(graph.BFS("A", "F", "reverse")));
		Assert.assertEquals("[A, B, E, F]", Arrays.toString(graph.DFS("A", "F", "alphabetical")));
		Assert.assertEquals("[A, D, E, F]", Arrays.toString(graph.DFS("A", "F", "reverse")));
		System.out.println(Arrays.toString(graph.DFS("A", "F", "alphabetical")));
		System.out.println(Arrays.toString(graph.DFS("A", "F", "reverse")));
	}
}
