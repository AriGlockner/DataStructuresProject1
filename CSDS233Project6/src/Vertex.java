import java.util.LinkedList;

/**
 * This class represents a vertex in the graph class. A vertex contains a String name,
 */
public class Vertex implements Comparable<Vertex>
{
	// ID belonging to this specific Vertex
	private final String name;
	private LinkedList<Edge> edges; // adjacency list
	private LinkedList<Vertex> parents;
	public boolean visited;
	//private boolean done;

	/**
	 * Initializes a new Vertex
	 * @param name of vertex
	 */
	public Vertex(String name)
	{
		this.name = name;
		edges = new LinkedList<>();
		parents = new LinkedList<>();
		visited = false;
	}

	/**
	 * Adds a new edge if possible
	 *
	 * @param destination other end of vertex
	 * @return true if successful, false otherwise
	 */
	public boolean addEdge(Vertex destination)
	{
		// Create Edge
		Edge newEdge = new Edge(this, destination);

		// Don't add edge and return false
		if (edges.contains(newEdge))
			return false;

		// If edge already exists, return false. Otherwise, add edge
		if (!destination.addParent(this))
			return false;

		// Add edge, set parent of destination to this, and return true
		edges.add(newEdge);
		return true;
	}

	/**
	 * Adds a new edge if possible
	 *
	 * @param destination other end of vertex
	 * @return true if successful, false otherwise
	 */
	public boolean addWeightedEdge(Vertex destination, int weight)
	{
		// Create Edge
		WeightedEdge newEdge = new WeightedEdge(this, destination, weight);

		// Don't add edge and return false
		if (edges.contains(newEdge))
			return false;

		// If edge already exists, return false. Otherwise, add edge
		if (!destination.addParent(this))
			return false;

		// Add edge, set parent of destination to this, and return true
		edges.add(newEdge);
		return true;
	}

	/**
	 * Removes this vertex's edge where this vertex is the start and the parameter v is the destination.
	 * @param v vertex destination
	 */
	public void removeEdge(Vertex v)
	{
		edges.remove(new Edge(this, v));
	}

	/**
	 *
	 * @param parent
	 * @return
	 */
	private boolean addParent(Vertex parent)
	{
		if (parents.contains(parent))
			return false;
		parents.add(parent);
		return true;
	}

	/**
	 * @return the list of vertices that have edges pointing towards this vertex
	 */
	public LinkedList<Vertex> getParents()
	{
		return parents;
	}

	/**
	 * @return the list of vertices that this vertex points towards
	 */
	public LinkedList<Vertex> getChildren()
	{
		LinkedList<Vertex> children = new LinkedList<>();
		for (Edge e : edges)
			children.add(e.getTo());
		return children;
	}

	/**
	 * @return the edges that this vertex has
	 */
	public LinkedList<Edge> getEdges()
	{
		return edges;
	}

	/**
	 * Only called from WeightedGraph class
	 *
	 * @param to destination of edge
	 * @return weighted edge from this vertex to the parameter to vertex
	 */
	WeightedEdge getWeightedEdge(Vertex to)
	{
		Edge e = new Edge(this, to);

		for (Edge edge : edges)
			if (e.equals(edge))
				if (edge instanceof WeightedEdge)
					return (WeightedEdge) edge;
				else
					return null;
		return null;
	}

	/**
	 * @return all weighted edges that this vertex contains
	 */
	public LinkedList<WeightedEdge> getWeightedEdges()
	{
		LinkedList<WeightedEdge> weightedEdges = new LinkedList<>();
		for (Edge e : edges)
			if (e instanceof WeightedEdge)
				weightedEdges.add((WeightedEdge) e);
		return weightedEdges;
	}


	/**
	 * @return name of this Vertex
	 */
	@Override
	public String toString()
	{
		return name;
	}

	/**
	 * Compares this object with the specified object for order.  Returns a
	 * negative integer, zero, or a positive integer as this object is less
	 * than, equal to, or greater than the specified object.
	 *
	 * <p>The implementor must ensure {@link Integer#signum
	 * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
	 * all {@code x} and {@code y}.  (This implies that {@code
	 * x.compareTo(y)} must throw an exception if and only if {@code
	 * y.compareTo(x)} throws an exception.)
	 *
	 * <p>The implementor must also ensure that the relation is transitive:
	 * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
	 * {@code x.compareTo(z) > 0}.
	 *
	 * <p>Finally, the implementor must ensure that {@code
	 * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
	 * == signum(y.compareTo(z))}, for all {@code z}.
	 *
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object
	 * is less than, equal to, or greater than the specified object.
	 * @throws NullPointerException if the specified object is null
	 * @throws ClassCastException   if the specified object's type prevents it
	 *                              from being compared to this object.
	 * @apiNote It is strongly recommended, but <i>not</i> strictly required that
	 * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
	 * class that implements the {@code Comparable} interface and violates
	 * this condition should clearly indicate this fact.  The recommended
	 * language is "Note: this class has a natural ordering that is
	 * inconsistent with equals."
	 */
	@Override
	public int compareTo(Vertex o)
	{
		return name.compareTo(o.name);
	}

	/**
	 * @param o other object
	 * @return true if other object is an instance of Vertex, and they share the same name, otherwise false
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Vertex v)
			return name.equals(v.name);
		return false;
	}
}
