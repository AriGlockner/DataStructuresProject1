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
	 *
	 * @param key   - value to be sorted by
	 * @param value - value node will contain
	 */
	public void insert(T key, V value)
	{
		/*
		// Create Node to add
		TreeNode<T, V> newNode = new TreeNode<>(key, value);

		// if tree is empty, add Node to tree
		if (root == null)
		{
			root = newNode;
			return;
		}

		// Add Node into sorted Tree
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
					return;
				}
				ptr = ptr.left;
			} else
			{
				if (ptr.right == null)
				{
					ptr.right = newNode;
					newNode.parent = ptr;
					return;
				}
				ptr = ptr.right;
			}
		}
		 */
		insertNode(new TreeNode<>(key, value));
	}

	private void insertNode(TreeNode<T, V> newNode)
	{
		if (newNode == null)
			return;

		// if tree is empty, add Node to tree
		if (root == null)
		{
			root = newNode;
			return;
		}

		// Add Node into sorted Tree
		TreeNode<T, V> ptr = root;

		// Return statement within while loop
		while (true)
		{
			// Return if value to add is the same as the node's value
			if (ptr.value == newNode.value)
				return;

			// If keys are the same, replace the old value with this value
			if (ptr.key.compareTo(newNode.key) == 0)
			{
				ptr.value = newNode.value;
				return;
			} else if (ptr.key.compareTo(newNode.key) > 0)
			{
				if (ptr.left == null)
				{
					ptr.left = newNode;
					newNode.parent = ptr;
					return;
				}
				ptr = ptr.left;
			} else
			{
				if (ptr.right == null)
				{
					ptr.right = newNode;
					newNode.parent = ptr;
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
		List<V> values = inorderRec();
		for (V value : values)
			sb.append(" ").append(value);
		return sb.substring(1);

		//return root.toString();
	}

	/**
	 * Searches for a node with a specific key in the BST
	 *
	 * @param key - value to search for
	 * @return a node with a specific key in the BST if it exists. Otherwise, returns null
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
	 *
	 * @param key - value to be sorted by
	 */
	public void delete(T key)
	{
		if (root == null)
			return;

		// Remove root
		if (key == root.key)
		{
			// If tree contains 1 node
			if (root.right == null && root.left == null)
			{
				root = null;
				return;
			}

			// If tree contains 1 right child, but no left child
			else if (root.left == null)
			{
				root = root.right;
				root.parent = null;
				return;
			}

			// If tree contains 1 left child, but no right child
			else if (root.right == null)
			{
				root = root.left;
				root.parent = null;
				return;
			}

			// Root contains 2 children
			TreeNode<T, V> placeholder = root.right.left;
			root.right.left = root.left;
			root = root.right;
			root.parent = null;
			root.left.parent = root;

			// Add placeholder back in
			insertNode(placeholder);

			return;
		}

		// Search for node to remove
		TreeNode<T, V> ptr = root;
		// isLeft is used for telling which direction the node to remove is in
		boolean isLeft = true;

		do
		{
			if (ptr.compareTo(key) == 0)
				break;
			else if (ptr.compareTo(key) > 0)
			{
				ptr = ptr.right;
				isLeft = false;
			}
			else
			{
				ptr = ptr.left;
				isLeft = true;
			}
		} while (ptr != null);

		if (ptr == null)
			return;

		// Remove ptr

		// Remove if 0 child nodes
		if (ptr.left == null && ptr.right == null)
		{
			if (isLeft)
				ptr.parent.left = null;
			else
				ptr.parent.right = null;
			return;
		}

		// Remove if ptr has left child node
		if (ptr.right == null)
		{
			if (isLeft)
				ptr.parent.left = ptr.left;
			else
				ptr.parent.right = ptr.left;
			return;
		}

		// Remove if ptr has right child node
		if (ptr.left == null)
		{
			if (isLeft)
				ptr.parent.left = ptr.right;
			else
				ptr.parent.right = ptr.right;
			return;
		}

		// Remove if 2 child nodes
		TreeNode<T, V> placeholder = ptr.right;
		ptr.parent.right = ptr.left;
		ptr.left.parent = ptr.parent;

		// Insert placeholder back in
		insertNode(placeholder);

		/*
		// Remove Root
		if (key == root.key)
		{
			// Tree only contains root node
			if (root.right == null && root.left == null)
				removeAll();
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
				// Create Placeholder node
				TreeNode<T, V> foo = root.right.left;

				// point
				root.right.left = root.left;

				// shift node to parent
				root = root.right;
				root.parent = null;

				//
				root.left.parent = root;

				// add placeholder node back in if placeholder is not null
				if (foo != null)
				{
					TreeNode<T, V> ptr = root;

					while (true)
						if (foo.compareTo(ptr.key) > 0)
							if (ptr.right == null)
							{
								ptr.right = foo;
								foo.parent = ptr;
								break;
							} else
								ptr = ptr.right;
						else if (ptr.left == null)
						{
							ptr.left = foo;
							foo.parent = ptr;
							break;
						} else
							ptr = ptr.left;
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
						ptr.parent.left = null;
						//lastPtr.left = null;
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
					System.out.println("Ptr: " + ptr);
					System.out.println("Parent: " + ptr.parent);
					System.out.println("Left: " + ptr.left);
					System.out.println("Right: " + ptr.right);

					// Create placeholder for node to be remove's right's left node
					TreeNode<T, V> foo = ptr.right.left;
					// Replace node to be deleted with node to be deleted's right node
					ptr.right.left = ptr.left;
					ptr.parent.left = ptr.right;
					//lastPtr.left = ptr.right;

					// Put placeholder node back into the tree
					//ptr = lastPtr.left.left;
					ptr = ptr.parent.left.left;
					while (ptr.right != null) ptr = ptr.right;
					ptr.right = foo;

				} else
				{
					System.out.println("Ptr: " + ptr);
					System.out.println("Parent: " + ptr.parent);
					System.out.println("Left: " + ptr.left);
					System.out.println("Right: " + ptr.right);

					// Create placeholder for node to be remove's left's right node
					TreeNode<T, V> foo = ptr.left.right;
					// Replace node to be deleted with node to be deleted's left node
					ptr.left.right = ptr.right;
					ptr.parent.right = ptr.left;
					//lastPtr.right = ptr.left;

					// Put placeholder node back into the tree
					//ptr = lastPtr.right.right;
					ptr = ptr.parent.right.right;
					while (ptr.left != null) ptr = ptr.left;
					ptr.left = foo;
				}

				return;
			}
			// Iterate to
			if (ptr.key.compareTo(key) > 0)
			{
				//lastPtr = ptr;
				ptr.parent = ptr;
				ptr = ptr.left;
				isLeft = true;
			} else
			{
				ptr.parent = ptr;
				//lastPtr = ptr;
				ptr = ptr.right;
				isLeft = false;
			}
		}
		 */
	}

	/**
	 * Find the kth the smallest element in the BST
	 *
	 * @param k the kth the smallest value in the tree to return
	 * @return the kth the smallest element in the BST
	 */
	V kthSmallest(int k)
	{
		return inorderRec().get(k - 1);
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
	 * Removes all elements from Tree
	 */
	public void removeAll()
	{
		root = null;
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
		// Key to be sorted by
		private final T key;
		// Element Node Contains
		private V value;
		// Left Child Node
		TreeNode<T, V> left;
		// Right Child Node
		TreeNode<T, V> right;
		// Parent Node
		TreeNode<T, V> parent;

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
			parent = null;
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
			/*
			StringBuilder sb = new StringBuilder();

			if (left != null) sb.append(" ").append(left);
			sb.append(" ").append(value.toString());
			if (right != null) sb.append(" ").append(right);

			return sb.substring(1);
			 */
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
