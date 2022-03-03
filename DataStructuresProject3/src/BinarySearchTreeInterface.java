import java.util.ArrayList;
import java.util.List;

public interface BinarySearchTreeInterface<T extends Comparable<T>, V>
{
	/**
	 * Inserts a node containing key in the BST
	 */
	void insert(T key, V value);

	/**
	 * Searches for a node with a specific key in the BST
	 *
	 * @return a node with a specific key in the BST
	 */
	V search(T key);

	/**
	 * Deletes a node containing key from the BST if it exists
	 */
	void delete(T key);

	/**
	 * Find the kth the smallest element in the BST
	 *
	 * @param k the kth the smallest value in the tree to return
	 * @return the kth the smallest element in the BST
	 */
	default V kthSmallest(int k) {
		return inorderRec().get(k - 1);
	}

	/**
	 * @return a list of values in inorder traversal of the BST implemented using
	 * recursion
	 */
	List<V> inorderRec();
}
