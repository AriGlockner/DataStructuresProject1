

public class BinarySearchTree
{

	public void insert(int key)
	{

	}

	public Node Search(int key)
	{
		return new Node();
	}

	public void delete(int key)
	{

	}

	public void inorderRec()
	{

	}

	public Node kthSmallest(int k)
	{
		return null;
	}


	public static void main(String[] args)
	{
		// 1) Construct an empty BinarySearchTree
		BinarySearchTree tree = new BinarySearchTree();

		// 2) Insert: 2, 1, 4, 5, 9, 3, 6, 7, 10, 12, 11
		tree.insert(2);
		tree.insert(1);
		tree.insert(4);
		tree.insert(5);
		tree.insert(9);
		tree.insert(3);
		tree.insert(6);
		tree.insert(7);
		tree.insert(10);
		tree.insert(12);
		tree.insert(11);

		// 3) Delete 4 then delete 9
		tree.delete(4);
		tree.delete(9);

		// 4) Print the keys using inorder traversal

		// 5) Search 12 then search 4
		System.out.println(tree.Search(12));
		System.out.println(tree.Search(4));

		// 6) Find the 3rd smallest element in the tree
		System.out.println(tree.kthSmallest(3));
	}
}
