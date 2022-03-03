import org.junit.*;

import java.util.ArrayList;
import java.util.Random;

public class TestBinarySearchTree
{
	@Test
	public void test()
	{
		BinarySearchTree<Character, Character> tree = new BinarySearchTree<>();
		char c = 'B';
		ArrayList<Character> characterArrayList = new ArrayList<>();

		for (int i = 0; i < 10; i++)
			characterArrayList.add((char) (c + i));

		System.out.println(characterArrayList);

		Random rand = new Random();
		while (characterArrayList.size() > 0)
		{
			int i = rand.nextInt(characterArrayList.size());
			System.out.print(characterArrayList.get(i) + " ");
			tree.insert(characterArrayList.get(i), characterArrayList.get(i));
			characterArrayList.remove(i);
		}

		System.out.println("\nSorted: " + tree);
		System.out.println(tree.root);

		helpTest(tree.root);
	}

	void helpTest(BinarySearchTree.TreeNode<?, ?> n)
	{
		System.out.println("Node: " + n + "\tParent: " + n.parent);
		if (n.left != null)
			helpTest(n.left);

		if (n.right != null)
			helpTest(n.right);
	}


	/**
	 * Tests all methods in the BinarySearchTree class with a key of type Integer and a value of type Character
	 */
	@Test
	public void testBinarySearchTree()
	{
		char c = 'B';
		BinarySearchTree<Integer, Character> tree = new BinarySearchTree<>();

		// Test inOrderRec / insert behind / insert when empty
		for (int i = 0; i < 10; i += 2)
			tree.insert(i, (char) (c + i));
		Assert.assertEquals("[B, D, F, H, J]", tree.inorderRec().toString());

		// Test insert in front
		tree.insert(-1, 'A');
		Assert.assertEquals("[A, B, D, F, H, J]", tree.inorderRec().toString());

		// Test insert in between
		tree.insert(3, 'E');
		Assert.assertEquals("[A, B, D, E, F, H, J]", tree.inorderRec().toString());
		tree.insert(5, 'G');
		Assert.assertEquals("[A, B, D, E, F, G, H, J]", tree.inorderRec().toString());

		// Test Search / Delete

		// Remove from left
		Assert.assertEquals("A", tree.search(-1).toString());
		tree.delete(-1);
		Assert.assertNull(tree.search(-1));
		Assert.assertEquals("[B, D, E, F, G, H, J]", tree.inorderRec().toString());

		// Remove from right
		Assert.assertEquals("J", tree.search(8).toString());
		tree.delete(8);
		Assert.assertNull(tree.search(8));
		Assert.assertEquals("[B, D, E, F, G, H]", tree.inorderRec().toString());

		// Remove from middle
		Assert.assertEquals("F", tree.search(4).toString());
		System.out.println(tree.root + " " + tree.search(4));
		System.out.println(tree);
		tree.delete(4);
		System.out.println(tree);
		Assert.assertNull(tree.search(4));
		Assert.assertEquals("[B, D, E, G, H]", tree.inorderRec().toString());

		// Remove Root
		Assert.assertEquals("B", tree.search(0).toString());
		tree.delete(0);
		Assert.assertNull(tree.search(0));
		Assert.assertEquals("[D, E, G, H]", tree.inorderRec().toString());


		// Delete when tree is Empty
		tree.removeAll();
		Assert.assertEquals("", tree.toString());
		tree.delete(4);
		Assert.assertEquals("", tree.toString());
	}

	/**
	 * Tests the BST with other generic types than the ones tested in the testBinarySearchTree method
	 *
	@Test
	public void testAnotherGenericType()
	{
		BinarySearchTree<String, Integer> tree1 = new BinarySearchTree<>();
		tree1.insert("czpfdje d\\f", 3);
		tree1.insert("xcygadfcxjv", 4);
		tree1.insert(" udbeicicmv", 0);
		tree1.insert("cvyebafci9c", 2);
		tree1.insert("adjtdfjcjvh", 1);
		Assert.assertEquals("[0, 1, 2, 3, 4]", tree1.inorderRec().toString());
	}
	*/
}
