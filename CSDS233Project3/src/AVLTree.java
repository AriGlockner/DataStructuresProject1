public class AVLTree<T extends Comparable<T>, V> extends BinarySearchTree<T, V>
{


	@Override
	public void insert(T key, V value)
	{
		// Insert like BST
		super.insert(key, value);

		// Rotate nodes to balance tree
	}

	@Override
	public void delete(T key)
	{
		// Delete like BST
		super.delete(key);

		// Rotate nodes to balance tree
	}

}
