import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Comparable<T>, V> implements BinarySearchTreeInterface<T, V>
{
	// Top level node of tree
	private TreeNode<T, V> root;

	/**
	 * Instantiates a new empty AVL Tree
	 */
	public AVLTree()
	{
		root = null;
	}

	/**
	 * Inserts a node containing key in the AVL Tree
	 */
	@Override
	public void insert(T key, V value)
	{
		// Create Node to add
		TreeNode<T, V> newNode = new TreeNode<>(key, value);

		// if tree is empty, add Node to tree and to list
		if (root == null)
		{
			root = newNode;
			return;
		}

		// Add Node into Tree the same way as adding it to the BST
		TreeNode<T, V> ptr = root;

		// Return statement within while loop
		while (true)
		{
			// Return if value to add is the same as the node's value
			if (ptr.value == value)
				return;

			// If keys are the same, replace the old value with this value
			if (ptr.key.compareTo(newNode.key) == 0)
			{
				ptr.value = value;
				return;
			} else if (ptr.key.compareTo(newNode.key) > 0)
			{
				if (ptr.left == null)
				{
					ptr.left = newNode;
					break;
				}
				ptr = ptr.left;
			} else
			{
				if (ptr.right == null)
				{
					ptr.right = newNode;
					break;
				}
				ptr = ptr.right;
			}
		}

		// Rearrange Nodes as needed
		/*
		Rotations Needed:
		Right-Right
		Left-Left
		Right-Left
		Left-Right
		 */

	}

	/**
	 * Anti-clockwise rotation of nodes
	 *
	 * @param P - Parent node of node to rotate
	 */
	private void rotateRightRight(TreeNode<T, V> P)
	{
		// Return if rotation is not possible
		if (P.right == null || P.right.right == null)
			return;
		TreeNode<T, V> A = P.right;
		TreeNode<T, V> B = A.right;
		A.right = null;
		B.left = A;
		P.right = B;
	}

	/**
	 * Clockwise rotation of nodes
	 *
	 * @param P - Parent node of node to rotate
	 */
	private void rotateLeftLeft(TreeNode<T, V> P)
	{
		// Return if rotation is not possible
		if (P.left == null || P.left.left == null)
			return;
		TreeNode<T, V> A = P.left;
		TreeNode<T, V> B = A.left;
		A.left = null;
		B.right = A;
		P.left = B;
	}

	private void rotateRightLeft(TreeNode<T, V> P)
	{

	}

	private void rotateLeftRight(TreeNode<T, V> P)
	{

	}


	/**
	 * Searches for a node with a specific key in the BST
	 *
	 * @return a node with a specific key in the BST
	 */
	@Override
	public V search(T key)
	{
		TreeNode<T, V> ptr = root;

		while (ptr != null)
		{
			if (ptr.key.compareTo(key) == 0)
				return ptr.value;
			if (ptr.key.compareTo(key) > 0)
				ptr = ptr.left;
			else
				ptr = ptr.right;
		}

		return null;
	}

	/**
	 * Deletes a node containing key from the AVL Tree if it exists
	 */
	@Override
	public void delete(T key)
	{

	}

	/**
	 * @return a list of values in inorder traversal of the AVL Tree implemented using
	 * recursion
	 */
	@Override
	public List<V> inorderRec()
	{
		if (root == null) return null;
		return root.inorderRec();
	}

	/**
	 * This class represents a TreeNode for the AVLTree Class. A TreeNode contains a value,
	 * a key that is used to sort this TreeNode in the BinarySearchTree,
	 * and pointers to the left and right Child TreeNodes of this TreeNode
	 *
	 * @param <T> key
	 * @param <V> value
	 */
	static class TreeNode<T extends Comparable<T>, V> implements Comparable<T>
	{
		// Key to be sorted by
		private final T key;
		// Element Node Contains
		private V value;
		// Left Child Node
		private TreeNode<T, V> left;
		// Right Child Node
		private TreeNode<T, V> right;

		/**
		 * Instantiates a new TreeNode containing a key and a value
		 *
		 * @param key   - what TreeNode is sorted by
		 * @param value - what TreeNode holds
		 */
		public TreeNode(T key, V value)
		{
			this.key = key;
			this.value = value;
			left = right = null;
		}

		/**
		 * @return a list containing this element and all child elements in a sorted order
		 */
		public List<V> inorderRec()
		{
			List<V> list = new ArrayList<>();

			if (left != null)
				list.addAll(left.inorderRec());

			list.add(value);

			if (right != null)
				list.addAll(right.inorderRec());

			return list;
		}

		/**
		 * @param o - other key
		 * @return -1, 0, 1
		 */
		@Override
		public int compareTo(T o)
		{
			return (key.compareTo(o));
		}

		/**
		 * @return each element in inorderRec seperated by a space
		 */
		@Override
		public String toString()
		{
			StringBuilder sb = new StringBuilder();

			if (left != null) sb.append(" ").append(left);
			sb.append(" ").append(value.toString());
			if (right != null) sb.append(" ").append(right);

			return sb.substring(1);
		}
	}

	@Override
	public String toString()
	{
		if (root == null) return "";
		return root.toString();
	}

	/**
	 * Demo
	 *
	 * @param args arguments
	 */
	public static void main(String[] args)
	{
		AVLTree<Integer, Integer> foo = new AVLTree<>();
		foo.insert(0, 0);
		foo.insert(1, 1);
		foo.insert(2, 2);
		foo.insert(-1, -1);
		foo.insert(-2, -2);
		System.out.println(foo);
		foo.rotateRightRight(foo.root);
		System.out.println(foo);
		foo.rotateLeftLeft(foo.root);
		System.out.println(foo);

		/*
		AVLTree<Integer, Character> tree = new AVLTree<>();
		// Insert: 2, 1, 4, 5, 9, 3, 6, 7, 10, 12, 11
		tree.insert(2, 'B');
		tree.insert(1, 'A');
		tree.insert(4, 'D');
		tree.insert(5, 'E');
		tree.insert(9, 'H');
		tree.insert(3, 'C');
		tree.insert(6, 'F');
		tree.insert(7, 'G');
		tree.insert(10, 'I');
		tree.insert(12, 'K');
		tree.insert(11, 'J');

		// Delete 4, then 9
		tree.delete(4);
		tree.delete(9);

		System.out.println("Expected:\tA B C E F G I J K\nActual:\t\t" + tree);

		// Search 12 then search 4.
		System.out.println("Expected:\tK\nActual:\t\t" + tree.search(12));
		System.out.println("Expected:\tnull\nActual:\t\t" + tree.search(4));
		System.out.println("Expected:\t[A, B, C, E, F, G, I, J, K]\nActual:\t\t" + tree.inorderRec());

		// Find the 3rd smallest element in the tree
		System.out.println("Expected:\tC\nActual\t\t" + tree.kthSmallest(3));

		// Show list works with other generic types
		// Double, String
		BinarySearchTreeInterface<Double, String> tree2 = new AVLTree<>();
		String[] strings2 = new String[] {"kajdfci", "qiduxh", "cvaebr", "k", "dchuvabjkk"};

		for (int i = 0; i < 5; i++)
			tree2.insert((double) i, strings2[i]);
		System.out.println("Expected:\tkajdfci qiduxh cvaebr k dchuvabjkk\nActual:\t\t" + tree2);

		// String, String
		BinarySearchTreeInterface<String, String> tree3 = new AVLTree<>();
		String[] strings3 = new String[] {"ycienso", ";socje", "3.14159", "abcdefg", "JDxjnadFjk"};

		for (int i = 0; i < 5; i++)
			tree3.insert(strings3[i], strings3[i]);
		System.out.println("Expected:\t3.14159 ;socje JDxjnadFjk abcdefg ycienso\nActual:\t\t" + tree3);
		*/
	}
}
