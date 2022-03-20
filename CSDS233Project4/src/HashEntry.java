/**
 * This class represents an individual Hash for the HashTable class.
 * Each Hash contains a String key that is used for finding this specific hash in the hash table
 * and a value that is used for sorting within the HashTable.
 *
 * @author ari
 */
public class HashEntry
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
}
