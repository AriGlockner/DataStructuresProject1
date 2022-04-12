import java.util.Collections;
import java.util.LinkedList;

/**
 * This class represents a vertex in the graph class. A vertex contains a String name,
 */
public class Vertex
{
	// ID belonging to this specific Vertex
	private final String name;

	private LinkedList<Edge> edges; // adjacency list
	private LinkedList<Vertex> parents;

	private boolean encountered;
	private boolean done;
	//private Vertex parents;

	/**
	 * Initializes a new Vertex
	 * @param name of vertex
	 */
	public Vertex(String name)
	{
		this.name = name;
		edges = new LinkedList<>();
		parents = new LinkedList<>();
	}

	/**
	 * @return name
	 */
	public String getName()
	{
		return name;
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

	public void removeEdge(Vertex v)
	{
		edges.remove(v);
	}

	private boolean addParent(Vertex parent)
	{
		if (parents.contains(parent))
			return false;
		parents.add(parent);
		return true;
	}

	public LinkedList<Vertex> getParents()
	{
		return parents;
	}

	public LinkedList<Edge> getEdges()
	{
		return edges;
	}

	@Override
	public String toString()
	{
		return name;
	}

	/**
	 * @param o other object
	 * @return true if other object is an instance of Vertex, and they share the same name, otherwise false
	 *
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Vertex v)
			return name.equals(v.name);
		return false;
	}
	*/
}
