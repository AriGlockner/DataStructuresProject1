import java.util.*;

/**
 *
 * @param <T> key
 * @param <V> value
 */
public class BinarySearchTree<T, V>
{
	private TreeNode<T, V> root;

	public BinarySearchTree()
	{
		root = null;
	}



	/**
	 * Inserts a node containing key in the BST
	 */
	public void insert(T key)
	{
		if (root == null)
		{

		}
	}

	/**
	 * Searches for a node with a specific key in the BST
	 *
	 * @return a node with a specific key in the BST
	 */
	public V search(T key)
	{
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
		return null;
	}

	/**
	 * Find the kth smallest element in the BST
	 *
	 * @param k
	 * @return the kth smallest element in the BST
	 */
	public V kthSmallest(T k)
	{
		return null;
	}

	/**
	 *
	 * @param <T> key
	 * @param <V> value
	 */
	static class TreeNode<T, V> implements Comparable
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
		public int compareTo(Object o)
		{
			return 0;
		}
	}

	public static void main(String[] args)
	{
		BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>();
	}
}
