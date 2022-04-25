/**
 * Hash Table that uses linear probing to handle collisions. Uses integer keys and strings values
 */
public class HashTableLinearProbing
{
	static class HashTableNode
	{
		private int key;
		private String value;

		public HashTableNode(int key, String value)
		{
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString()
		{
			return value;
		}
	}


	private HashTableNode[] table;
	private int numElements;

	public HashTableLinearProbing()
	{
		table = new HashTableNode[8];
		numElements = 0;
	}

	public HashTableLinearProbing(int initialSize)
	{
		table = new HashTableNode[initialSize];
		numElements = 0;
	}

	public void put(int key, String value)
	{
		int index = key % table.length;

		if (isFull())
			increaseTableSize();
		numElements++;

		while (table[index % table.length] != null)
			index++;

		table[index % table.length] = new HashTableNode(key, value);
	}

	public void update(int key, String newValue)
	{
		if (table[key % table.length] == null)
		{
			put(key, newValue);
			return;
		}
		if (table[key % table.length].key == key)
		{
			table[key % table.length].value = newValue;
			return;
		}

		for (int position = (key % table.length) + 1; position != key % table.length;
		     position = (position + 1 % table.length))
		{
			if (table[position] == null)
			{
				put(key, newValue);
				return;
			}
			if (table[position].key == key)
			{
				table[position].value = newValue;
				return;
			}
		}
	}

	public String get(int key)
	{
		if (table[key % table.length] == null)
			return "";
		if (table[key % table.length].key == key)
			return table[key % table.length].value;

		for (int position = (key % table.length) + 1; position != key % table.length;
		     position = (position + 1 % table.length))
		{
			if (table[position] == null)
				return "";
			if (table[position].key == key)
				return table[position].value;
		}

		return "";
	}

	private boolean isFull()
	{
		return numElements == table.length;
	}

	private void increaseTableSize()
	{
		HashTableLinearProbing newTable = new HashTableLinearProbing(table.length * 2);
		for (HashTableNode node : table)
			newTable.put(node.key, node.value);
		table = newTable.table;
	}

	@Override
	public String toString()
	{
		if (numElements == 0)
			return "";

		StringBuilder sb = new StringBuilder("[");

		for (HashTableNode n : table)
			if (n != null)
				sb.append(n).append(", ");
		return sb.substring(0, sb.length() - 2) + "]";
	}

	public static void main(String[] args)
	{
		HashTableLinearProbing hashTable = new HashTableLinearProbing(2);

		hashTable.put(1, "one");
		hashTable.put(4, "four");
		System.out.println(hashTable);
		hashTable.put(5, "five");

		System.out.println(hashTable);

		System.out.println(hashTable.get(20));
		System.out.println(hashTable.get(1));
		System.out.println(hashTable.get(4));
		System.out.println(hashTable.get(5));

		hashTable.update(4, "not four");
		System.out.println(hashTable);
	}
}
