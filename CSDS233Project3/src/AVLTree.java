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

	/**
	 * Balance the tree when the tree is unbalanced because a node is inserted into the right subtree of the right subtree
	 *
	 * @param top - top level node to be rotated
	 */
	private void balanceRightRight(TreeNode<T, V> top)
	{
		if (top == root)
		{
			root = root.right;

			// Create placeholder
			TreeNode<T, V> placeholder = root.left;
			root.left = root.parent;
			if (placeholder != null)
				root.left.right = placeholder;
			root.parent = null;

			return;
		}
	}

	public static void main(String[] args)
	{
		AVLTree<Integer, Integer> avlTree = new AVLTree<>();

		avlTree.insert(2, 2);
		avlTree.insert(1, 1);
		avlTree.insert(0, 0);

		System.out.println(avlTree);
		System.out.println(avlTree.root + " " + avlTree.root.left + " " + avlTree.root.right + " " + avlTree.root.left.left);

		avlTree.balanceRightRight(avlTree.root);

		System.out.println(avlTree);
		System.out.println(avlTree.root + " " + avlTree.root.left + " " + avlTree.root.right + " " + avlTree.root.left.left);
	}
}
