import org.junit.*;


public class TestNumLinkedList {
	
	@Test
	public void test() {
		NumLinkedList foo = new NumLinkedList();
		
		foo.insert(0, 1.0);
		Assert.assertEquals("1.0", foo.toString());
		foo.insert(1, 3.0);
		Assert.assertEquals("1.0 3.0", foo.toString());
		foo.insert(1, 2.0);
		Assert.assertEquals("1.0 2.0 3.0", foo.toString());
		foo.insert(0, 0.0);
		Assert.assertEquals("0.0 1.0 2.0 3.0", foo.toString());
		
		
		// Test Remove
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
}
