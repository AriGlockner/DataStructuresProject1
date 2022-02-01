import org.junit.*;


public class TestNumLinkedList {
	
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
		Assert.assertEquals("1.0 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0", bar.toString());
		bar.removeDuplicates();
		Assert.assertEquals("1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0", bar.toString());
	}
}
