import org.junit.*;

/**
 * The TestNumLinkedList class tests the NumLinkedList class
 * 
 * @author ari
 *
 */
public class TestNumLinkedList {

	/**
	 * This method tests most of the NumLinkedList class
	 */
	@Test
	public void test() {
		NumList list = new NumLinkedList();

		Assert.assertEquals(Integer.MAX_VALUE, list.capacity());
		Assert.assertEquals(0, list.size());

		for (int i = 1; i <= 10; i++)
			list.insert(100, i);
		Assert.assertEquals(10, list.size());
		Assert.assertEquals("1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0", list.toString());
		Assert.assertTrue(list.isSorted());
		list.reverse();
		Assert.assertEquals("10.0 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0 1.0", list.toString());
		Assert.assertFalse(list.isSorted());
		list.reverse();
		Assert.assertEquals("1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0", list.toString());

		Assert.assertFalse(list.contains(3.14159));
		list.insert(0, 3.14159);
		list.insert(list.size() / 2, 3.14159);
		list.insert(list.size(), 3.14159);
		Assert.assertEquals("3.14159 1.0 2.0 3.0 4.0 3.14159 5.0 6.0 7.0 8.0 9.0 10.0 3.14159", list.toString());

		Assert.assertTrue(list.contains(3.14159));
		Assert.assertEquals(3.14159, list.lookup(0), 3.14159);
		Assert.assertEquals(3.14159, list.lookup(7), 6.0);
		Assert.assertEquals(3.14159, list.lookup(list.size() - 1), 3.14159);
		list.remove(0);
		list.remove(list.size() - 1);
		Assert.assertEquals("1.0 2.0 3.0 4.0 3.14159 5.0 6.0 7.0 8.0 9.0 10.0", list.toString());
	}

	/**
	 * Tests equals method for NumLinkedList class and NumArrayList class
	 */
	@Test
	public void testArrayListEquals() {
		NumList list1 = new NumLinkedList();
		NumList list2 = new NumArrayList(2);
		for (int i = 0; i < 5; i++) {
			list1.add(i);
			list2.add(i);
		}
		Assert.assertTrue(list1.equals(list1));
		Assert.assertTrue(list1.equals(list2));
		Assert.assertTrue(list2.equals(list1));
		list1.add(0);
		Assert.assertFalse(list1.equals(list2));
		Assert.assertFalse(list2.equals(list1));
	}

	/**
	 * Tests the union method
	 */
	@Test
	public void testUnion() {
		// Lists of two different types
		NumList list1 = new NumArrayList(5);
		NumList list2 = new NumLinkedList();

		// Add elements to each list
		for (int i = 0; i < 5; i++) {
			list1.add(i);
			list1.add(i);
		}
		for (int i = 5; i < 10; i++) {
			list2.add(i);
			list2.add(i);
		}

		// Union method calls removeDuplicates method
		// Test adding linked list elements to an arraylist
		list1 = list1.union(list1, list2);
		Assert.assertEquals("0.0 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0", list1.toString());
		for (int i = 0; i < 5; i++)
			list1.remove(5);

		// Test adding arraylist elements to a linked list

		list2 = new NumArrayList().union(list2, list1);
		Assert.assertEquals("0.0 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0", list2.toString());

		// Test adding arraylist elements to a linked list
		list1 = new NumArrayList().union(list1, list1);
		Assert.assertEquals("0.0 1.0 2.0 3.0 4.0", list1.toString());
	}
}
