import org.junit.*;

/**
 * This class tests the HashTable class
 */
public class TestHashTable
{
	private final String[] testStrings = new String[] {
			"a", "b", "c", "foo", "bar", "foobar", "a", "a", "a", "b", "and", "foobar",
	};

	/**
	 * This method tests the HashTable class with a constructor without parameters
	 */
	@Test
	public void testEmptyConstructor()
	{
		int count = 0;
		HashTable hashTable = new HashTable();

		// Test put(key, value)
		for (String str : testStrings)
			hashTable.put(str, count++);

		Assert.assertEquals("[and, 10] [foobar, 5] [foobar, 11] [foo, 3] [a, 0] [a, 6] [a, 7] [a, 8] [b, 1] " +
				"[b, 9] [c, 2] [bar, 4]", hashTable.toString());

		// Test get(key)
		Assert.assertEquals(5, hashTable.get("foobar"));

		hashTable.put("foobar", 11, 5);
		Assert.assertEquals("[foobar, 11] [and, 10] [foobar, 5] [foobar, 11] [foo, 3] [a, 0] [a, 6] [a, 7] " +
				"[a, 8] [b, 1] [b, 9] [c, 2] [bar, 4]", hashTable.toString());

		// Test get(key, hashcode) to make sure it is the same as get(key) in the case of a non-collision
		Assert.assertEquals(hashTable.get("and"), hashTable.get("and", 27));

		// Test get(key, hashcode) to make sure it is different from get(key) in the case of a collision
		Assert.assertEquals(5, hashTable.get("foobar"));
		Assert.assertEquals(11, hashTable.get("foobar", 5));

		// Test update
		Assert.assertEquals(2, hashTable.get("c"));
		hashTable.update("c", 4);
		Assert.assertEquals(4, hashTable.get("c"));

		// Test update when item does not exist
		hashTable.update("d", 40);
		Assert.assertEquals("[d, 40] [foobar, 11] [and, 10] [foobar, 5] [foobar, 11] [foo, 3] [a, 0] [a, 6] " +
				"[a, 7] [a, 8] [b, 1] [b, 9] [c, 4] [bar, 4]", hashTable.toString());
	}

	/**
	 * This method tests the HashTable class with constructors with parameters of 1, 2, 5, and 1000
	 */
	@Test
	public void testConstructorWithParameter()
	{
		HashTable[] hashTables = new HashTable[] {
				new HashTable(1), new HashTable(2), new HashTable(5), new HashTable(1000)
		};

		int count = 0;

		for (String str : testStrings)
		{
			for (HashTable h : hashTables)
				h.put(str, count);
			count++;
		}

		// Test hash table with capacity 1
		Assert.assertEquals("[a, 0] [b, 1] [c, 2] [foo, 3] [bar, 4] [foobar, 5] [a, 6] [a, 7] [a, 8] [b, 9] " +
				"[and, 10] [foobar, 11]", hashTables[0].toString());
		// Test hash table with capacity 2
		Assert.assertEquals("[b, 1] [foo, 3] [b, 9] [a, 0] [c, 2] [bar, 4] [foobar, 5] [a, 6] [a, 7] [a, 8] " +
				"[and, 10] [foobar, 11]", hashTables[1].toString());
		// Test hash table with capacity 5
		Assert.assertEquals("[a, 0] [a, 6] [a, 7] [a, 8] [and, 10] [b, 1] [foobar, 5] [b, 9] [foobar, 11] " +
				"[c, 2] [foo, 3] [bar, 4]", hashTables[2].toString());
		// Test hash table with capacity 1000
		Assert.assertEquals("[a, 0] [a, 6] [a, 7] [a, 8] [b, 1] [b, 9] [c, 2] [bar, 4] [foo, 3] [and, 10] " +
				"[foobar, 5] [foobar, 11]", hashTables[3].toString());
	}
}
