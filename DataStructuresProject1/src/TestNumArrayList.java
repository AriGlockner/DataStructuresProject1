import org.junit.*;

/**
 * The TestNumArrayList class tests the NumArrayList class
 * 
 * @author ari
 */

public class TestNumArrayList {

	/**
	 * Tests add/size/capacity/toString methods for ArrayLists of different sizes
	 */
	@Test
	public void testArrayCapacities() {
		NumList[] lists = new NumArrayList[] { new NumArrayList(1), new NumArrayList(2), new NumArrayList(5),
				new NumArrayList(9) };
		NumList list0 = new NumArrayList(0);
		Assert.assertEquals(0, list0.capacity());
		Assert.assertEquals(1, lists[0].capacity());
		Assert.assertEquals(2, lists[1].capacity());
		Assert.assertEquals(5, lists[2].capacity());
		Assert.assertEquals(9, lists[3].capacity());

		for (int i = 0; i < 10; i++) {
			for (NumList l : lists)
				l.add(i);
		}

		for (NumList l : lists) {
			Assert.assertEquals("0.0 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0", l.toString());
			Assert.assertEquals(10, l.size());
		}
		Assert.assertEquals(10, lists[0].capacity());
		Assert.assertEquals(10, lists[1].capacity());
		Assert.assertEquals(10, lists[2].capacity());
		Assert.assertEquals(18, lists[3].capacity());
	}

	/**
	 * Tests everything that the capacity (as long as it's not 0) doesn't apply to
	 */
	@Test
	public void testOneList() {
		NumList front = new NumArrayList(5);
		NumList back = new NumArrayList(5);

		for (int i = 0; i < 5; i++)
			front.add(i);
		for (int i = 5; i < 10; i++)
			back.add(i);

		NumList list = new NumArrayList().union(front, back);

		Assert.assertEquals("0.0 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0", list.toString());
		Assert.assertTrue(list.isSorted());
		list.insert(0, 2.718);
		list.insert(1234567890, 2.718);
		list.insert(3, 3.14159);
		Assert.assertFalse(list.isSorted());
		Assert.assertEquals("2.718 0.0 1.0 3.14159 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 2.718", list.toString());
		list.removeDuplicates();
		Assert.assertEquals("2.718 0.0 1.0 3.14159 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0", list.toString());
		Assert.assertTrue(list.contains(9.0));
		Assert.assertFalse(list.contains(10.0));
		Assert.assertEquals(2.718, list.lookup(0), 2.718);
		list.reverse();
		Assert.assertEquals("9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0 3.14159 1.0 0.0 2.718", list.toString());
		list.reverse();
		Assert.assertEquals("2.718 0.0 1.0 3.14159 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0", list.toString());
	}
}
