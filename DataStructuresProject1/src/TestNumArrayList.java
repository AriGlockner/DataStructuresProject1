import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

public class TestNumArrayList {

	/** List of methods in NumArrayList: **/
	// 2 Constructors -> tested when declaring NumArrayList objects
	// size -> tested in add/insert/remove/removeDuplicates capacity
	// add -> tested in insert method when i > size insert
	// remove -> tested in testRemoveDuplicates method
	// removeDuplicates
	// toString -> tested in testInsert method
	// increaseListSize -> tested in add/insert methods
	// contains lookup -> tested in lookup method
	// equals -> tested in lookup method

	@Test
	public void testCapacity() {
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
	}

	// Commented out due to being called from testRemoveDuplicates method
	public NumArrayList[] testInsert() {
		NumArrayList list1 = new NumArrayList(1);
		NumArrayList list2 = new NumArrayList(2);
		NumArrayList list3 = new NumArrayList(5);
		NumArrayList list4 = new NumArrayList(10);
		NumArrayList[] lists = new NumArrayList[] { list1, list2, list3, list4 };

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
		Assert.assertFalse(list1.equals(list2));

		int size = list4.size();
		NumArrayList foobar = new NumArrayList(list4.capacity());
		for (int i = 0; i < size; i++)
			foobar.add(list4.lookup(i));

		Assert.assertTrue(list4.equals(foobar));
		foobar.add(555555555.6);
		Assert.assertFalse(list4.equals(foobar));

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
		// Prior
		Assert.assertEquals("0.0 1.0 2.0 3.0 4.0", lists[2].toString());
		lists[2].remove(0);
		Assert.assertEquals("1.0 2.0 3.0 4.0", lists[2].toString());
		lists[2].remove(Integer.MAX_VALUE);
		Assert.assertEquals("1.0 2.0 3.0 4.0", lists[2].toString());
	}
}
