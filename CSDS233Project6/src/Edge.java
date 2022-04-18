/**
 * An edge connects one vertex to another vertex
 *
 * @author ari
 */
public class Edge
{
	public final Vertex from;
	public final Vertex to;

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
	 * @param o other Object
	 * @return true if o is an Edge and has the same from and to, otherwise returns false
	 */
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
