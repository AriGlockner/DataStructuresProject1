import java.util.*;

public class BinarySearchTree<T extends Comparable<T>, V>
{
	private BSTNode<T, V> root;
	private int size;

	public BinarySearchTree()
	{
		root = null;
	}

	static class BSTNode<T extends Comparable<T>, V> implements Comparable<BSTNode<T, V>>
	{
		private T key;
		private V value;

		private BSTNode<T, V> left;
		private BSTNode<T, V> right;

		private BSTNode<T, V> parent;

		public BSTNode(T key, V value, BSTNode<T, V> parent)
		{
			this.key = key;
			this.value = value;
			this.parent = parent;
		}

		@Override
		public int compareTo(BSTNode<T, V> o)
		{
			return key.compareTo(o.key);
		}
		public List<V> preorder()
		{
			List<V> list = new ArrayList<>();

			// Visit Root
			list.add(value);

			// Preform preorder transversal of left subtree
			if (left != null)
				list.addAll(left.preorder());

			// Preform preorder transversal of right subtree
			if (right != null)
				list.addAll(right.preorder());

			return list;
		}

		public List<V> postorder()
		{
			List<V> list = new ArrayList<>();

			// preform postorder of left subtree
			if (left != null)
				list.addAll(left.postorder());

			// preform postorder of right subtree
			if (right != null)
				list.addAll(right.postorder());

			// Visit root
			list.add(value);

			return list;
		}

		public List<V> inorder()
		{
			List<V> list = new ArrayList<>();

			// left
			if (left != null)
				list.addAll(left.inorder());

			// root
			list.add(value);

			// right
			if (right != null)
				list.addAll(right.inorder());

			return list;
		}

	}

	public void insert(T key, V value)
	{
		if (root == null)
		{
			root = new BSTNode<>(key, value, null);
			return;
		}

		size++;
		insertNode(new BSTNode<>(key, value, null));
	}

	private void insertNode(BSTNode<T, V> newNode)
	{
		BSTNode<T, V> ptr = root;

		while (ptr != null)
		{
			int diff = newNode.compareTo(ptr);

			if (diff == 0)
				return;
			if (diff > 0)
			{
				if (ptr.right == null)
				{
					ptr.right = newNode;
					newNode.parent = ptr;
					return;
				}
				ptr = ptr.right;
			} else
			{
				if (ptr.left == null)
				{
					ptr.left = newNode;
					newNode.parent = ptr;
					return;
				}
				ptr = ptr.left;
			}
		}
	}

	public V search(T key)
	{
		BSTNode<T, V> compare = new BSTNode<>(key, null, null);
		BSTNode<T, V> ptr = root;

		while (ptr != null)
		{
			int diff = compare.compareTo(ptr);

			if (diff == 0)
				return ptr.value;

			if (diff > 0)
				if (ptr.right == null)
					return null;
				else
					ptr = ptr.right;
			else if (ptr.left == null)
				return null;
			else
				ptr = ptr.left;
		}

		return null;
	}

	public void delete(T key)
	{
		BSTNode<T, V> ptr = root;

		while (ptr.key != key)
		{
			if (key.compareTo(ptr.key) > 0)
				if (ptr.right == null)
					return;
				else
					ptr = ptr.right;
			else
				if (ptr.left == null)
					return;
				else
					ptr = ptr.left;
		}
		size--;

		// ptr is not root
		if (ptr.parent != null)
		{
			// ptr is node to remove
			boolean onLeftSide = (ptr.parent.left.compareTo(ptr) == 0);

			// Left of ptr is null
			if (ptr.left == null)
			{
				if (onLeftSide)
					ptr.parent.left = ptr.right;
				else
					ptr.parent.right = ptr.right;
				return;
			}

			if (ptr.right == null)
			{
				if (onLeftSide)
					ptr.parent.left = ptr.left;
				else
					ptr.parent.right = ptr.left;
				return;
			}

			// both left and right exist
			if (onLeftSide)
				ptr.parent.left = ptr.left;
			else
				ptr.parent.right = ptr.left;
			insertNode(ptr.right);
		}
		else
		{
			// Left of ptr is null
			if (ptr.left == null)
			{
				root = ptr.right;
				if (size > 0)
					root.parent = null;
				return;
			}


			root = ptr.left;

			if (size > 0)
				insertNode(ptr.right);
			root.parent = null;
		}
	}

	public List<V> preorderTraversal()
	{
		return root.preorder();
	}

	public List<V> postorderTraversal()
	{
		// Left - Right -

		return root.postorder();
	}

	public List<V> inorderTraversal()
	{
		return root.inorder();
	}

	public List<V> levelOrderTraversal()
	{
		List<V> list = new ArrayList<>(size);

		Queue<BSTNode<T, V>> queue = new ArrayDeque<>();
		queue.add(root);

		while (!queue.isEmpty())
		{
			BSTNode<T, V> ptr = queue.remove();
			list.add(ptr.value);

			if (ptr.left != null)
				queue.add(ptr.left);
			if (ptr.right != null)
				queue.add(ptr.right);
		}

		return list;
	}

	@Override
	public String toString()
	{
		return inorderTraversal().toString();
	}

	public static void main(String[] args)
	{
		BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();

		int[] nums = new int[] {2, 1, 4, 5, 9, 3, 6, 7, 10, 12, 11};
		for (int num : nums)
			bst.insert(num, num);

		System.out.println(bst.preorderTraversal());
		System.out.println(bst.postorderTraversal());
		System.out.println(bst.inorderTraversal());
		System.out.println(bst.levelOrderTraversal());

		bst.delete(2);
		System.out.println();

		System.out.println(bst.preorderTraversal());
		System.out.println(bst.postorderTraversal());
		System.out.println(bst.inorderTraversal());
		System.out.println(bst.levelOrderTraversal());
		//System.out.println(bst.search(5));
		//System.out.println(bst.search(9090));
	}
}
