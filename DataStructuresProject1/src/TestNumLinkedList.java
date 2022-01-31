import org.junit.*;


public class TestNumLinkedList {
	
	@Test
	public void test() {
		NumLinkedList foo = new NumLinkedList();
		
		// Test add
		Assert.assertEquals(0, foo.size());
		foo.add(1.0);
		Assert.assertEquals(1, foo.size());
		foo.add(2.0);
		Assert.assertEquals(2, foo.size());
		foo.add(3.0);
		Assert.assertEquals(3, foo.size());
		
		// Test Remove
		foo.remove(-1);
		Assert.assertEquals(3, foo.size());
		foo.remove(3);
		Assert.assertEquals(3, foo.size());
		foo.remove(1);
		Assert.assertEquals(2, foo.size());
		foo.remove(1);
		Assert.assertEquals(1, foo.size());
		foo.remove(0);
		Assert.assertEquals(0, foo.size());
	}
}
