import org.junit.*;

/**
 * This class tests the HashTable class. It tests the methods that aren't tested in the WordStat class
 */
public class TestHashTable
{
	/**
	 * Tests the put and get methods in the HashTable class
	 */
	@Test
	public void testPutAndGet()
	{
		HashTable table1 = new HashTable();
		HashTable table2 = new HashTable(7);
		HashTable table3 = new HashTable(3);

		String[] keys = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};

		// Test put(key, value, hashCode)
		for (int i = 0; i < keys.length; i++)
		{
			// put normally
			table1.put(keys[i], i * 2 + 1);
			table2.put(keys[i], i * 2 + 1);
			table3.put(keys[i], i * 2 + 1);
			// put with hashcode
			table1.put(keys[i], (i + 1) * 2, 60 + i);
			table2.put(keys[i], (i + 1) * 2, 60 + i);
			table3.put(keys[i], (i + 1) * 2, 60 + i);
		}

		// Test Put
		Assert.assertEquals("[A, 2] [B, 4] [C, 6] [D, 8] [E, 10] [A, 1] [F, 12] [B, 3] [G, 14] [C, 5] [H, 16]" +
				" [D, 7] [I, 18] [E, 9] [J, 20] [F, 11] [K, 22] [G, 13] [H, 15] [I, 17] [J, 19] [K, 21]", table1.toString());
		Assert.assertEquals("[D, 8] [F, 11] [K, 22] [E, 10] [G, 13] [A, 1] [F, 12] [H, 15] [B, 3] [G, 14] " +
				"[I, 17] [A, 2] [C, 5] [H, 16] [J, 19] [B, 4] [D, 7] [I, 18] [K, 21] [C, 6] [E, 9] [J, 20]", table2.toString());

		Assert.assertEquals("[A, 2] [B, 3] [D, 8] [E, 9] [G, 14] [H, 15] [J, 20] [K, 21] [B, 4] [C, 5] " +
				"[E, 10] [F, 11] [H, 16] [I, 17] [K, 22] [A, 1] [C, 6] [D, 7] [F, 12] [G, 13] [I, 18] [J, 19]", table3.toString());

		// Test Get
		for (int i = 0; i < keys.length; i++)
		{
			Assert.assertEquals(((i * 2) + 1), table1.get(keys[i]));
			Assert.assertEquals(((i * 2) + 1), table2.get(keys[i]));
			Assert.assertEquals(((i * 2) + 1), table3.get(keys[i]));
			Assert.assertEquals(((i + 1) * 2), table1.get(keys[i], 60 + i));
			Assert.assertEquals(((i + 1) * 2), table2.get(keys[i], 60 + i));
			Assert.assertEquals(((i + 1) * 2), table3.get(keys[i], 60 + i));
		}
	}
}
