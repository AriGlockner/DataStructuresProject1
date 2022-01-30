

public class NumLinkedList implements NumList {
	private NumNode front;
	private NumNode back;
	private int size;
	
	public NumLinkedList() {
		size = 0;
		front = back = null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return 0;
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
		if (i >= size) {
			add(value);
			return;
		}
		
		NumNode newNode = new NumNode(value);
		size++;
		
		if (i == 0) {
			newNode.setNext(front);
			front.setPrevious(newNode);
			front = newNode;
			return;
		}
	}

	@Override
	public void remove(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(double value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double lookup(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(NumList otherList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeDuplicates() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSorted() {
		// TODO Auto-generated method stub
		return false;
	}
}

