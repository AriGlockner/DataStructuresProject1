import org.junit.*;
import java.util.HashMap;

public class TestBinarySearchTree
{
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
		tree.delete(4);
		Assert.assertNull(tree.search(4));
		Assert.assertEquals("[B, D, E, G, H]", tree.inorderRec().toString());

		// Remove Root
		Assert.assertEquals("B", tree.search(0).toString());
		tree.delete(0);
		Assert.assertNull(tree.search(0));
		Assert.assertEquals("[D, E, G, H]", tree.inorderRec().toString());


		// Delete when tree is Empty
		tree = new BinarySearchTree<>();
		Assert.assertEquals("", tree.toString());
		tree.delete(4);
		Assert.assertEquals("", tree.toString());
	}

	/**
	 * Tests the BST with other generic types than the ones tested in the testBinarySearchTree method
	 */
	@Test
	public void testOtherGenericTypes()
	{
		// Test String key and Integer value
		BinarySearchTree<String, Integer> tree1 = new BinarySearchTree<>();
		tree1.insert("czpfdje d\\f", 3);
		tree1.insert("xcygadfcxjv", 4);
		tree1.insert(" udbeicicmv", 0);
		tree1.insert("cvyebafci9c", 2);
		tree1.insert("adjtdfjcjvh", 1);
		Assert.assertEquals("[0, 1, 2, 3, 4]", tree1.inorderRec().toString());

		// Test Long key and Object value
		BinarySearchTree<Long, Object> tree2 = new BinarySearchTree<>();
		tree2.insert(100L, "The duck does not eat the bear");
		tree2.insert(Long.MAX_VALUE, new HashMap<>());
		tree2.insert(Long.MAX_VALUE, new Exception("This exception is useless and is just for fun"));
		tree2.insert(0L, "A queue is just like ordering at the den");
		tree2.insert(-4L, Math.PI);
		Assert.assertEquals("3.141592653589793 A queue is just like ordering at the den The duck does not eat"
				+ " the bear java.lang.Exception: This exception is useless and is just for fun", tree2.toString());
	}
}
