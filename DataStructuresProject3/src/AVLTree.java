import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Comparable<T>, V> implements BinarySearchTreeInterface<T, V>
{
	private TreeNode<T, V> root;

	public AVLTree()
	{
		root = null;
	}

	@Override
	public void insert(T key, V value)
	{
		TreeNode<T, V> newNode = new TreeNode<T, V>(key, value);

		if (root == null)
		{
			root = newNode;
			return;
		}
	}

	@Override
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

	@Override
	public void delete(T key)
	{

	}

	@Override
	public V kthSmallest(int k)
	{
		return null;
	}

	@Override
	public List<V> inorderRec()
	{
		return null;
	}

	/**
	 * This class represents a TreeNode for the AVLTree Class. A TreeNode contains a value,
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
		private TreeNode<T, V> left;
		private TreeNode<T, V> right;

		public TreeNode(T key, V value)
		{
			this.key = key;
			this.value = value;
			left = right = null;
		}

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

		@Override
		public int compareTo(T o)
		{
			return (key.compareTo(o));
		}

		@Override
		public String toString()
		{
			StringBuilder sb = new StringBuilder();

			if (left != null) sb.append(" ").append(left);
			sb.append(" ").append(value.toString());
			if (right != null) sb.append(" ").append(right);

			return sb.substring(1);
		}
	}

	@Override
	public String toString() {
		if (root == null) return "";
		return root.toString();
	}

	/**
	 * Demo
	 *
	 * @param args arguments
	 */
	public static void main(String[] args)
	{
		AVLTree<Integer, Character> tree = new AVLTree<>();
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
		BinarySearchTreeInterface<Double, String> tree2 = new AVLTree<>();
		String[] strings2 = new String[] {"kajdfci", "qiduxh", "cvaebr", "k", "dchuvabjkk"};

		for (int i = 0; i < 5; i++)
			tree2.insert((double) i, strings2[i]);
		System.out.println("Expected:\tkajdfci qiduxh cvaebr k dchuvabjkk\nActual:\t\t" + tree2);

		// String, String
		BinarySearchTreeInterface<String, String> tree3 = new AVLTree<>();
		String[] strings3 = new String[] {"ycienso", ";socje", "3.14159", "abcdefg", "JDxjnadFjk"};

		for (int i = 0; i < 5; i++)
			tree3.insert(strings3[i], strings3[i]);
		System.out.println("Expected:\t3.14159 ;socje JDxjnadFjk abcdefg ycienso\nActual:\t\t" + tree3);
	}
}
