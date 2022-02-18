import java.util.Iterator;

class LinkedListIterator implements Iterator<Double> {
	// Current node in list
	private NumNode current;

	// initialize pointer to head of the list for iteration
	public LinkedListIterator(NumLinkedList list) {
		current = list.getFront();
	}

	// Adds an element
	public void add(double element) {
		while (current.hasNext())
			next();

		current.setNext(new NumNode(element));
	}

	// Adds an element at a specified index
	public void add(int index, double element) {
		// Get to index
		while (current.hasNext() && index > 0) {
			next();
			index--;
		}

		// Insert new node containing element
		NumNode n = new NumNode(element);
		n.setNext(current.getNext());
		n.setPrevious(current);
		n.getNext().setPrevious(n);
		current.setNext(n);
	}

	// returns false if next element does not exist
	public boolean hasNext() {
		return current != null;
	}

	// returns false if previous element does not exist
	public boolean hasPrevious() {
		return current != null;
	}

	// return current data and update pointer
	public Double next() {
		double data = current.getElement();
		current = current.getNext();
		return data;
	}

	// return current element and update pointer
	public double previous() {
		double data = current.getElement();
		current = current.getPrevious();
		return data;
	}

	// implement if needed
	public void remove() {
		if (current.hasPrevious()) {
			current.getPrevious().setNext(current.getNext());
		}
		if (current.hasNext()) {
			current.getNext().setPrevious(current.getPrevious());
		}
	}

	public double get() {
		return current.getElement();
	}

	// Replaces the last element returned by next() or previous() with the specified
	// element
	public void set(double t) {
		current.setElement(t);
	}
}