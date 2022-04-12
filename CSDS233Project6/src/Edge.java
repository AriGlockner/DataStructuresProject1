public class Edge {
	//private int endNode;
	//private Edge next; // Matrix Representation
	private Vertex from;
	private Vertex to;

	public Edge(Vertex from, Vertex to)
	{
		this.from = from;
		this.to = to;
	}

	public Vertex getTo()
	{
		return to;
	}
}
