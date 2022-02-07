import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * NumList is an abstract interface that can be used to represent an ordered
 * sequence of double precision floating-point numbers.
 * 
 * @author ari
 */

public abstract interface NumList extends Iterable<Double> {

	/**
	 * This method returns the size of the list
	 * 
	 * @return size of the list
	 */
	int size();

	/**
	 * Number of elements the array can currently hold
	 * 
	 * @return number of elements the array can currently hold
	 */
	int capacity();

	/**
	 * Add a new element to the end of the current list
	 * 
	 * @param value
	 */
	void add(double value);

	/**
	 * Inserts a new element before the i-th element of the list. If i > the number
	 * of elements in the list, method adds number to back of list
	 * 
	 * @param i
	 * @param value
	 */
	void insert(int i, double value);

	/**
	 * Removes the i-th element of the list. Does nothing if i is greater than the
	 * number of elements in the list
	 * 
	 * @param i
	 */
	void remove(int i);

	/**
	 * returns true if the list contains the value, false otherwise
	 * 
	 * @param value
	 * @return if the list contains the value specified in the parameter
	 */
	default boolean contains(double value) {
		for (double d : this)
			if (d == value)
				return true;
		
		return false;
	}

	/**
	 * returns the element at i index in the list. Throwns an exception if i is out
	 * of bounds
	 * 
	 * @param i
	 * @return value specified at index i in list
	 */
	default double lookup(int i) {
		for (double d : this)
			if (i-- == 0)
				return d;
		throw new NoSuchElementException("The index is out of bounds of the list");
	}

	/**
	 * returns if this list is equal to the other list
	 * 
	 * @param otherList
	 * @return if each element in each list is the same and the sizes are the same
	 */
	default boolean equals(NumList otherList) {
		return size() == otherList.size() && toString().equals(otherList.toString());
	}

	/**
	 * Removes any duplicates of any elements in the list while preserving the order
	 * of the first duplicate
	 * 
	 */
	void removeDuplicates();

	/**
	 * Converts the contents of the list to a String
	 * 
	 * @return each element in the list seperated by a space
	 */
	String toString();

	/**
	 * @return true if the list is in increasing sorted order
	 */
	default boolean isSorted() {
		double prior = Double.NaN;
		for (double d : this)
			if (prior != Double.NaN && d < prior)
				return false;
			else
				prior = d;
		
		return true;
	}

	/*
	 * Sorts the list
	 */
	void sort();

	/**
	 * Creates a new NumList that is
	 * 
	 * @param list1
	 * @param list2
	 * @return NumList list1 and list2 without duplicates
	 */
	default NumList union(NumList list1, NumList list2) {
		// If both lists are sorted add each element from list 2 to list 1 in a sorted
		// order
		if (list1.isSorted() && list2.isSorted())
			for (double d : list2)
				list1.sortedInsert(d);

		// Otherwise add list2 to the back of list1
		else
			for (double d : list2)
				list1.add(d);

		return list1;
	}

	/**
	 * if list is sorted, adds value to list in sorted position otherwise adds
	 * element to back
	 * 
	 * @param value
	 */
	void sortedInsert(double value);
	
	/**
	 * Reverses each element in the list
	 */
	void reverse();
	
	/**
	 * @return a new array iterator for this list
	 */
	Iterator<Double> iterator();
}
