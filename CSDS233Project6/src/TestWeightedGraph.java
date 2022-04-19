import org.junit.*;

import java.util.Arrays;

public class TestWeightedGraph
{
	@Test
	public void testFile()
	{
		WeightedGraph graph = WeightedGraph.readWeightedGraph("weightedgraph.txt");
		//graph.printGraph();
		//System.out.println();
		graph.printWeightedGraph();
		Assert.assertEquals("A 2 B 1 D\nB 3 D 10 E\nC 4 A 5 F\nD 2 C 2 E 8 F 4 G\nE 6 G\nF\nG 1 F", graph.toString());
	}

	@Test
	public void testRealWorldExample()
	{
		WeightedGraph graph = new WeightedGraph();

		// Add cities
		Assert.assertTrue(graph.addNodes(new String[] {"Los Angeles", "San Francisco", "Seattle", "Cleveland",
				"Chicago", "New York", "Washington DC", "Boston", "Miami", "Dallas", "Houston", "Denver", "Atlanta"}));

		// Add weighted edges
		Assert.assertTrue(graph.addWeightedEdges("Los Angeles",
				new String[] {"San Francisco", "Denver"},
				new int[] {323, 878}));
		Assert.assertTrue(graph.addWeightedEdges("San Francisco",
				new String[] {"Los Angeles", "Seattle"},
				new int[] {323, 755}));
		Assert.assertTrue(graph.addWeightedEdges("Seattle",
				new String[] {"San Francisco", "Denver"},
				new int[] {755, 1099}));
		Assert.assertTrue(graph.addWeightedEdges("Cleveland",
				new String[] {"Chicago", "New York", "Washington DC", "Atlanta"},
				new int[] {306, 419, 341, 598}));
		Assert.assertTrue(graph.addWeightedEdges("Chicago",
				new String[] {"Denver", "Dallas", "Cleveland", "Atlanta"},
				new int[] {826, 806, 306, 632}));
		Assert.assertTrue(graph.addWeightedEdges("New York",
				new String[] {"Washington DC", "Cleveland", "Boston"},
				new int[] {217, 419, 210}));
		Assert.assertTrue(graph.addWeightedEdges("Washington DC",
				new String[] {"Atlanta", "Cleveland", "New York"},
				new int[] {585, 341, 217}));
		Assert.assertTrue(graph.addWeightedEdge("Boston", "New York", 210)); // add weight
		Assert.assertTrue(graph.addWeightedEdge("Miami", "Atlanta", 549));
		Assert.assertTrue(graph.addWeightedEdges("Dallas",
				new String[] {"Denver", "Houston", "Chicago"},
				new int[] {696, 199, 813}));
		Assert.assertTrue(graph.addWeightedEdges("Houston",
				new String[] {"Dallas", "Atlanta"},
				new int[] {199, 665}));
		Assert.assertTrue(graph.addWeightedEdges("Denver",
				new String[] {"Seattle", "Los Angeles", "Dallas", "Chicago"},
				new int[] {1099, 878, 696, 826}));
		Assert.assertTrue(graph.addWeightedEdges("Atlanta",
				new String[] {"Miami", "Houston", "Washington DC"},
				new int[] {549, 665, 585}));

		//
		graph.printWeightedGraph();
		Assert.assertEquals("""
				Los Angeles 323 San Francisco 878 Denver
				San Francisco 323 Los Angeles 755 Seattle
				Seattle 755 San Francisco 1099 Denver
				Cleveland 306 Chicago 419 New York 341 Washington DC 598 Atlanta
				Chicago 826 Denver 806 Dallas 306 Cleveland 632 Atlanta
				New York 217 Washington DC 419 Cleveland 210 Boston
				Washington DC 585 Atlanta 341 Cleveland 217 New York
				Boston 210 New York
				Miami 549 Atlanta
				Dallas 696 Denver 199 Houston 813 Chicago
				Houston 199 Dallas 665 Atlanta
				Denver 1099 Seattle 878 Los Angeles 696 Dallas 826 Chicago
				Atlanta 549 Miami 665 Houston 585 Washington DC""", graph.toString());
		System.out.println();

		// Shortest Path
		Assert.assertEquals("[Seattle, Denver, Chicago, Atlanta, Miami]",
				Arrays.toString(graph.shortestPath("Seattle", "Miami")));
		Assert.assertEquals("[Boston, Cleveland, Chicago, Denver, Los Angeles]",
				Arrays.toString(graph.shortestPath("Boston", "Los Angeles")));
		Assert.assertEquals("[San Francisco, Denver, Chicago, Cleveland, Washington DC]",
				Arrays.toString(graph.shortestPath("San Francisco", "Washington DC")));
		Assert.assertEquals("[Cleveland, Atlanta, Houston]",
				Arrays.toString(graph.shortestPath("Cleveland", "Houston")));
		Assert.assertEquals("[Denver, Chicago, Atlanta]",
				Arrays.toString(graph.shortestPath("Denver", "Atlanta")));

		// Second-Shortest Path
		// TODO: Write method, then implement these tests:
		/*
		System.out.println(Arrays.toString(graph.secondShortestPath("Seattle", "Miami")));
		System.out.println(Arrays.toString(graph.secondShortestPath("Boston", "Los Angeles")));
		System.out.println(Arrays.toString(graph.secondShortestPath("San Francisco", "Washington DC")));
		System.out.println(Arrays.toString(graph.secondShortestPath("Cleveland", "Houston")));
		System.out.println(Arrays.toString(graph.secondShortestPath("Denver", "Atlanta")));
		 */

		// BFS

		// DFS
	}
}
