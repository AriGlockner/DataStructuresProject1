

public class AVLTree<T extends Comparable<T>, V> extends BinarySearchTree<T, V>
{

	public AVLTree()
	{
		super();
	}

	/**
	 * Overrides the insert method in the BST class to insert the node into the tree and then to balance out the tree
	 *
	 * @param key - value to be sorted by
	 * @param value - the value the node contains
	 */
	@Override
	public void insert(T key, V value)
	{
		// Insert node into tree
		super.insert(key, value);

		// Balance the tree
	}

	/**
	 * Overrides the delete method in the BST class to delete the node from the tree and then to balance out the tree
	 *
	 * @param key - value to be sorted by
	 */
	@Override
	public void delete(T key)
	{
		// Delete node from tree
		super.delete(key);

		// Balance the tree
	}

	private void rotateRight(TreeNode n)
	{

	}


	public static void main(String[] args)
	{
		AVLTree<Integer, Integer> tree = new AVLTree<>();

	}
}
