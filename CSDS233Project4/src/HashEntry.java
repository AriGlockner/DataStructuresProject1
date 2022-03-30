/**
 * This class represents an individual Hash for the HashTable class.
 * Each Hash contains a String key that is used for finding this specific hash in the hash table
 * and a value that is used for sorting within the HashTable.
 *
 * @author ari
 */
public class HashEntry implements Comparable<HashEntry>
{
	// The key is the index
	private final String key;
	// Value stored in object
	private int value;
	// Next hash entry in case of multiple hashes having the same key
	private HashEntry next;

	/**
	 * Construct a new HashEntry
	 *
	 * @param key   a string value that is also used to find the object in the HashTable
	 * @param value stored in object
	 */
	public HashEntry(String key, int value)
	{
		this.key = key;
		this.value = value;
	}

	/**
	 * @return key
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * @return value
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * @return next Hash Entry in linked list when multiple Hash Entries share the same location in the Hash Table
	 */
	public HashEntry getNext()
	{
		return next;
	}

	/**
	 * Method called when two or more HashEntry's share the same position in the HashTable
	 *
	 * @param newHashEntry HashEntry to be added to the back of the linked list
	 */
	public void setNext(HashEntry newHashEntry)
	{
		if (next == null)
			next = newHashEntry;
		else
			next.setNext(newHashEntry);
	}

	/**
	 * Sets the value
	 *
	 * @param value used for sorting purposes within the HashTable class
	 */
	public void setValue(int value)
	{
		this.value = value;
	}

	/**
	 * Helper method for WordStat class
	 *
	 * @return this Object without any items in the Linked List
	 */
	HashEntry get()
	{
		return new HashEntry(key, value);
	}

	/**
	 * @return "[key, value] " + any other objects in the LinkedList
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(key);
		sb.append(", ");
		sb.append(value);
		sb.append("] ");
		if (next != null)
			sb.append(next);

		return sb.toString();
	}

	/**
	 * Helper method for compareTo
	 *
	 * @param h other hashEntry
	 * @return 1, 0, -1 like compareTo using alphabetical order as the comparator
	 */
	private int compareKey(HashEntry h)
	{
		// Return if a character is different
		int len = Math.min(key.length(), h.key.length());
		for (int i = 0; i < len; i++)
			if (key.charAt(i) != h.key.charAt(i))
				return key.charAt(i) > h.getKey().charAt(i) ? -1 : 1;

		// Return based on length of Strings
		if (key.length() == h.getKey().length())
			return 0;
		return key.length() > h.getKey().length() ? -1 : 1;
	}

	/**
	 * Compares this object to another HashEntry
	 *
	 * @param h other hash entry
	 * @return this value compared to other value
	 */
	@Override
	public int compareTo(HashEntry h)
	{
		// Returns -1 or 1 based on value. If value is same, returns output from compareKey
		return (value != h.value) ? (value - h.value) / Math.abs(value - h.value) : compareKey(h);
	}

	public int compareWithoutKey(HashEntry h)
	{
		return Integer.compare(value, h.value);
	}

	/**
	 * @param o other object
	 * @return true if other object is a HashEntry and the keys are the same and the values are the same. Otherwise,
	 * returns false
	 */
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof HashEntry h))
			return false;
		return key.equals(h.key) && value == h.value;
	}
}
