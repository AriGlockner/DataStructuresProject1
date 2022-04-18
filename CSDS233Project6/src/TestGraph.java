import org.junit.*;

import java.util.Arrays;

public class TestGraph
{
	@Test
	public void testFile()
	{
		Graph graph = Graph.read("graph.txt");
		Assert.assertEquals("A B C D\nB A C\nC A B\nD A", graph.toString());
		graph.printGraph();

		System.out.println("\n");
		Assert.assertEquals("[B, A, C]", Arrays.toString(graph.DFS("B", "C", "alphabetical")));
		Assert.assertEquals("[B, C]", Arrays.toString(graph.BFS("B", "C", "alphabetical")));
		Assert.assertEquals("[]", Arrays.toString(graph.secondShortestPath("D", "C")));
		System.out.println(Arrays.toString(graph.secondShortestPath("B", "C")));
	}

	@Test
	public void testEmpty()
	{
		Graph graph = new Graph();
	}
}
