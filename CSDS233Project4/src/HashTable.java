import java.lang.*;
import java.util.ArrayList;

public class HashTable
{
	private ArrayList<HashEntry> table;

	/**
	 * Initializes HashTable
	 */
	public HashTable()
	{
		table = new ArrayList<>();
	}

	/**
	 * Initializes HashTable to a set capacity
	 *
	 * @param capacity - size of initial table
	 */
	public HashTable(int capacity)
	{
		table = new ArrayList<>(capacity);
	}

	/**
	 * Adds value to
	 *
	 * @param key   what hash contains
	 * @param value used for sorting purposes in the HastTable class
	 */
	void put(String key, int value)
	{
		put(key, value, Math.abs(key.hashCode()));
	}

	/**
	 * @param key      what hash contains
	 * @param value    used for sorting purposes in the HastTable class
	 * @param hashCode
	 */
	void put(String key, int value, int hashCode)
	{
		if (table.size() == 0)
		{
			table.add(new HashEntry(key, value));
			return;
		}

		HashEntry hash = new HashEntry(key, value);

		// Binary insert

	}

	public void update(String key, int value)
	{

	}


	int get(String key)
	{
		return 0;
	}

	int get(String key, int hashCode)
	{
		return 0;
	}

	private HashEntry search()
	{
		return null;
	}

}
