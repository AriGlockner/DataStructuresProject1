import org.junit.*;

/**
 * The TestNumLinkedList class tests the NumLinkedList class
 * 
 * @author ari
 *
 */

public class TestNumLinkedList {

	/*
	@Test
	public void test() {
		NumLinkedList foo = new NumLinkedList();

		// Test add/insert
		foo.insert(0, 1.0);
		Assert.assertEquals("1.0", foo.toString());
		foo.insert(1, 3.0);
		Assert.assertEquals("1.0 3.0", foo.toString());
		foo.insert(1, 2.0);
		Assert.assertEquals("1.0 2.0 3.0", foo.toString());
		foo.insert(0, 0.0);
		Assert.assertEquals("0.0 1.0 2.0 3.0", foo.toString());

		// Test isSorted
		Assert.assertTrue(foo.isSorted());
		foo.insert(0, 4.0);
		Assert.assertFalse(foo.isSorted());

		// Test Contains
		Assert.assertTrue(foo.contains(0.0));
		Assert.assertFalse(foo.contains(5.0));

		// Test Remove
		foo.remove(0);
		foo.remove(-1);
		Assert.assertEquals(4, foo.size());
		Assert.assertEquals("0.0 1.0 2.0 3.0", foo.toString());
		foo.remove(4);
		Assert.assertEquals(4, foo.size());
		Assert.assertEquals("0.0 1.0 2.0 3.0", foo.toString());
		foo.remove(3);
		Assert.assertEquals(3, foo.size());
		Assert.assertEquals("0.0 1.0 2.0", foo.toString());
		foo.remove(1);
		Assert.assertEquals(2, foo.size());
		Assert.assertEquals("0.0 2.0", foo.toString());
		foo.remove(0);
		Assert.assertEquals(1, foo.size());
		Assert.assertEquals("2.0", foo.toString());
		foo.remove(0);
		Assert.assertEquals(0, foo.size());
	}

	@Test
	public void testRemoveDuplicates() {
		NumLinkedList bar = new NumLinkedList();

		for (double d = 1.0; d <= 10.0; d++) {
			bar.add(d);
			bar.insert((int) d, d);
		}
		Assert.assertEquals("1.0 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0",
				bar.toString());
		bar.removeDuplicates();
		Assert.assertEquals("1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0", bar.toString());
	}
	*/
	
	@Test
	public void test() {
		NumLinkedList list = new NumLinkedList();
		
		Assert.assertEquals(Integer.MAX_VALUE, list.capacity());
		Assert.assertEquals(0, list.size());
		
		for (int i = 1; i <= 10; i++)
			list.insert(100, i);
		Assert.assertEquals(10, list.size());
		Assert.assertEquals("1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0", list.toString());
		Assert.assertTrue(list.isSorted());
		///*
		list.reverse();
		Assert.assertEquals("10.0 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0 1.0", list.toString());
		Assert.assertFalse(list.isSorted());
		list.reverse();
		//*/
		Assert.assertEquals("1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0", list.toString());
		
		Assert.assertFalse(list.contains(3.14159));
		list.insert(0, 3.14159);
		list.insert(list.size()/2, 3.14159);
		list.insert(list.size(), 3.14159);
		Assert.assertEquals("3.14159 1.0 2.0 3.0 4.0 3.14159 5.0 6.0 7.0 8.0 9.0 10.0 3.14159", list.toString());
		
		Assert.assertTrue(list.contains(3.14159));
		Assert.assertEquals(3.14159, list.lookup(0), 3.14159);
		Assert.assertEquals(3.14159, list.lookup(7), 6.0);
		Assert.assertEquals(3.14159, list.lookup(list.size() - 1), 3.14159);
		list.removeDuplicates();
		Assert.assertEquals("3.14159 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0", list.toString());
		list.remove(0);
		Assert.assertEquals("1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0", list.toString());
	}
	
	@Test
	public void testArrayListEqualToLinkedList() {
		NumLinkedList list1 = new NumLinkedList();
		NumArrayList list2 = new NumArrayList(2);
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
}
