public class BSTNode<T extends Comparable<T>, V> implements Comparable<T>
{
	private T key;
	private V value;
	private BSTNode<T, V> left;
	private BSTNode<T, V> right;

	public BSTNode(T key, V value)
	{
		this.key = key;
		this.value = value;
		left = right = null;
	}

	public V getValue()
	{
		return value;
	}

	public void setValue(V value)
	{
		this.value = value;
	}

	public BSTNode<T, V> getLeft()
	{
		return left;
	}

	public void setLeft(BSTNode<T, V> left)
	{
		this.left = left;
	}

	public BSTNode<T, V> getRight()
	{
		return right;
	}

	public void setRight(BSTNode<T, V> right)
	{
		this.right = right;
	}

	public void setKey(T key)
	{
		this.key = key;
	}

	@Override
	public int compareTo(T o)
	{
		return (key.compareTo(o));
	}
}
