public class WeightedEdge extends Edge
{
	private double cost;

	public WeightedEdge(Vertex from, Vertex to, double cost)
	{
		super(from, to);
		this.cost = cost;
	}

	public double getCost()
	{
		return cost;
	}
}
