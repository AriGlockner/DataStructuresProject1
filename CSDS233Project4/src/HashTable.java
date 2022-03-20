import java.lang.*;
import java.util.*;

public class HashTable
{
	// HashTable containing all HashEntries
	private HashEntry[] table;

	/**
	 * Initializes HashTable to a predetermined capacity
	 */
	public HashTable()
	{
		table = new HashEntry[8];
	}

	/**
	 * Initializes HashTable to a specific capacity
	 *
	 * @param capacity - size of initial table
	 */
	public HashTable(int capacity)
	{
		table = new HashEntry[capacity];
	}

	/**
	 * Adds value to
	 *
	 * @param key   mapping to index in HashTable
	 * @param value stored in HashEntry
	 */
	void put(String key, int value)
	{
		int position = Math.abs(key.hashCode() % table.length);

		HashEntry newHash = new HashEntry(key, value);

		// Add to HashTable
		if (table[position] == null)
			table[position] = new HashEntry(key, value);
			// Add to back of HashEntry Linked List
		else
			table[position].setNext(newHash);
	}

	/**
	 * @param key      what hash contains
	 * @param value    used for sorting purposes in the HastTable class
	 * @param hashCode adds new HashEntry at position determined by hashCode in parameter
	 */
	public void put(String key, int value, int hashCode)
	{
		int position = Math.abs(hashCode % table.length);

		HashEntry newHash = new HashEntry(key, value);

		// Add hash normally
		if (table[position] == null)
			table[position] = new HashEntry(key, value);
			// Add hash entry to back of linked list at that index
		else
			table[position].setNext(newHash);
	}

	/**
	 * Updates the value associated with key in the hash table. If key does not exist, it will be added to the table
	 *
	 * @param key   what to search for
	 * @param value the value to be updated
	 */
	public void update(String key, int value)
	{
		int position = Math.abs(key.hashCode() % table.length);

		if (table[position] == null)
			put(key, value);
		else
			table[position].setValue(value);
	}

	/**
	 * Returns the current value associated with the parameter key
	 *
	 * @param key what to search the HashTable for
	 * @return the value associated with key if key exists and -1 if it does not
	 */
	public int get(String key)
	{
		HashEntry hash = table[key.hashCode() % table.length];

		// if key does not exist in table, return -1
		if (hash == null)
			return -1;
		// Otherwise, return the hash's value
		return hash.getValue();
	}

	/**
	 * Should have the same function as the other get method, but uses the provided hash code. This is to allow direct
	 * testing of collisions.
	 *
	 * @param key      what to search the list of HashEntries with the same hashcode
	 * @param hashCode what to search the HashTable for
	 * @return the value associated with key if key exists and -1 if it does not
	 */
	public int get(String key, int hashCode)
	{
		// Search every HashEntry with the key of hashCode for a HashEntry with a specific key
		HashEntry ptr = table[Math.abs(hashCode % table.length)];

		while (ptr != null)
		{
			if (ptr.getKey().equals(key))
				return ptr.getValue();
			ptr = ptr.getNext();
		}

		// Otherwise, return -1
		return -1;
	}

	HashEntry getHashEntry(String key)
	{
		return table[Math.abs(key.hashCode() % table.length)];
	}

	/**
	 * Doubles the size of the table and fills in the values into the new table
	 */
	private void increaseTableSize()
	{
		table = Arrays.copyOf(table, table.length * 2);
	}

	/**
	 * @return every HashEntry in the HashTable
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for (HashEntry h : table)
			sb.append(h).append(" ");

		return sb.toString();
	}

	public static void main(String[] args)
	{
		HashTable t = new HashTable();

		t.put("foobar", 0);
		t.put("foobar", 0);
		t.put("foobar", 1);
		t.put("foo", 0);
		t.put("foo", 1);
		t.put("fdsjhgfjxy3yufuyc4", 983265);

		System.out.println(t);
	}
}
