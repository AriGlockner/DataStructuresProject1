/**
 *
 * NumList is an abstract interface that can be used to represent an ordered
 * sequence of double precision floating-point numbers.
 * 
 * @author ari
 */

public abstract interface NumList {

	/**
	 * This method returns the size of the list
	 * 
	 * @return size of the list
	 */
	public int size();

	/**
	 * Number of elements the array can currently hold
	 * 
	 * @return number of elements the array can currently hold
	 */
	public int capacity();

	/**
	 * Add a new element to the end of the current list
	 * 
	 * @param value
	 */
	public void add(double value);

	/**
	 * Inserts a new element before the i-th element of the list. If i > the number
	 * of elements in the list, method adds number to back of list
	 * 
	 * @param i
	 * @param value
	 */
	public void insert(int i, double value);

	/**
	 * Removes the i-th element of the list. Does nothing if i is greater than the
	 * number of elements in the list
	 * 
	 * @param i
	 */
	public void remove(int i);

	/**
	 * returns true if the list contains the value, false otherwise
	 * 
	 * @param value
	 * @return if the list contains the value specified in the parameter
	 */
	public boolean contains(double value);

	/**
	 * returns the element at i index in the list. Throwns an exception if i is out
	 * of bounds
	 * 
	 * @param i
	 * @return value specified at index i in list
	 */
	public double lookup(int i);

	/**
	 * returns if this list is equal to the other list
	 * 
	 * @param otherList
	 * @return if each element in each list is the same and the sizes are the same
	 */
	public boolean equals(NumList otherList);

	/**
	 * Removes any duplicates of any elements in the list while preserving the order
	 * of the first duplicate
	 * 
	 */
	public void removeDuplicates();

	/**
	 * Converts the contents of the list to a String
	 * 
	 * @return each element in the list seperated by a space
	 */
	@Override
	public String toString();
}
