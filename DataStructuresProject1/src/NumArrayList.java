import java.util.Arrays;

/**
 * NumArrayList is
 * 
 * @author ari
 */

public class NumArrayList implements NumList {
	private int capacity;
	private int size;
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
		this.capacity = capacity;
		size = 0;
		list = new double[0];
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
		return capacity;
	}

	/**
	 * Adds a new element to the end of the current list
	 * 
	 * @param value - the element to be added to the the list
	 */
	@Override
	public void add(double value) {
		// If size < capacity, increase size and add value to back
		if (size < capacity) {
			list = Arrays.copyOf(list, ++size);
			list[size-1] = value;
			
		} else {
			System.out.println("Unable to add " + value + " to the list due to the capacity being hit.");
		}

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
		if (i > size)
		{
			add(value);
			return;
		}

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
		// TODO Auto-generated method stub

	}

	/**
	 * @param value - the value to search for
	 * 
	 * @return true if if the list contains value
	 * @return false otherwise
	 */
	@Override
	public boolean contains(double value) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @returns the i-th element of the list (using 0 for the index of the first
	 *          element)
	 * @throws exception if the list has fewer than i+1 elements
	 */
	@Override
	public double lookup(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param otherList
	 * 
	 * @return true if this list equals the other list
	 * @return false otherwise
	 */
	@Override
	public boolean equals(NumList otherList) {
		if (this.capacity != otherList.capacity() || this.size != otherList.size())
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
		// TODO Auto-generated method stub

	}
	
	@Override
	public String toString() {
		if (size == 0)
			return "";
		
		String str = new String();
		for (double d : list)
			str += " " + d;
		
		return str.substring(1);
	}
	
	
	// For test Cases
	public static void main(String[] args) {
		NumArrayList nal = new NumArrayList(5);
		for (int i = 0; i < 10; i++)
			nal.add(i);
		System.out.println(nal);
	}
}
