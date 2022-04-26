import java.util.ArrayList;

public class Heap<T extends Comparable<T>>
{
	private ArrayList<T> items;

	public Heap()
	{
		items = new ArrayList<>();
	}

	public boolean insert(T item)
	{
		return false;
	}

	public T remove()
	{
		return null;
	}

	private void siftDown(int i)
	{
		T toSift = items.get(i);
		int parent = i;
		int child = 2 * i + 1;
		while (child < items.size())
		{
			if (child + 1 < items.size() && items.get(child).compareTo(items.get(child + 1)) < 0)
				child++;

			if (toSift.compareTo(items.get(child)) >= 0)
				break;

			items.set(parent, items.get(parent));
			items.set(child, toSift);
			parent = child;
			child = 2 * child + 1;
		}
		items.set(parent, toSift);
	}

	public T peek()
	{
		return items.get(0);
	}

	public boolean isEmpty()
	{
		return items.isEmpty();
	}
}
