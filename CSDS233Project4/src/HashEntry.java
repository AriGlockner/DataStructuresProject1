/**
 * This class represents an individual Hash for the HashTable class.
 * Each Hash contains a String key that is what String is stored in
 * the Hash and a value that is used for sorting within the HashTable.
 *
 * @author ari
 */
public class HashEntry
{
	// String element contains
	private final String key;
	// Value associated to String to be used when sorting in the HashTable class
	private int value;

	/**
	 * Construct a new HashEntry
	 *
	 * @param key   what hash contains
	 * @param value used for sorting purposes in the HastTable class
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
	 * Sets the value
	 *
	 * @param value used for sorting purposes within the HashTable class
	 */
	public void setValue(int value)
	{
		this.value = value;
	}
}
