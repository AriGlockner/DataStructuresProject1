import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestNumArrayList {
	NumArrayList list1 = new NumArrayList(0);
	NumArrayList list2 = new NumArrayList(1);
	NumArrayList list3 = new NumArrayList(2);
	NumArrayList list4 = new NumArrayList(5);
	NumArrayList list5 = new NumArrayList(10);
	
	/*
	 * List of methods in NumArrayList:
	 * 
	 * 2 Constructors -> tested when declaring NumArrayList objects
	 * size -> tested in add/insert/remove/removeDuplicates
	 * capacity
	 * add -> tested in insert method when i > size
	 * insert
	 * remove -> tested all cases except remove from front in removeDuplicates method
	 * removeDuplicates
	 * toString
	 * increaseListSize -> tested in add/insert methods
	 */
	
	@Test
	public void testCapacity() {
		
	}
	
	@Test
	public void testInsert() {
		
	}
	
	@Test
	public void testRemove() {
		
	}
	
	@Test
	public void removeDuplicates() {
		
	}
}
