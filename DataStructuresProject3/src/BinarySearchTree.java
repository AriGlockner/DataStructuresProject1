import com.sun.source.tree.Tree;

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
		StringBuilder sb = new StringBuilder();
		//if (root)
		sb.append(" " + root.toString());
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

	}

	/**
	 * @return a list of values in inorder traversal of the BST implemented using
	 * recursion
	 */
	public List<V> inorderRec()
	{
		/*
		if (left != null)
				sb.append(" " + left.toString());
			sb.append(" " + value.toString());
			if (right != null)
				sb.append(" " + right.toString());
		 */
		List<V> list = new ArrayList<V>();

		TreeNode<T, V> lptr = root;



		list.add(root.value);

		return list;
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
		// Delete 4, then 9
		//tree.delete(4);
		//tree.delete(9);
		System.out.println(tree);

		// Search 12 then search 4.
		System.out.println(tree.search(12));
		System.out.println(tree.search(4));

	}
}
