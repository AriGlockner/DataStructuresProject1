public class Node
{
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

	public String getName()
	{
		return name;
	}
}
