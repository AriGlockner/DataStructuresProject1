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

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Edge)
			return to.equals(((Edge) o).to) && from.equals(((Edge) o).from);
		return false;
	}

	@Override
	public String toString()
	{
		return to.toString();
	}
}
