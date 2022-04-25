import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyArrayList<T extends Comparable<T>>
{
	private T[] array;
	int numElements;

	public MyArrayList()
	{
		//(T[]) new ArrayList<>().toArray(new Object[0]);
		//array = (T[]) Array.newInstance(array.getClass(), 16); //(T[]) Array.newInstance(array.getClass(), 16); //(T[]) new Object[16];
		numElements = 0;
	}

	public MyArrayList(int initialCapacity)
	{
		array = (T[]) new Object[initialCapacity];
		numElements = 0;
	}

	public MyArrayList(T[] elements)
	{
		array = elements;
		numElements = elements.length;
	}

	public boolean isEmpty()
	{
		return numElements == 0;
	}

	public int size()
	{
		return numElements;
	}

	public int capacity()
	{
		return array.length;
	}

	void add(T element)
	{
		// Array is currently empty
		if (isEmpty())
		{
			array[numElements++] = element;
			return;
		}

		// Search for index to insert item
		int front = 0;
		int back = numElements;
		int mid = (front + back / 2);

		while (front <= mid && mid <= back)
		{
			T current = array[mid];
			int diff = element.compareTo(array[mid]);

			if (diff == 0)
			{

				break;
			} else if (diff > 0)
			{
				front = mid;
			} else
			{
				back = mid;
			}
			mid = (front + back) / 2;
		}


		// Increase Capacity if necessary
		if (numElements == array.length)
		{

		}
	}

	void remove(T element)
	{

	}

	T search(int index)
	{
		return array[index];
	}

	boolean contains(T element)
	{
		return false;
	}

	@Override
	public String toString()
	{
		if (numElements == 0)
			return "";

		StringBuilder sb = new StringBuilder();

		for (T value : array)
			sb.append(value).append(" ");

		return sb.substring(0, sb.length() - 1);
	}

	public static void main(String[] args)
	{
		MyArrayList<Integer> array = new MyArrayList<>();
		array.add(5);
		array.add(10);
	}
}
