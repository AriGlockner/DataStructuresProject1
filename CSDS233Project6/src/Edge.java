public class Edge
{
	private final Vertex from;
	private final Vertex to;

	/**
	 * Initializes a new Edge
	 *
	 * @param from Start Vertex
	 * @param to   End Vertex
	 */
	public Edge(Vertex from, Vertex to)
	{
		this.from = from;
		this.to = to;
	}

	/**
	 * @return destination of this Edge
	 */
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

	/**
	 * @return Vertex To
	 */
	@Override
	public String toString()
	{
		return to.toString();
	}
}
