import java.util.Arrays;

/**
 * NumArrayList is
 * 
 * @author ari
 */

public class NumArrayList implements NumList {
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
		capacity = size = 0;
		list = new double[capacity];
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
		Arrays.fill(list, Double.NaN);
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
		// Set last value equal to Not a Number
		list[i - 1] = Double.NaN;

		// Decrease variable containing number of values
		size--;

		// Decrease list size as necessary
		if (size % capacity == 0)
			list = Arrays.copyOf(list, size);
	}

	/**
	 * @param value - the value to search for
	 * 
	 * @return true if if the list contains value
	 * @return false otherwise
	 */
	@Override
	public boolean contains(double value) {
		for (double d : list)
			if (d == value)
				return true;
		return false;
	}

	/**
	 * @returns the i-th element of the list (using 0 for the index of the first
	 *          element)
	 * @throws exception if the list has fewer than i+1 elements
	 */
	@Override
	public double lookup(int i) {
		return list[i];
	}

	/**
	 * @param otherList
	 * 
	 * @return true if this list equals the other list
	 * @return false otherwise
	 */
	@Override
	public boolean equals(NumList otherList) {
		// Checks if size and capacity are equal to each other
		if (this.size != otherList.size())
			return false;

		// Iterates through each element of each list to compare if they're different
		for (int i = 0; i < size; i++)
			if (list[i] != otherList.lookup(i))
				return false;

		return true;
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
	 * Overrides toString method from the Object class.
	 * 
	 * @return a String containing each element in the list seperated by a space
	 */
	@Override
	public String toString() {
		if (size == 0)
			return "";

		String str = new String();
		for (int i = 0; i < size; i++)
			if (list[i] == Double.NaN)
				return str.substring(1);
			else
				str += " " + list[i];

		return str.substring(1);
	}

	/**
	 * Helper method for add/insert methods that increases size of list by the
	 * capacity
	 */
	private void increaseListSize() {
		list = Arrays.copyOf(list, size + capacity);
		Arrays.fill(list, size, size + capacity, Double.NaN);
	}

	// For test Cases
	public static void main(String[] args) {
		NumArrayList nal = new NumArrayList(2);
		for (int i = 0; i < 10; i++)
			nal.add((double) i);
		/*
		 * //System.out.println(nal); for (int i = 0; i <= 18; i += 2) nal.insert(i, 100
		 * * i); //System.out.println(nal); nal.insert(100, 2000.0);
		 * System.out.println(nal); nal.insert(0, 0.0); nal.remove(0); nal.remove(4);
		 * for (int i = 0; i < 5; i++) nal.remove(nal.size - 1); nal.remove(nal.size);
		 */
		for (int i = 0; i < 10; i++)
			nal.add((double) i);
		System.out.println(nal);
		nal.removeDuplicates();
		System.out.println(nal);
	}
}
