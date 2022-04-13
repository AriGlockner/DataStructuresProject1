public class WeightedEdge extends Edge
{
	private final int weight;

	public WeightedEdge(Vertex from, Vertex to, int weight)
	{
		super(from, to);
		this.weight = weight;
	}

	public int getWeight()
	{
		return weight;
	}

	@Override
	public String toString()
	{
		return "" + weight;
		//return super.toString() + " " + weight;
	}
}
