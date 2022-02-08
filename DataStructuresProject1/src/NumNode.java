/**
 * The NumNode class represents a node for the NumLinkedList class. A NumNode
 * contains an element of type double and pointers to the next and previous
 * nodes in the list
 * 
 * @author ari
 */

public class NumNode {
	// Element stored in node
	private double element;
	// Next Node in list
	private NumNode next;
	// Previous Node in list
	private NumNode previous;

	/*
	 * Constructor that creates a new Linked List Node with an element
	 */
	public NumNode(double e) {
		element = e;
		next = previous = null;
	}

	/*
	 * Returns element stored in this node
	 */
	public double getElement() {
		return element;
	}

	/*
	 * Sets this node's element
	 */
	public void setElement(double e) {
		element = e;
	}

	/*
	 * Gets the next node in the list
	 */
	public NumNode getNext() {
		return next;
	}

	/*
	 * Sets the next node in the list
	 */
	public void setNext(NumNode n) {
		next = n;
	}

	/*
	 * Gets the previous node in the list
	 */
	public NumNode getPrevious() {
		return previous;
	}

	/**
	 * Sets the previous node in the list
	 */
	public void setPrevious(NumNode p) {
		previous = p;
	}

	/**
	 * @return true if this node has a next node
	 */
	public boolean hasNext() {
		return getNext() != null;
	}

	/**
	 * @return true if this node has a prior node
	 */
	public boolean hasPrevious() {
		return getPrevious() != null;
	}

	/**
	 * @return element in form of String
	 */
	@Override
	public String toString() {
		return "" + element;
	}
}
