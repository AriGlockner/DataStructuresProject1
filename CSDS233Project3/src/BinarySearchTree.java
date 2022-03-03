import java.util.*;

/**
 * This class represents a collection of Tree Nodes containing a Key and a Value as a tree
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
		TreeNode<T, V> ptr = root;

		// Return statement within while loop
		while (true)
		{
			// If keys are the same, replace the old value with this value
			if (ptr.key.compareTo(key) == 0)
			{
				ptr.value = value;
				return;
			} else if (ptr.key.compareTo(key) > 0)
			{
				if (ptr.left == null)
				{
					ptr.left = new TreeNode<>(key, value, ptr);
					return;
				}
				ptr = ptr.left;
			} else
			{
				if (ptr.right == null)
				{
					ptr.right = new TreeNode<>(key, value, ptr);
					return;
				}
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
		if (root == null) return "";

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
		TreeNode<T, V> ptr = root;

		while (ptr != null)
		{
			if (ptr.key.compareTo(key) == 0) return ptr.value;
			if (ptr.key.compareTo(key) > 0) ptr = ptr.left;
			else ptr = ptr.right;
		}

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


		TreeNode<T, V> ptr = root;
		//TreeNode<T, V> lastPtr = root;
		boolean isLeft = true;

		while (ptr != null)
		{
			// Remove node
			if (ptr.key.compareTo(key) == 0)
			{
				// Remove leaf node
				if (ptr.left == null && ptr.right == null)
					if (isLeft)
						//lastPtr.left = null;
						ptr.parent.left = null;
					else
						ptr.parent.right = null;
						//lastPtr.right = null;

					// Remove if left child is null
				else if (ptr.left == null)
					if (isLeft)
						ptr.parent.left = ptr.right;
						//lastPtr.left = ptr.right;
					else
						ptr.parent.right = ptr.right;
						//lastPtr.right = ptr.right;

					// Remove if right child is null
				else if (ptr.right == null)
					if (isLeft)
						ptr.parent.left = ptr.left;
						//lastPtr.left = ptr.left;
					else
						ptr.parent.right = ptr.left;
						//lastPtr.right = ptr.left;

					// Remove if Node has 2 child Nodes
				else if (isLeft)
				{
					// Create placeholder for node to be remove's right's left node
					TreeNode<T, V> foo = ptr.right.left;
					// Replace node to be deleted with node to be deleted's right node
					ptr.right.left = ptr.left;
					ptr.parent.left = ptr.right;
					//lastPtr.left = ptr.right;

					// Put placeholder node back into the tree
					ptr = ptr.parent.left.left;
					//ptr = lastPtr.left.left;
					while (ptr.right != null) ptr = ptr.right;
					ptr.right = foo;
				} else
				{
					// Create placeholder for node to be remove's left's right node
					TreeNode<T, V> foo = ptr.left.right;
					// Replace node to be deleted with node to be deleted's left node
					ptr.left.right = ptr.right;
					ptr.parent.right = ptr.left;
					//lastPtr.right = ptr.left;

					// Put placeholder node back into the tree
					ptr = ptr.parent.right.right;
					//ptr = lastPtr.right.right;
					while (ptr.left != null) ptr = ptr.left;
					ptr.left = foo;
				}

				return;
			}
			// Iterate to
			if (ptr.key.compareTo(key) > 0)
			{
				//lastPtr = ptr;
				ptr = ptr.left;
				isLeft = true;
			} else
			{
				//lastPtr = ptr;
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
		if (root == null) return null;
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
		private final T key;
		private V value;
		TreeNode<T, V> left;
		TreeNode<T, V> right;
		TreeNode<T, V> parent;

		public TreeNode(T key, V value)
		{
			this.key = key;
			this.value = value;
			left = right = parent = null;
		}

		public TreeNode(T key, V value, TreeNode<T, V> p)
		{
			this.key = key;
			this.value = value;
			left = right = null;
			parent = p;
		}

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

		@Override
		public int compareTo(T o)
		{
			return (key.compareTo(o));
		}

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

		System.out.println("Expected:\tA B C E F G I J K\nActual:\t\t" + tree);

		// Search 12 then search 4.
		System.out.println("Expected:\tK\nActual:\t\t" + tree.search(12));
		System.out.println("Expected:\tnull\nActual:\t\t" + tree.search(4));
		System.out.println("Expected:\t[A, B, C, E, F, G, I, J, K]\nActual:\t\t" + tree.inorderRec());

		// Find the 3rd smallest element in the tree
		System.out.println("Expected:\tC\nActual\t\t" + tree.kthSmallest(3));

		// Show list works with other generic types
		// Double, String
		BinarySearchTree<Double, String> tree2 = new BinarySearchTree<>();
		String[] strings2 = new String[] {"kajdfci", "qiduxh", "cvaebr", "k", "dchuvabjkk"};

		for (int i = 0; i < 5; i++)
			tree2.insert((double) i, strings2[i]);
		System.out.println("Expected:\tkajdfci qiduxh cvaebr k dchuvabjkk\nActual:\t\t" + tree2);

		// String, String
		BinarySearchTree<String, String> tree3 = new BinarySearchTree<>();
		String[] strings3 = new String[] {"ycienso", ";socje", "3.14159", "abcdefg", "JDxjnadFjk"};

		for (int i = 0; i < 5; i++)
			tree3.insert(strings3[i], strings3[i]);
		System.out.println("Expected:\t3.14159 ;socje JDxjnadFjk abcdefg ycienso\nActual:\t\t" + tree3);
	}
}
