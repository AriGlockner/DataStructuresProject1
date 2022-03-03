import java.util.ArrayList;
import java.util.List;

public class temp<T extends Comparable<T>, V> extends BinarySearchTree<T, V>
{
	/*
	public temp()
	{
		super();
	}
	*/

	@Override
	public void insert(T key, V value)
	{
		super.insert(key, value);
	}

	public static void main(String[] args)
	{
		temp<Integer, Integer> t = new temp<>();
		t.insert(5, 5);
		System.out.println(t);
	}
}


class AVLTree2<T extends Comparable<T>, V>
{
	// Top level node of tree
	private TreeNode<T, V> root;

	/**
	 * Instantiates a new empty AVL Tree
	 */
	public AVLTree2()
	{
		root = null;
	}

	/**
	 * Inserts a node containing key in the AVL Tree
	 */
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
					newNode.parent = ptr;
					break;
				}
				ptr = ptr.left;
			} else
			{
				if (ptr.right == null)
				{
					ptr.right = newNode;
					newNode.parent = ptr;
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
	 * @param oldTop - Top node to rotate
	 */
	private void rotateRightRight(TreeNode<T, V> oldTop)
	{
		TreeNode<T, V> parent = oldTop.parent;

		// top is root
		if (parent == null)
		{
			// Set top.right as root node
			root = oldTop.right;
			root.parent = null;
			oldTop.parent = root;

			// Set root.left as the old top node's old left node
			oldTop.right = root.left;
			if (root.left != null)
				root.left.parent = oldTop;

			// Set root.left = top
			root.left = oldTop;
		} else {
			// parent
			parent.right = oldTop.right;
			oldTop.right.parent = parent;


			oldTop.right = parent.right.left;
			if (parent.right.left != null)
				oldTop.right.parent = oldTop;


			parent.right.left = oldTop;
		}



		/*
		// top node is root
		if (parent == null)
		{
			root = top.right;
			root.parent = null;
			top.right = null;
			root.left = top;
			top.parent = root;
			return;
		}
		// default
		else
		{
			parent.right = top.right;
			top.right = null;
			parent.right.left = top;
			top.parent = parent;
		}
		*/
	}

	/**
	 * Clockwise rotation of nodes
	 *
	 * @param top - Top node to rotate
	 */
	private void rotateLeftLeft(TreeNode<T, V> top)
	{
		TreeNode<T, V> parent = top.parent;

		// top node is root
		if (parent == null)
		{
			root = top.left;
			root.parent = null;
			top.left = null;
			root.right = top;
			top.parent = root;
			return;
		}

		// default
		parent.left = top.left;
		top.left = null;
		parent.left.right = top;
		top.parent = parent;
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
	public void delete(T key)
	{

	}

	/**
	 * @return a list of values in inorder traversal of the AVL Tree implemented using
	 * recursion
	 */
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
		// Parent Node
		private TreeNode<T, V> parent;

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
			left = right = parent = null;
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
		// H, I, J, B, A, E, C, F, D, G, K, L
		AVLTree2<Character, Character> foo = new AVLTree2<>();
		foo.insert('H', 'H');
		foo.insert('I', 'I');
		foo.insert('J', 'J');
		foo.rotateRightRight(foo.root);
		System.out.println(foo);
		foo.insert('K', 'K');
		foo.insert('L', 'L');
		System.out.println(foo);
		foo.rotateRightRight(foo.root.right);
		System.out.println(foo);
		System.out.println(foo.root.right.right.value);
		System.out.println(foo.root.right.left.value);

		/*
		System.out.println(foo);
		System.out.println(foo.root.value);
		foo.rotateRightRight(foo.root);
		System.out.println(foo.root.value);
		foo.insert('B', 'B');
		foo.insert('A', 'A');
		System.out.println(foo.root.left.left.value);
		foo.rotateLeftLeft(foo.root.left.left);
		System.out.println(foo.root.left.left.value);
		foo.insert('E', 'E');
		System.out.println(foo);
		System.out.println(foo.root.left.right.value);

		/*
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
