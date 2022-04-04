/**
 * The HashTable class represents a table of HashEntries. The HashTable class uses a HashCode to get a constant lookup
 * in all situations except when two or more HashEntries share the same key.
 *
 * @author ari
 */
public class HashTable
{
	// HashTable containing all HashEntries
	private final HashEntry[] table;

	/**
	 * Initializes HashTable to a predetermined capacity of 1024
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
	 * Creates a new HashEntry from key and value and adds it into the HashTable
	 *
	 * @param key   mapping to index in HashTable
	 * @param value stored in HashEntry
	 */
	void put(String key, int value)
	{
		// Calculate position to insert in table
		int position = Math.abs(key.hashCode() % table.length);

		// Create a new HashEntry to add to the table
		HashEntry newHash = new HashEntry(key, value);

		// Add newHash to HashTable at position if empty
		if (table[position] == null)
			table[position] = new HashEntry(key, value);
			// Otherwise, add to back of HashEntry Linked List
		else
			table[position].setNext(newHash);
	}

	/**
	 * @param key      mapping to index in HashTable
	 * @param value    used for sorting purposes in the HastTable class
	 * @param hashCode adds new HashEntry at position determined by hashCode in parameter rather than by key
	 */
	public void put(String key, int value, int hashCode)
	{
		// Calculate position to insert in table
		int position = Math.abs(hashCode % table.length);

		// Create a new HashEntry to add to the table
		HashEntry newHash = new HashEntry(key, value);

		// Add newHash to HashTable at position if empty
		if (table[position] == null)
			table[position] = new HashEntry(key, value);
			// Otherwise, add to back of HashEntry Linked List
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
		// Calculate position to update in table
		int position = Math.abs(key.hashCode() % table.length);

		// Create pointer to first
		HashEntry ptr = table[position];
		while (ptr != null)
			// Update HashEntry
			if (ptr.getKey().equals(key))
			{
				ptr.setValue(value);
				return;
				// Iterate to next HashEntry in Linked List
			} else
				ptr = ptr.getNext();
		// If table does not contain item with key, call put method
		put(key, value);
	}

	/**
	 * Returns the current value associated with the parameter key
	 *
	 * @param key what to search the HashTable for
	 * @return the value associated with key if key exists and -1 if it does not
	 */
	public int get(String key)
	{
		// Get HashEntry at key
		HashEntry hash = table[Math.abs(key.hashCode()) % table.length];

		// If no HashEntry in table contains key's position in table return -1
		if (hash == null)
			return -1;

		// Search for HashEntry with specified key
		while (hash != null)
			// If HashTable contains item with key, return that item's value
			if (hash.getKey().equals(key))
				return hash.getValue();
			else
				// Go to next HashEntry in Linked List
				hash = hash.getNext();
		// Return -1 if no item contains key
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
		// Get HashEntry at the key of hashCode within the table
		HashEntry hash = table[Math.abs(hashCode) % table.length];

		// If no HashEntry in table contains key's position in table return -1
		if (hash == null)
			return -1;

		// Search for HashEntry with specified key
		while (hash != null)
			if (key.equals(hash.getKey()))
				// If HashTable contains item with key, return that item's value
				return hash.getValue();
			else
				// Go to next HashEntry in Linked List
				hash = hash.getNext();

		// Return -1 if not in HashTable
		return -1;
	}

	/**
	 * Helper method for WordStat class
	 *
	 * @return the table as an array
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
			{
				HashEntry ptr = h;
				while (ptr != null)
				{
					sb.append(ptr).append(" ");
					ptr = ptr.getNext();
				}
			}

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

		// Initialize HashTables
		HashTable table1 = new HashTable();
		HashTable table2 = new HashTable(7);
		HashTable table3 = new HashTable(3);

		String[] keys = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};

		// Test put(key, value, hashCode)
		for (int i = 0; i < keys.length; i++)
		{
			// put normally
			table1.put(keys[i], i * 2 + 1);
			table2.put(keys[i], i * 2 + 1);
			table3.put(keys[i], i * 2 + 1);
			// put with hashcode
			table1.put(keys[i], (i + 1) * 2, 60 + i);
			table2.put(keys[i], (i + 1) * 2, 60 + i);
			table3.put(keys[i], (i + 1) * 2, 60 + i);
		}

		System.out.println("Put:");
		System.out.println("Expected:\n[A, 2] [B, 4] [C, 6] [D, 8] [E, 10] [A, 1] [F, 12] [B, 3] [G, 14] [C, 5] [H, 16]" +
				" [D, 7] [I, 18] [E, 9] [J, 20] [F, 11] [K, 22] [G, 13] [H, 15] [I, 17] [J, 19] [K, 21]\nActual:\n"
				+ table1);
		System.out.println("Expected:\n[D, 8] [F, 11] [K, 22] [E, 10] [G, 13] [A, 1] [F, 12] [H, 15] [B, 3] [G, 14] " +
				"[I, 17] [A, 2] [C, 5] [H, 16] [J, 19] [B, 4] [D, 7] [I, 18] [K, 21] [C, 6] [E, 9] [J, 20]\nActual:\n"
				+ table2);
		System.out.println("Expected:\n[A, 2] [B, 3] [D, 8] [E, 9] [G, 14] [H, 15] [J, 20] [K, 21] [B, 4] [C, 5] " +
				"[E, 10] [F, 11] [H, 16] [I, 17] [K, 22] [A, 1] [C, 6] [D, 7] [F, 12] [G, 13] [I, 18] [J, 19]" +
				"\nActual:\n" + table3);

		System.out.println("\nget:");
		for (int i = 0; i < keys.length; i++)
		{
			System.out.println("Expected:\n" + ((i * 2) + 1) + " " + ((i * 2) + 1) + " " + ((i * 2) + 1) +
					"\nActual:\n" + table1.get(keys[i]) + " " + table2.get(keys[i]) + " " + table3.get(keys[i]));
			System.out.println("Expected:\n" + ((i + 1) * 2) + " " + ((i + 1) * 2) + " " + ((i + 1) * 2) +
					"\nActual:\n" + table1.get(keys[i], 60 + i) + " " + table2.get(keys[i], 60 + i) +
					" " + table3.get(keys[i], 60 + i) + " ");
		}
	}
}
