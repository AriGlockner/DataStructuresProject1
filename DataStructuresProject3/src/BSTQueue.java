import java.util.*;

public class BSTQueue<T extends Comparable<T>, V>
{
	BSTNode<T, V> front;
	BSTNode<T, V> back;
	ArrayList<BSTNode<T, V>> queue = new ArrayList(5);

	public BSTQueue()
	{
		front = back = null;
	}

	public void add(BSTNode<T, V> node)
	{
		if (queue.size() == 0 || queue.contains(node))
		{
			queue.add(node);
			return;
		}

		int front = 0;
		int back = queue.size() - 1;

		while (front < back)
		{
			int mid = (front + back) / 2;
			//if (queue)
		}
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
}
