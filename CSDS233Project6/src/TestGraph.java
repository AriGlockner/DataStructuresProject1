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
		graph.addNodes(new String[]{"A", "B", "C", "D", "E", "F"});
		graph.addEdges("A", new String[] {"B", "C", "D"});
		graph.addEdge("B", "E");
		graph.addEdge("C", "F");
		graph.addEdge("D", "E");
		graph.addEdge("E", "F");
		graph.addEdges("F", new String[] {"A", "C", "E"});
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
