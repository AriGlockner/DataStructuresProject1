import java.lang.*;

/**
 * The HashTable class represents a table of HashEntries. The HashTable class uses a HashCode to get a constant lookup
 * in all situations except when two or more HashEntries share the same key.
 */
public class HashTable
{
	// HashTable containing all HashEntries
	private final HashEntry[] table;

	/**
	 * Initializes HashTable to a predetermined capacity
	 */
	public HashTable()
	{
		table = new HashEntry[1024];
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
		HashEntry hash = table[Math.abs(key.hashCode()) % table.length];

		// if key does not exist in table, return -1
		if (hash == null)
			return -1;
		// Otherwise, return the hash's value
		//return hash.getValue();
		while (hash != null)
		{
			if (hash.getKey().equals(key))
				return hash.getValue();
			hash = hash.getNext();
		}
		return -1;
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
		HashEntry hash = table[Math.abs(hashCode) % table.length];

		if (hash == null)
			return -1;

		while (hash != null)
			if (key.equals(hash.getKey()))
				return hash.getValue();
			else
				hash = hash.getNext();

		return -1;
	}

	/**
	 * Helper method for WordStat class
	 *
	 * @param key value to search for
	 * @return the HashEntry with the key
	 */
	HashEntry getHashEntry(String key)
	{
		return table[Math.abs(key.hashCode() % table.length)];
	}

	/**
	 * Helper method for WordStat class
	 * @return the table
	 */
	HashEntry[] getTable()
	{
		return table;
	}

	/**
	 * @return every HashEntry in the HashTable
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for (HashEntry h : table)
			if (h != null)
				sb.append(h);

		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * Demo
	 *
	 * @param args program arguments
	 */
	public static void main(String[] args)
	{
		System.out.println("Demo:\n");

		String[] strings = new String[] {"one", "ten", "five", "ten", "fifteen", "ten", "two", "twenty", "fifteen",
				"ten"};
		int[] values = new int[] {1, 10, 5, 314159, 15, 10, 2, 20, 15, 10};

		HashTable table1 = new HashTable();
		HashTable table2 = new HashTable(7);

		// Test put(key, value)
		for (int i = 0; i < strings.length; i++)
		{
			table1.put(strings[i], values[i]);
			table2.put(strings[i], values[i]);
		}

		System.out.println("\nput(key, value):");
		System.out.println("Expected:\n[ten, 10] [ten, 10] [ten, 10] [ten, 10] [five, 5] [twenty, 20] [two, 2] " +
				"[one, 1] [fifteen, 15] [fifteen, 15]\nActual:\n" + table1);
		System.out.println("Expected:\n[two, 2] [ten, 10] [ten, 10] [ten, 10] [ten, 10] [one, 1] [five, 5] " +
				"[fifteen, 15] [fifteen, 15] [twenty, 20]\nActual:\n" + table2);

		// Test put(key, value, hashCode)
		System.out.println("\nput(key, value, hashCode):");
		table1.put("hashCode=314159", 314159, 314159);
		table2.put("hashCode=314159", 314159, 314159);
		System.out.println("Expected:\n[ten, 10] [ten, 10] [ten, 10] [ten, 10] [five, 5] [hashCode=314159, 314159] " +
				"[twenty, 20] [two, 2] [one, 1] [fifteen, 15] [fifteen, 15]\nActual:\n" + table1);
		System.out.println("Expected:\n[two, 2] [ten, 10] [ten, 10] [ten, 10] [ten, 10] [one, 1] [five, 5] " +
				"[fifteen, 15] [fifteen, 15] [twenty, 20] [hashCode=314159, 314159]\nActual:\n" + table2);

		// Test update
		System.out.println("\nUpdate:");
		table1.update("two", 22);
		table2.update("two", 22);
		System.out.println("Expected:\n[ten, 10] [ten, 10] [ten, 10] [ten, 10] [five, 5] [hashCode=314159, 314159] " +
				"[twenty, 20] [two, 22] [one, 1] [fifteen, 15] [fifteen, 15]\nActual:\n" + table1);
		System.out.println("Expected:\n[two, 22] [ten, 10] [ten, 10] [ten, 10] [ten, 10] [one, 1] [five, 5] " +
				"[fifteen, 15] [fifteen, 15] [twenty, 20] [hashCode=314159, 314159]\nActual:\n" + table2);
		// test update not there -> add
		table1.update("MyPhoneNumber", 1234567890);
		table2.update("MyPhoneNumber", 1234567890);
		System.out.println("Expected:\n[ten, 10] [ten, 10] [ten, 10] [ten, 10] [five, 5] [MyPhoneNumber, 1234567890]" +
				" [hashCode=314159, 314159] [twenty, 20] [two, 22] [one, 1] [fifteen, 15] [fifteen, 15]\nActual:\n" +
				table1);
		System.out.println("Expected:\n[two, 22] [ten, 1234567890] [ten, 10] [ten, 10] [ten, 10] [one, 1] [five, 5] " +
				"[fifteen, 15] [fifteen, 15] [twenty, 20] [hashCode=314159, 314159]\nActual:\n" + table2);

		// Test get(key)
		System.out.println("\nget(key):");
		System.out.println("Expected:\n-1\nActual:\n" + table1.get("great value"));
		System.out.println("Expected:\n1234567890\nActual:\n" + table2.get("great value"));

		// Test get(key, hashCode)
		System.out.println("\nget(key, hashCode):");
		System.out.println("Expected:\n-1\nActual:\n" + table1.get("ten", 8));
		System.out.println("Expected:\n10\nActual:\n" + table1.get("ten", 17));
		System.out.println("Expected:\n1234567890\nActual:\n" + table2.get("ten", 8));
		System.out.println("Expected:\n-1\nActual:\n" + table2.get("ten", 17));
	}
}
