import java.util.*;

/**
 * This class represents a collection of Tree Nodes containing a Key and a Value as a sorted tree
 *
 * @param <T> key
 * @param <V> value
 */
public class BinarySearchTree<T extends Comparable<T>, V>
{
	// Top level node of tree
	TreeNode<T, V> root;

	/**
	 * Instantiates a new empty Binary Search Tree
	 */
	public BinarySearchTree()
	{
		root = null;
	}


	/**
	 * Inserts a node containing key in the BST
	 */
	public void insert(T key, V value)
	{
		// if tree is empty, add Node to tree and to list
		if (root == null)
		{
			root = new TreeNode<>(key, value);
			return;
		}

		// Add Node into sorted arraylist

		// Create a pointer node used for placing the node to be added in the correct spot
		TreeNode<T, V> ptr = root;

		// Return statement within while loop
		while (true)
		{
			// If keys are the same, replace the old value with the new value
			if (ptr.key.compareTo(key) == 0)
			{
				ptr.value = value;
				return;
				// Tree key is greater than key to insert
			} else if (ptr.key.compareTo(key) > 0)
			{
				// Insert to left
				if (ptr.left == null)
				{
					ptr.left = new TreeNode<>(key, value, ptr);
					return;
				}
				// Go to pointer's left node
				ptr = ptr.left;
			} else
			// Tree key is less than key to insert
			{
				// Insert to right
				if (ptr.right == null)
				{
					ptr.right = new TreeNode<>(key, value, ptr);
					return;
				}
				// Go to pointer's right node
				ptr = ptr.right;
			}
		}
	}

	/**
	 * @return each value in the tree separated by a space
	 */
	@Override
	public String toString()
	{
		if (root == null)
			return "";

		StringBuilder sb = new StringBuilder();
		List<V> list = inorderRec();

		for (V value : list)
			sb.append(" ").append(value);

		return sb.substring(1);

	}

	/**
	 * Searches for a node with a specific key in the BST
	 *
	 * @return a node with a specific key in the BST
	 */
	public V search(T key)
	{
		// Create a pointer node to search for the node with a specific key
		TreeNode<T, V> ptr = root;

		// Search for the key
		while (ptr != null)
		{
			if (ptr.key.compareTo(key) == 0) return ptr.value;
			if (ptr.key.compareTo(key) > 0) ptr = ptr.left;
			else ptr = ptr.right;
		}

		// Returns null if key does not exist
		return null;
	}

	/**
	 * Deletes a node containing key from the BST if it exists
	 */
	public void delete(T key)
	{
		if (root == null)
			return;


		// Remove Root
		if (key == root.key)
		{
			// Tree only contains root node
			if (root.right == null && root.left == null)
				root = null;
				// Root's right node is empty
			else if (root.right == null)
			{
				root = root.left;
				root.parent = null;
			}
			// Root's left node is empty
			else if (root.left == null)
			{
				root = root.right;
				root.parent = null;
			}
			// Default
			else
			{
				// Create Placeholder Node
				TreeNode<T, V> foo = root.right.left;

				// Move root node to root's right node
				root.right.left = root.left;
				root = root.right;
				root.parent = null;
				root.left.parent = root;

				// Add placeholder node back in if needed
				if (foo != null)
				{
					TreeNode<T, V> ptr = root.left;

					while (ptr.right != null)
						ptr = ptr.right;
					foo.parent = ptr;
					ptr.right = foo;
				}
			}
			return;
		}

		// Create pointer node
		TreeNode<T, V> ptr = root;
		// Boolean used for determine if child is a left child or right child
		boolean isLeft = true;

		// Search for node
		while (ptr != null)
		{
			// Remove node
			if (ptr.key.compareTo(key) == 0)
			{
				// Remove leaf node
				if (ptr.left == null && ptr.right == null)
					if (isLeft)
						ptr.parent.left = null;
					else
						ptr.parent.right = null;

					// Remove if left child is null
				else if (ptr.left == null)
					if (isLeft)
						ptr.parent.left = ptr.right;
					else
						ptr.parent.right = ptr.right;

					// Remove if right child is null
				else if (ptr.right == null)
					if (isLeft)
						ptr.parent.left = ptr.left;
					else
						ptr.parent.right = ptr.left;

					// Remove if Node has 2 child Nodes
				else if (isLeft)
				{
					// Create placeholder for node to be remove's right's left node
					TreeNode<T, V> foo = ptr.right.left;
					// Replace node to be deleted with node to be deleted's right node
					ptr.right.left = ptr.left;
					ptr.parent.left = ptr.right;

					// Put placeholder node back into the tree
					ptr = ptr.parent.left.left;

					while (ptr.right != null)
						ptr = ptr.right;
					ptr.right = foo;
				} else
				{
					// Create placeholder for node to be remove's left's right node
					TreeNode<T, V> foo = ptr.left.right;
					// Replace node to be deleted with node to be deleted's left node
					ptr.left.right = ptr.right;
					ptr.parent.right = ptr.left;

					// Put placeholder node back into the tree
					ptr = ptr.parent.right.right;

					while (ptr.left != null) ptr = ptr.left;
					ptr.left = foo;
				}

				return;
			}
			// Go to the pointer's left node
			if (ptr.key.compareTo(key) > 0)
			{
				ptr = ptr.left;
				isLeft = true;
				// Go to the pointer's right node
			} else
			{
				ptr = ptr.right;
				isLeft = false;
			}
		}
	}

	/**
	 * @return a list of values in inorder traversal of the BST implemented using
	 * recursion
	 */
	public List<V> inorderRec()
	{
		// If tree is empty, return null
		if (root == null)
			return null;
		// Call recursive method from node class
		return root.inorderRec();
	}


	/**
	 * Find the kth the smallest element in the BST
	 *
	 * @param k the kth the smallest value in the tree to return
	 * @return the kth the smallest element in the BST
	 */
	public V kthSmallest(int k)
	{
		// If k is not available in the list, will naturally throw an exception
		return inorderRec().get(k - 1);
	}

	/**
	 * This class represents a TreeNode for the BinarySearchTree Class. A TreeNode contains a value,
	 * a key that is used to sort this TreeNode in the BinarySearchTree,
	 * and pointers to the left and right Child TreeNodes of this TreeNode
	 *
	 * @param <T> key
	 * @param <V> value
	 */
	static class TreeNode<T extends Comparable<T>, V> implements Comparable<T>
	{
		// key to sort by within tree
		private final T key;
		// value node contains
		private V value;
		// Pointer to left child node
		TreeNode<T, V> left;
		// Pointer to right child node
		TreeNode<T, V> right;
		// Pointer to parent node
		TreeNode<T, V> parent;

		/**
		 * Instantiates a new TreeNode
		 *
		 * @param key   - value to be sorted by within the BST class
		 * @param value - element this TreeNode contains
		 */
		public TreeNode(T key, V value)
		{
			this.key = key;
			this.value = value;
			left = right = parent = null;
		}

		/**
		 * Instantiates a new TreeNode
		 *
		 * @param key   - value to be sorted by within the BST class
		 * @param value - element this TreeNode contains
		 * @param p     - parent node of this TreeNode
		 */
		public TreeNode(T key, V value, TreeNode<T, V> p)
		{
			this.key = key;
			this.value = value;
			left = right = null;
			parent = p;
		}

		/**
		 * Helper method for inorderRec method in BST class
		 *
		 * @return a list of values inorder transversal for this TreeNode and its child nodes
		 */
		private List<V> inorderRec()
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
		 * Compares this objects key to another key
		 *
		 * @param o - other key to compare to
		 * @return 1, 0, -1
		 */
		@Override
		public int compareTo(T o)
		{
			return (key.compareTo(o));
		}

		/**
		 * @return value
		 */
		@Override
		public String toString()
		{
			return value.toString();
		}
	}

	/**
	 * Demo
	 *
	 * @param args arguments
	 */
	public static void main(String[] args)
	{
		// Construct an empty BinarySearchTree
		// This example uses key-value pairs of Integer and Character
		BinarySearchTree<Integer, Character> tree = new BinarySearchTree<>();

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

		// Print the keys using inorder transversal
		System.out.println("Expected:\tA B C E F G I J K\nActual:\t\t" + tree);

		// Search 12 then search 4.
		System.out.println("Expected:\tK\nActual:\t\t" + tree.search(12));
		System.out.println("Expected:\tnull\nActual:\t\t" + tree.search(4));
		//System.out.println("Expected:\t[A, B, C, E, F, G, I, J, K]\nActual:\t\t" + tree.inorderRec());

		// Find the 3rd smallest element in the tree
		System.out.println("Expected:\tC\nActual\t\t" + tree.kthSmallest(3));

		/* Show list works with other generic types by using at least 2 other types */

		// Key: String, Value: String
		BinarySearchTree<String, String> tree2 = new BinarySearchTree<>();
		String[] strings = new String[] {"ycienso", ";socje", "3.14159", "abcdefg", "JDxjnadFjk"};

		for (int i = 0; i < 5; i++)
			tree2.insert(strings[i], strings[i]);
		System.out.println("Expected:\t3.14159 ;socje JDxjnadFjk abcdefg ycienso\nActual:\t\t" + tree2);

		// Key: float, Value boolean
		BinarySearchTree<Float, Boolean> tree3 = new BinarySearchTree<>();

		for (int i = 0; i < 10; i++)
			if (i % 2 == 0)
				tree3.insert((float) i, true);
			else
				tree3.insert((float) i, false);

		System.out.println("Expected:\ttrue false true false true false true false true false\nActual:\t\t" + tree3);

		// Key: Integer, Value: don't care
		BinarySearchTree<Integer, Object> tree4 = new BinarySearchTree<>();
		Object[] values = new Object[] {Math.PI, new HashMap<>(), true, Math.E, Long.MAX_VALUE, "Does this prove to you that I know how to use generic types?"};
		for (int i = 0; i < values.length; i++)
			tree4.insert(i, values[i]);
		System.out.println("Expected:\t3.141592653589793 {} true 2.718281828459045 9223372036854775807 Does this prove to you that I know how to use generic types?");
		System.out.println("Actual:\t" + tree4);
	}
}
