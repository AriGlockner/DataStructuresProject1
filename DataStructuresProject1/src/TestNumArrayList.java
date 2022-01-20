import org.junit.Assert;
import org.junit.Test;

public class TestNumArrayList {

	/** List of methods in NumArrayList: **/
	// 2 Constructors -> tested when declaring NumArrayList objects
	// size -> tested in add/insert/remove/removeDuplicates capacity
	// add -> tested in insert method when i > size insert
	// insert -> tested in testInsert method
	// remove -> tested in testRemoveDuplicates method
	// removeDuplicates -> tested in removeDuplicates method
	// toString -> tested in testInsert method
	// increaseListSize -> tested in add/insert methods
	// contains/lookup -> tested in lookup method
	// equals -> tested in testInsert method

	// No @Test because method is called from testInsert method
	public NumArrayList[] testCapacity() {
		NumArrayList list1 = new NumArrayList(0);
		NumArrayList list2 = new NumArrayList(1);
		NumArrayList list3 = new NumArrayList(2);
		NumArrayList list4 = new NumArrayList(5);
		NumArrayList list5 = new NumArrayList(10);
		Assert.assertEquals(0, list1.capacity());
		Assert.assertEquals(1, list2.capacity());
		Assert.assertEquals(2, list3.capacity());
		Assert.assertEquals(5, list4.capacity());
		Assert.assertEquals(10, list5.capacity());
		return new NumArrayList[] { list2, list3, list4, list5 };
	}

	// No @Test because method is called from testRemoveDuplicates method
	public NumArrayList[] testInsert() {
		/*
		NumArrayList list1 = new NumArrayList(1);
		NumArrayList list2 = new NumArrayList(2);
		NumArrayList list3 = new NumArrayList(5);
		NumArrayList list4 = new NumArrayList(10);
		*/
		NumArrayList[] lists = testCapacity();

		for (NumArrayList l : lists) {
			l.insert(0, 1.0);
			Assert.assertEquals("1.0", l.toString());
			l.insert(1, 3.0);
			Assert.assertEquals("1.0 3.0", l.toString());
			l.insert(1, 2.0);
			Assert.assertEquals("1.0 2.0 3.0", l.toString());
			l.insert(100000, 4.0);
			Assert.assertEquals("1.0 2.0 3.0 4.0", l.toString());
			l.insert(0, 0.0);
			Assert.assertEquals("0.0 1.0 2.0 3.0 4.0", l.toString());
		}

		// Test Equals & Lookup Methods
		Assert.assertTrue(lists[0].equals(lists[1]));

		int size = lists[3].size();
		NumArrayList foobar = new NumArrayList(lists[3].capacity());
		for (int i = 0; i < size; i++)
			foobar.add(lists[3].lookup(i));

		Assert.assertTrue(lists[3].equals(foobar));
		foobar.add(555555555.6);
		Assert.assertFalse(lists[3].equals(foobar));

		Assert.assertTrue(lists[1].contains(lists[1].lookup(0)));
		//Assert.assertEquals(0.0, lists[1].lookup(0));
		Assert.assertEquals(0.0, 0.0, lists[1].lookup(0));
		
		// Return method so it can be used for other method
		return lists;
	}

	@Test
	public void removeDuplicates() {
		NumArrayList[] lists = testInsert();

		// Test removeDuplicates method
		for (NumArrayList l : lists) {
			// Before adding duplicates
			Assert.assertEquals("0.0 1.0 2.0 3.0 4.0", l.toString());

			// After Adding duplicates
			l.add(0.0);
			l.insert(8, 1.0);
			l.insert(3, 1.0);
			l.insert(2, 2.0);
			Assert.assertEquals("0.0 1.0 2.0 2.0 1.0 3.0 4.0 0.0 1.0", l.toString());

			// After removing duplicates
			l.removeDuplicates();
			Assert.assertEquals("0.0 1.0 2.0 3.0 4.0", l.toString());
		}

		// Test remove method
		Assert.assertEquals("0.0 1.0 2.0 3.0 4.0", lists[2].toString());
		lists[2].remove(0);
		Assert.assertEquals("1.0 2.0 3.0 4.0", lists[2].toString());
		lists[2].remove(Integer.MAX_VALUE);
		Assert.assertEquals("1.0 2.0 3.0 4.0", lists[2].toString());
	}
}
