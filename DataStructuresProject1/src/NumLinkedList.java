import java.util.Iterator;

public class NumLinkedList implements NumList, Iterable<Double> {
	// Front of list
	private NumNode front;
	// Back of list
	private NumNode back;
	// Size of list
	private int size;

	// Initalize list
	public NumLinkedList() {
		size = 0;
		front = back = null;
	}

	// Returns front Node in list
	NumNode getFront() {
		return front;
	}

	// Returns back Node in list
	NumNode getBack() {
		return back;
	}

	// Returns size of list
	@Override
	public int size() {
		return size;
	}

	// Returns size of list
	@Override
	public int capacity() {
		return size;
	}

	// Adds element to back of list
	@Override
	public void add(double value) {
		// Create new node
		NumNode newNode = new NumNode(value);

		// list is empty
		if (size++ == 0) {
			front = back = newNode;
			return;
		}

		// add list to back
		back.setNext(newNode);
		newNode.setPrevious(back);
		back = newNode;
	}

	// Inserts a value at the specified index
	@Override
	public void insert(int i, double value) {
		// Add to back
		if (i >= size || size == 0) {
			add(value);
			return;
		}

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

	// Removes i-th value in list
	@Override
	public void remove(int i) {
		if (i >= size || i < 0)
			return;

		if (--size == 0) {
			front = back = null;
			return;
		}

		if (i == 0) {
			front = front.getNext();
			front.setPrevious(null);
			return;
		}

		if (i == size) {
			back = back.getPrevious();
			back.setNext(null);
			return;
		}

		NumNode ptr = front;
		while (ptr.hasNext() && i > 0) {
			ptr = ptr.getNext();
			i--;
		}
		ptr.getNext().setPrevious(ptr.getPrevious());
		ptr.getPrevious().setNext(ptr.getNext());

	}

	// Returns true if list contains value
	// Otherwise returns false
	@Override
	public boolean contains(double value) {
		for (double d : this)
			if (d == value)
				return true;
		return false;
	}

	// Returns i-th value in list
	@Override
	public double lookup(int i) {
		for (double d : this)
			if (i-- <= 0)
				return d;
		return back.getElement();
	}

	// Removes any duplicates in the list
	@Override
	public void removeDuplicates() {
		if (size < 2)
			return;
		NumNode ptr1 = front;

		while (true) {
			NumNode ptr2 = ptr1.getNext();
			while (true) {
				if (ptr1.getElement() == ptr2.getElement()) {
					size--;
					ptr2 = ptr2.getPrevious();
					ptr2.setNext(ptr2.getNext().getNext());
					if (!ptr2.hasNext())
						break;
					ptr2.getNext().setPrevious(ptr2);
				}
				if (!ptr2.hasNext())
					break;

				ptr2 = ptr2.getNext();
			}
			if (!ptr1.hasNext())
				break;
			ptr1 = ptr1.getNext();
		}
	}

	// Returns each Value in the list as a string seperated by a space
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

	// Returns true if list is sorted
	// Otherwise returns false
	@Override
	public boolean isSorted() {
		if (size < 2)
			return true;

		NumNode ptr = front;
		while (ptr.hasNext()) {
			if (ptr.getElement() > ptr.getNext().getElement())
				return false;

			ptr = ptr.getNext();
		}
		return true;
	}
	
	/**
	 * Sorts the NumLinkedList
	 */
	@Override
	public void sort() {
		if (size < 2)
			return;
		
		
		
		System.out.println("Unable to sort");
	}
	
	
	@Override
	public Iterator<Double> iterator() {
		return new LinkedListIterator(this);
	}

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
}