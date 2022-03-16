/**
 * This class represents an individual Hash for the HashTable class.
 * Each Hash contains a String key that is what String is stored in
 * the Hash and a value that is used for sorting within the HashTable.
 *
 * @author ari
 */
public class HashEntry implements Comparable<Integer>
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


	/**
	 * Compares this object with the specified object for order.  Returns a
	 * negative integer, zero, or a positive integer as this object is less
	 * than, equal to, or greater than the specified object.
	 *
	 * <p>The implementor must ensure {@link Integer#signum
	 * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
	 * all {@code x} and {@code y}.  (This implies that {@code
	 * x.compareTo(y)} must throw an exception if and only if {@code
	 * y.compareTo(x)} throws an exception.)
	 *
	 * <p>The implementor must also ensure that the relation is transitive:
	 * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
	 * {@code x.compareTo(z) > 0}.
	 *
	 * <p>Finally, the implementor must ensure that {@code
	 * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
	 * == signum(y.compareTo(z))}, for all {@code z}.
	 *
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object
	 * is less than, equal to, or greater than the specified object.
	 * @throws NullPointerException if the specified object is null
	 * @throws ClassCastException   if the specified object's type prevents it
	 *                              from being compared to this object.
	 * @apiNote It is strongly recommended, but <i>not</i> strictly required that
	 * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
	 * class that implements the {@code Comparable} interface and violates
	 * this condition should clearly indicate this fact.  The recommended
	 * language is "Note: this class has a natural ordering that is
	 * inconsistent with equals."
	 */
	@Override
	public int compareTo(Integer o)
	{
		if (value > o)
			return 1;
		if (value < o)
			return -1;
		return 0;
	}
}
