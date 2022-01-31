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

	// No @Test annotation because method is called from testInsert method
	public NumArrayList[] testCapacity() {
		// Create list objects
		NumArrayList list1 = new NumArrayList(1);
		NumArrayList list2 = new NumArrayList(2);
		NumArrayList list3 = new NumArrayList(5);
		NumArrayList list4 = new NumArrayList(10);

		// Test capacity method
		Assert.assertEquals(0, new NumArrayList().capacity());
		Assert.assertEquals(1, list1.capacity());
		Assert.assertEquals(2, list2.capacity());
		Assert.assertEquals(5, list3.capacity());
		Assert.assertEquals(10, list4.capacity());

		// Return lists 1, 2, 3, & 4 for use in testInsert and removeDuplicates methods
		return new NumArrayList[] { list1, list2, list3, list4 };
	}

	// No @Test because method is called from testRemoveDuplicates method
	public NumArrayList[] testInsert() {
		// Get empty lists created in testCapacity method
		NumArrayList[] lists = testCapacity();

		// For each list, test insert method
		for (NumArrayList l : lists) {
			// Test empty
			l.insert(0, 1.0);
			Assert.assertEquals("1.0", l.toString());
			// Test add to back and add method
			l.insert(1, 3.0);
			Assert.assertEquals("1.0 3.0", l.toString());
			// Test add in between
			l.insert(1, 2.0);
			Assert.assertEquals("1.0 2.0 3.0", l.toString());
			// Test insert with index greater than size
			l.insert(Integer.MAX_VALUE, 4.0);
			Assert.assertEquals("1.0 2.0 3.0 4.0", l.toString());
			// Test add to front
			l.insert(0, 0.0);
			Assert.assertEquals("0.0 1.0 2.0 3.0 4.0", l.toString());
		}

		// Test Equals & Lookup Methods
		Assert.assertTrue(lists[0].equals(lists[0]));
		Assert.assertFalse(lists[0].equals(lists[1]));

		// Create new NumArrayList with same elements as lists[3]
		NumArrayList foobar = new NumArrayList(lists[3].capacity());
		int size = lists[3].size();
		for (int i = 0; i < size; i++)
			foobar.add(lists[3].lookup(i));

		// Check to see if newly created NumArrayList has same elements as list[3]
		Assert.assertTrue(lists[3].equals(foobar));
		// Add another element to foobar to see if the lists are not equal
		foobar.add(555555555.6);
		Assert.assertFalse(lists[3].equals(foobar));

		// Tests contains and lookup methods
		Assert.assertTrue(lists[1].contains(lists[1].lookup(0)));
		Assert.assertEquals(0.0, 0.0, lists[1].lookup(0));

		// Return method so it can be used for other method
		return lists;
	}

	@Test
	public void removeDuplicates() {
		NumArrayList[] lists = testInsert();

		// Test removeDuplicates method
		for (NumArrayList l : lists) {
			// Add duplicate values into the list
			l.add(0.0);
			l.insert(8, 1.0);
			l.insert(3, 1.0);
			l.insert(2, 2.0);
			Assert.assertFalse(l.isSorted());
			Assert.assertEquals("0.0 1.0 2.0 2.0 1.0 3.0 4.0 0.0 1.0", l.toString());
			
			// Remove duplicates from list
			l.removeDuplicates();
			Assert.assertEquals("0.0 1.0 2.0 3.0 4.0", l.toString());
			Assert.assertTrue(l.isSorted());
		}

		// Test remove first element in list
		lists[2].remove(0);
		Assert.assertEquals("1.0 2.0 3.0 4.0", lists[2].toString());
		// Test remove where index > list size
		lists[2].remove(Integer.MAX_VALUE);
		Assert.assertEquals("1.0 2.0 3.0 4.0", lists[2].toString());
	}
}
