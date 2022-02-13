import java.util.Arrays;
import java.util.Iterator;

/**
 * The NumArrayList class represents an arraylist of type double. It is
 * initialized with a capcity to increase by so when the list needs to be
 * increased in size, it does so by that amount for efficiency purposes. The
 * NumArrayList class implements the NumList and Iterable interfaces
 * 
 * @author ari
 */

public class NumArrayList implements NumList, Iterable<Double> {
	// Number to adjust size by when resizing is necessary
	private int capacity;
	// Number of values in the list
	private int size;
	// List containing values stored
	private double[] list;

	/**
	 * Initializes an empty NumArrayList of capacity 0
	 */
	public NumArrayList() {
		capacity = 1;
		size = 0;
		//capacity = size = 0;
		list = new double[] { 0.0 };
	}

	/**
	 * Initializes an empty NumArrayList of capacity provided in the parameter
	 * 
	 * @param capacity
	 */
	public NumArrayList(int capacity) {
		size = 0;
		this.capacity = capacity;
		list = new double[capacity];
		Arrays.fill(list, 0.0);
	}

	/**
	 * Returns the number of elements in the sequence
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the number of elements the array can currently hold
	 */
	@Override
	public int capacity() {
		return list.length;
	}

	/**
	 * Adds a new element to the end of the current list
	 * 
	 * @param value - the element to be added to the the list
	 */
	@Override
	public void add(double value) {
		// If size has reached capacity, increase size
		if (size % capacity == 0)
			increaseListSize();

		// Add value to back of list and increase size
		list[size++] = value;
	}

	/**
	 * Inserts a new element before the i-th element (using 0 for the index of the
	 * first element). Thus, the inserted element becomes the i-th element. For the
	 * case when the list has i or fewer elements, insert the new element at the end
	 * of the list.
	 * 
	 * @param i     - index for the element to be inserted
	 * @param value - the element to be added to the list
	 */
	@Override
	public void insert(int i, double value) {
		if (i > size) {
			add(value);
			return;
		}

		if (size % capacity == 0)
			increaseListSize();

		double[] postIValues = Arrays.copyOfRange(list, i, size);
		list[i] = value;
		for (double d : postIValues) {
			i++;
			list[i] = d;
		}
		size++;
	}

	/**
	 * Removes the i-th element of the list (using 0 for the index of the first
	 * element). For the case when the list has fewer elements, do nothing since the
	 * element to be removed does not exist.
	 * 
	 * @param i - the index to remove the element from
	 */
	@Override
	public void remove(int i) {
		// List is Empty or i is bigger than the size
		if (size == 0 || size < 0 || size <= i)
			return;
		// List contains 1 value
		if (size == 1) {
			list = new double[--size];
			return;
		}

		// Iterate through list for each spot after i to replace past value with current
		// value
		i++;
		while (i < size) {
			list[i - 1] = list[i];
			i++;
		}
		// Set last value equal to 0.0
		list[i - 1] = 0.0;

		// Decrease variable containing number of values
		size--;

		// Decrease list size as necessary
		if (size % capacity == 0)
			list = Arrays.copyOf(list, size);
	}

	/**
	 * @param value - the value to search for
	 * 
	 * @return true if if the list contains value, otherwise returns false
	 */
	@Override
	public boolean contains(double value) {
		for (double d : list)
			if (d == value)
				return true;
		return false;
	}

	/**
	 * Overrides lookup method in NumList because this implementation is faster for
	 * this type of list
	 * 
	 * @returns the i-th element of the list (using 0 for the index of the first
	 *          element)
	 * @throws exception if the list has fewer than i+1 elements
	 */
	@Override
	public double lookup(int i) {
		return list[i];
	}

	/**
	 * Removes duplicates of any elements in the list, preserving the list order and
	 * the position of the first of the duplicates. For example, removing the
	 * duplicates of the list [1, 2, 3, 1, 2, 4] should result in [1, 2, 3, 4].
	 */
	@Override
	public void removeDuplicates() {
		if (size < 2)
			return;

		// Iterate from back to front to check if elements are duplicates
		for (int i = size - 1; i > 0; i--)
			// Iterate through prior elements to see if current element is a repeated
			// element
			for (int j = i - 1; j >= 0; j--)
				// If element is a duplicate, removes that element
				if (list[i] == list[j]) {
					remove(i);
					break;
				}

	}

	/**
	 * Specified in NumList Interface, but method Overrides toString method from the
	 * Object class.
	 * 
	 * @return a String containing each element in the list seperated by a space
	 */
	@Override
	public String toString() {
		// return "" if list contains 0 elements
		if (size == 0)
			return "";

		// otherwise return each element seperated by a space
		// StringBuilder is faster than "" += ""
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < size; i++)
			sb.append(" " + list[i]);

		return sb.substring(1);
	}

	/**
	 * Helper method for add/insert methods that increases size of list by the
	 * capacity as needed
	 */
	private void increaseListSize() {
		list = Arrays.copyOf(list, size + capacity);
		Arrays.fill(list, size, size + capacity, 0.0);
	}

	/**
	 * if list is sorted, adds value to list in sorted position otherwise
	 * addselement into the list at an unspecified location. List won't be sorted if
	 * itwasn't sorted prior to method call
	 */
	@Override
	public void sortedInsert(double value) {
		for (int i = 0; i < size; i++)
			if (lookup(i) >= value) {
				insert(i, value);
				return;
			}
		add(value);
	}

	/**
	 * Reverses each element in the list
	 */
	@Override
	public void reverse() {
		for (int i = 0; i < size / 2; i++) {
			double foo = lookup(i);
			list[i] = lookup(size - i - 1);
			list[size - i - 1] = foo;
		}
	}

	/**
	 * @return a new array iterator for this list
	 */
	@Override
	public Iterator<Double> iterator() {
		return Arrays.stream(list).iterator();
	}

	/**
	 * Demonstration
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Create list
		NumList list = new NumArrayList(2);

		// Add elements to back of list
		for (int i = 1; i <= 10; i++)
			list.insert(100, i);
		System.out.println("List should print:\t1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0");
		System.out.println("List:\t\t\t" + list);
		System.out.println("List should be sorted (expected true):\t" + list.isSorted() + "\n");

		// Reverse List
		list.reverse();
		System.out.println("Reverse List");
		System.out.println("List should print: \t10.0 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0 1.0");
		System.out.println("The list should no longer be sorted (Expected false): " + list.isSorted() + "\n");

		// Reverse List to return to prior state
		list.reverse();
		System.out.println("Reversed list again\nList should print:\t1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0");
		System.out.println("List:\t\t\t" + list + "\n");

		// Contains
		System.out.println("Lookup/Contains:");
		System.out.println("List should not contain 3.14159 (expected false):\t" + list.contains(3.14159));
		System.out.println("List should result in 10.0 from lookup:\t" + list.lookup(list.size() - 1) + "\n");

		// Insert
		System.out.println("Inserting 3.14159 at the front, middle, and back of the list");
		list.insert(0, 3.14159);
		list.insert(list.size() / 2, 3.14159);
		list.insert(list.size() * 100, 3.14159);
		System.out.println("List should print:\t3.14159 1.0 2.0 3.0 4.0 3.14159 5.0 6.0 7.0 8.0 9.0 10.0 3.14159");
		System.out.println("List:\t\t\t" + list + "\n");

		// Contains
		System.out.println(
				"List should now contain the value 3.14159 (expected true):\t" + list.contains(3.14159) + "\n");

		// Remove
		System.out.println("Removing Duplicates:");
		list.removeDuplicates();
		System.out.println("List should now contain:\t3.14159 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0");
		System.out.println("List contains:\t\t\t" + list + "\n");
		System.out.println("Removing in front should result in:\t1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0");
		System.out.println("List contains:\t\t\t" + list + "\n");
	}
}
