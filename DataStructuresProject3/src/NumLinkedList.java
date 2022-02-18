import java.util.Iterator;

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

	/**
	 * @return front NumNode of list
	 */
	public NumNode getFront() {
		return front;
	}

	/**
	 * @return back NumNode of list
	 */
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
	 * @returns a large integer
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

		// Iterate through list to get to node to insert
		while (--i > 0)
			ptr = ptr.getNext();

		// Insert Node
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
		// Can't remove, so return
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

		// Iterate through the list
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
	 * if list is sorted, adds value to list in sorted position otherwise adds
	 * element to list in front of 1st element where value is less than value in
	 * list or back of list
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
		add(value);
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

	/**
	 * @return a new iterator for this NumLinkedList
	 */
	@Override
	public Iterator<Double> iterator() {
		return new LinkedListIterator(this);
	}

	/**
	 * Demonstration
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Create list
		NumList list = new NumLinkedList();

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