import java.util.Iterator;

public class NumLinkedList implements NumList, Iterable<Double> {
	private NumNode front;
	private NumNode back;
	private int size;

	public NumLinkedList() {
		size = 0;
		front = back = null;
	}

	NumNode getFront() {
		return front;
	}

	NumNode getBack() {
		return back;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int capacity() {
		return size;
	}

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

		// if size = 1, set front to new node as well
		if (size == 1)
			front = back;
	}

	@Override
	public void insert(int i, double value) {
		// Add to back
		if (size == 0 || i >= size) {
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
		} else if (i == size) {
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

	@Override
	public boolean contains(double value) {
		NumNode ptr = front;

		while (ptr.hasNext()) {
			if (ptr.getElement() == value)
				return true;
			ptr = ptr.getNext();
		}

		return ptr.getElement() == value;
	}

	@Override
	public double lookup(int i) {
		if (i >= size)
			return back.getElement();

		NumNode ptr = front;

		while (i > 0) {
			i--;
			ptr = ptr.getNext();
		}

		return ptr.getElement();
	}

	@Override
	public boolean equals(NumList otherList) {
		if (otherList.size() != size)
			return false;

		for (int i = 0; i < size; i++)
			if (otherList.lookup(i) != this.lookup(i))
				return false;

		return true;
	}

	@Override
	public void removeDuplicates() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		if (size == 0)
			return "";
		String s = "" + front.getElement();

		NumNode ptr = front;
		while (ptr.hasNext()) {
			ptr = ptr.getNext();
			s += " " + ptr.getElement();
		}

		return s;
	}

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

	@Override
	public Iterator<Double> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator(this);
	}
}

class ListIterator implements Iterator<Double> {
	// Current node in list
	private NumNode current;

	// initialize pointer to head of the list for iteration
	public ListIterator(NumLinkedList list) {
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