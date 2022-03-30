import java.util.ArrayList;

/**
 * The MyHeap class represents a max heap of type HashEntry
 */
public class Heap
{
	private ArrayList<HashEntry> items;

	/**
	 * Initializes a new Max Heap
	 */
	public Heap()
	{
		items = new ArrayList<>();
	}

	/**
	 * @return the number of items in the heap
	 */
	public int size()
	{
		return items.size();
	}

	/**
	 * Inserts item into the heap
	 *
	 * @param item to be inserted into the Heap
	 */
	public void insert(HashEntry item)
	{
		items.add(new HashEntry(item.getKey(), item.getValue()));
		siftUp();
	}

	public void update(HashEntry item)
	{
		for (HashEntry hashEntry : items)
			if (hashEntry.equals(item))
			{
				hashEntry.setValue(hashEntry.getValue() + 1);
				return;
			}
		insert(item);
	}

	/**
	 * Deletes and Returns the maximum value in the heap
	 *
	 * @return the max value in the heap
	 */
	public HashEntry delete()
	{
		if (items.size() == 0)
			return null;
		if (items.size() == 1)
			return items.remove(0);

		HashEntry placeholder = items.get(0);
		items.set(0, items.remove(items.size() - 1));
		siftDown();
		return placeholder;
	}

	/**
	 * Helper method for the insert method. Rearranges items in the heap after insertion.
	 */
	private void siftUp()
	{
		int k = items.size() - 1;

		while (k > 0)
		{
			int p = (k - 1) / 2;
			HashEntry item = items.get(k);
			HashEntry parent = items.get(p);

			if (item.compareTo(parent) > 0)
			{
				// swap
				items.set(k, parent);
				items.set(p, item);

				// move up one level
				k = p;
			} else
				return;
		}
	}

	/**
	 * Helper method for the delete method. Rearranges items in the heap after deletion.
	 */
	private void siftDown()
	{
		int k = 0;
		// l = 2 * k + 1, which is always 1
		int l = 1;

		while (l < items.size())
		{
			int max = l;
			int r = l + 1;

			if (r < items.size())
			{
				// there is a right child
				if (items.get(r).compareTo(items.get(l)) > 0)
					max++;
			}
			if (items.get(k).compareTo(items.get(max)) < 0)
			{
				// switch
				HashEntry temp = items.get(k);
				items.set(k, items.get(max));
				items.set(max, temp);
				k = max;
				l = 2 * k + 1;
			} else
				return;
		}
	}

	/**
	 * Removes all elements from the heap without returning any of them
	 */
	public void clearAll()
	{
		items = new ArrayList<>();
	}

	/**
	 * @return true if heap contains 0 items. Otherwise, returns false
	 */
	public boolean isEmpty()
	{
		return items.isEmpty();
	}
}
