import java.util.Iterator;
import java.util.Random;

/**
 * The NumLinkedList class represents a linked list of type double. The
 * NuLinkedList class implements the NumList and Iterable interfaces
 * 
 * @author ari
 */

public class NumLinkedList implements NumList, Iterable<Double> {
	// Front of list
	private NumNode front;
	// Back of list
	private NumNode back;
	// Size of list
	private int size;

	/**
	 * Initalizes an empty NumLinkedList
	 */
	public NumLinkedList() {
		size = 0;
		front = back = null;
	}

	public NumNode getFront() {
		// TODO Auto-generated method stub
		return front;
	}

	public NumNode getBack() {
		return back;
	}

	/**
	 * Returns size of list
	 * 
	 * @return size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the maximum elements the list can currently hold
	 * 
	 * @returns a large number
	 */
	@Override
	public int capacity() {
		return Integer.MAX_VALUE;
	}

	/**
	 * Adds element to back of list
	 */
	@Override
	public void add(double value) {
		// Create new node
		NumNode newNode = new NumNode(value);

		// list is empty
		if (++size == 1) {
			front = back = newNode;
			return;
		}

		// add list to back
		back.setNext(newNode);
		newNode.setPrevious(back);
		back = newNode;
	}

	/**
	 * Inserts a value at the specified index
	 */
	@Override
	public void insert(int i, double value) {
		// Add to back
		if (i >= size || size == 0) {
			add(value);
			return;
		}

		// Increase size and create new Node
		size++;
		NumNode newNode = new NumNode(value);

		// Add to front
		if (i == 0) {
			newNode.setNext(front);
			front.setPrevious(newNode);
			front = newNode;
			return;
		}

		// Add in between
		NumNode ptr = front;

		while (--i > 0)
			ptr = ptr.getNext();

		newNode.setNext(ptr.getNext());
		newNode.setPrevious(ptr);
		ptr.setNext(newNode);
		newNode.getNext().setPrevious(newNode);
	}

	/**
	 * Removes the i-th element of the list. Does nothing if i is greater than
	 * thenumber of elements in the list
	 */
	@Override
	public void remove(int i) {
		// Can't remove
		if (i >= size || i < 0)
			return;

		// List has 1 element
		if (--size == 0) {
			front = back = null;
			return;
		}

		// Remove front
		if (i == 0) {
			front = front.getNext();
			front.setPrevious(null);
			return;
		}

		// Remove back
		if (i == size) {
			back = back.getPrevious();
			back.setNext(null);
			return;
		}

		// Remove in middle
		NumNode ptr = front;
		while (ptr.hasNext() && i > 0) {
			ptr = ptr.getNext();
			i--;
		}
		ptr.getNext().setPrevious(ptr.getPrevious());
		ptr.getPrevious().setNext(ptr.getNext());

	}

	/**
	 * Removes any duplicates of any elements in the list while preserving the
	 * orderof the first duplicate
	 */
	@Override
	public void removeDuplicates() {
		// Return if size is less than 2
		if (size < 2)
			return;

		// Iterate through the list to
		for (NumNode ptr1 = front; ptr1 != null; ptr1 = ptr1.getNext())
			// Compare future elements in list to current element
			for (NumNode ptr2 = ptr1.getNext(); ptr2 != null; ptr2 = ptr2.getNext())

				// Remove Element if they're the same
				if (ptr1.getElement() == ptr2.getElement()) {
					ptr2.getPrevious().setNext(ptr2.getNext());
					if (ptr2.hasNext())
						ptr2.getNext().setPrevious(ptr2.getPrevious());
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
		// Returns empty string if list is empty
		if (size == 0)
			return "";

		// Otherwise return each element in a String seperated by a space
		// StringBuilder is faster than "" + ""
		StringBuilder sb = new StringBuilder();
		for (double d : this)
			sb.append(" " + d);
		return sb.substring(1);
	}

	/**
	 * Sorts the NumLinkedList via a bubble sort. Has a big-o runtime of N^2 due to
	 * having a nested loop
	 */
	@Override
	public void sort() {
		// If size < 2, then list is already sorted
		if (size < 2)
			return;

		boolean hasSwapped;
		NumNode ptr;
		NumNode lastPtr = null;

		// Iterate through the list to determine which elements need to be swapped
		do {
			hasSwapped = false;
			ptr = front;

			while (ptr.getNext() != lastPtr) {
				// Swap elements as needed
				if (ptr.getElement() > ptr.getNext().getElement()) {
					double element = ptr.getElement();
					ptr.setElement(ptr.getNext().getElement());
					ptr.getNext().setElement(element);
					hasSwapped = true;
				}
				ptr = ptr.getNext();
			}
			lastPtr = ptr;
		} while (hasSwapped);
	}

	/**
	 * @return a new iterator for this NumLinkedList
	 */
	@Override
	public Iterator<Double> iterator() {
		return new LinkedListIterator(this);
	}

	/**
	 * if list is sorted, adds value to list in sorted position otherwise
	 * addselement to back
	 */
	@Override
	public void sortedInsert(double value) {
		int index = 0;
		for (double d : this) {
			if (d > value) {
				insert(index, value);
				return;
			}
			index++;
		}

	}

	/**
	 * Reverses each element in the list
	 */
	@Override
	public void reverse() {
		if (size < 2)
			return;

		back = front;
		NumNode ptr = front;
		NumNode previousNode = null;

		while (ptr != null) {
			// swap the previous and next nodes for the current node
			previousNode = ptr.getPrevious();
			ptr.setPrevious(ptr.getNext());
			ptr.setNext(previousNode);

			ptr = ptr.getPrevious();
		}

		if (previousNode != null)
			front = previousNode.getPrevious();
	}

	public static void main(String[] args) {
		NumLinkedList list = new NumLinkedList();
		Random rand = new Random();

		for (int i = 0; i < 10; i++)
			list.add(rand.nextInt(100));
		System.out.println(list);
		list.reverse();
		System.out.println(list);
		list.reverse();
		System.out.println(list);
	}
}