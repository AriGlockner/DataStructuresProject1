import java.util.*;

/**
 * @param <T> key
 * @param <V> value
 */
public class BinarySearchTree<T extends Comparable<T>, V>
{
	private TreeNode<T, V> root;

	public BinarySearchTree()
	{
		root = null;
	}


	/**
	 * Inserts a node containing key in the BST
	 */
	public void insert(T key, V value)
	{
		// Create Node to add
		TreeNode<T, V> newNode = new TreeNode<T, V>(key, value);

		// if tree is empty, add Node to tree and to list
		if (root == null)
		{
			root = newNode;
			return;
		}

		// Add Node into sorted arraylist
		TreeNode<T, V> ptr = root;
		if (root.value == value)
			return;
		while (ptr != null)
		{
			// Return if value to add is the same as the node's value
			if (ptr.value == value)
				return;

			if (ptr.key.compareTo(newNode.key) == 0)
			{
				ptr.value = value;
			} else if (ptr.key.compareTo(newNode.key) > 0)
			{
				if (ptr.left == null)
				{
					ptr.left = newNode;
					return;
				}
				ptr = ptr.left;
			} else
			{
				if (ptr.right == null)
				{
					ptr.right = newNode;
					return;
				}
				ptr = ptr.right;
			}
		}
	}

	@Override
	public String toString()
	{
		if (root == null)
			return "";
		return root.toString();
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
	 * Deletes a node containing key from the BST if it exists
	 */
	public void delete(T key)
	{
		if (root == null)
			return;

		if (key == root.key)
		{
			// Assign placeholder node for root's right's left node
			TreeNode<T, V> foo = root.right.left;

			// Replace the root of the tree with the root's right node
			root.right.left = root.left;
			root = root.right;

			// Find a new spot for the placeholder node
			TreeNode<T, V> ptr = root.left;
			while (ptr.right != null)
				ptr = ptr.right;
			ptr.right = foo;
			return;
		}


		TreeNode ptr = root;
		TreeNode lastPtr = root;
		boolean isLeft = true;

		while (ptr != null)
		{
			// Remove node
			if (ptr.key.compareTo(key) == 0)
			{
				// Remove leaf node
				if (ptr.left == null && ptr.right == null)
					if (isLeft)
						lastPtr.left = null;
					else
						lastPtr.right = null;

				// Remove if left child is null
				else if (ptr.left == null)
					if (isLeft)
						lastPtr.left = ptr.right;
					else
						lastPtr.right = ptr.right;

				// Remove if right child is null
				else if (ptr.right == null)
					if (isLeft)
						lastPtr.left = ptr.left;
					else
						lastPtr.right = ptr.left;

				// Remove if Node has 2 child Nodes
				else
					if (isLeft)
					{
						// Create placeholder for node to be remove's right's left node
						TreeNode<T, V> foo = ptr.right.left;
						// Replace node to be deleted with node to be deleted's right node
						ptr.right.left = ptr.left;
						lastPtr.left = ptr.right;

						// Put placeholder node back into the tree
						ptr = lastPtr.left.left;
						while (ptr.right != null)
							ptr = ptr.right;
						ptr.right = foo;
					}
					else
					{
						// Create placeholder for node to be remove's left's right node
						TreeNode<T, V> foo = ptr.left.right;
						// Replace node to be deleted with node to be deleted's left node
						ptr.left.right = ptr.right;
						lastPtr.right = ptr.left;

						// Put placeholder node back into the tree
						ptr = lastPtr.right.right;
						while (ptr.left != null)
							ptr = ptr.left;
						ptr.left = foo;
					}

				return;
			}
			// Iterate to
			if (ptr.key.compareTo(key) > 0)
			{
				lastPtr = ptr;
				ptr = ptr.left;
				isLeft = true;
			}
			else
			{
				lastPtr = ptr;
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
		if (root == null)
			return null;
		return root.inorderRec();
	}


	/**
	 * Find the kth smallest element in the BST
	 *
	 * @param k
	 * @return the kth smallest element in the BST
	 */
	public V kthSmallest(int k)
	{
		return null;
	}

	public void removeAll()
	{
		root = null;
	}

	/**
	 * @param <T> key
	 * @param <V> value
	 */
	static class TreeNode<T extends Comparable<T>, V> implements Comparable<T>
	{
		private T key;
		private V value;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(T key, V value)
		{
			this.key = key;
			this.value = value;
			left = right = null;
		}

		private List<V> inorderRec()
		{
			List<V> list = new ArrayList<V>();

			if (left != null)
				for (V v : (List<V>) left.inorderRec())
					list.add(v);

			list.add(value);

			if (right != null)
				for (V v : (List<V>) right.inorderRec())
					list.add(v);
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
			StringBuilder sb = new StringBuilder();

			if (left != null)
				sb.append(" " + left.toString());
			sb.append(" " + value.toString());
			if (right != null)
				sb.append(" " + right.toString());

			return sb.substring(1);
		}
	}

	public static void main(String[] args)
	{
		BinarySearchTree<Integer, Character> tree = new BinarySearchTree<Integer, Character>();
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

		System.out.println(tree);

		// Delete 4, then 9
		tree.delete(4);
		tree.delete(9);
		tree.delete(2);

		System.out.println(tree);

		// Search 12 then search 4.
		System.out.println(tree.search(12));
		System.out.println(tree.search(4));
		System.out.println(tree.inorderRec());

	}
}
