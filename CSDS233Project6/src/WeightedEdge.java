/**
 * A WeightedEdge is an Edge that also has a weight associated with it
 *
 * @author ari
 */
public class WeightedEdge extends Edge implements Comparable<WeightedEdge>
{
	public final int weight;

	/**
	 * Initializes a new WeightedEdge, which is an edge that has a weight
	 *
	 * @param from   starting vertex
	 * @param to     ending vertex
	 * @param weight cost associated with transversal across the edge
	 */
	public WeightedEdge(Vertex from, Vertex to, int weight)
	{
		super(from, to);
		this.weight = weight;
	}

	/**
	 * @return weight and destination separated by a single space
	 */
	@Override
	public String toString()
	{
		return weight + " " + super.toString();
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
	public int compareTo(WeightedEdge o)
	{
		return weight - o.weight;
	}

	/**
	 * @param o other Object
	 * @return from and to are equal to each other
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof WeightedEdge)
			return to.equals(((WeightedEdge) o).to) && from.equals(((WeightedEdge) o).from);
		return false;
	}
}
