import java.util.*;

public class BSTQueue<T extends Comparable<T>, V>
{
	BSTNode<T, V> front;
	BSTNode<T, V> back;
	ArrayList<BSTNode<T, V>> queue = new ArrayList();

	public BSTQueue()
	{
		front = back = null;
	}

	public void add(BSTNode<T, V> node)
	{
		if (queue.contains(node))
			return;

		queue.add(node);
	}

	/*
	static class QueueNode<T, V>
	{
		T key;
		V value;
		QueueNode<T, V> next;

		public QueueNode(T key, V value)
		{
			this.key = key;
			this.value = value;
		}
	}
	 */
	public static void main(String[] args)
	{
		BSTQueue<Integer, Integer> queue = new BSTQueue<>();

	}
}
