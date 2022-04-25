import java.util.LinkedList;

/**
 * Hash Table that uses chaining to handle collisions. Uses integers keys and strings for the values
 */
public class HashTableWithChaining
{
	static class SeparateChainingNode
	{
		private int key;
		private String value;

		private LinkedList<SeparateChainingNode> collisionNodes;

		public SeparateChainingNode(int key, String value)
		{
			this.key = key;
			this.value = value;
			collisionNodes = new LinkedList<>();
		}

		public void addCollision(SeparateChainingNode node)
		{
			if (!collisionNodes.contains(node))
				collisionNodes.addLast(node);
		}

		public String get(int key)
		{
			if (this.key == key)
				return value;

			for (SeparateChainingNode node : collisionNodes)
				if (key == node.key)
					return node.value;
			return "";
		}

		@Override
		public boolean equals(Object o)
		{
			if (o instanceof SeparateChainingNode)
				return ((SeparateChainingNode) o).key == key;
			return false;
		}

		@Override
		public String toString()
		{
			if (collisionNodes.size() == 0)
				return value;

			StringBuilder sb = new StringBuilder();
			sb.append(value).append(", ");

			for (SeparateChainingNode node : collisionNodes)
				sb.append(node.value).append(", ");

			return sb.substring(0, sb.length() - 2);
		}
	}

	private SeparateChainingNode[] table;

	public HashTableWithChaining()
	{
		table = new SeparateChainingNode[8];
	}

	public HashTableWithChaining(int size)
	{
		table = new SeparateChainingNode[size];
	}

	public void put(int key, String value)
	{
		SeparateChainingNode newNode = new SeparateChainingNode(key, value);
		int position = key % table.length;

		if (table[position] == null)
			table[position] = newNode;
		else
			table[position].addCollision(newNode);
	}

	public String get(int key)
	{
		if (table[key % table.length] == null)
			return "";
		return table[key % table.length].get(key);
	}

	public void update(int key, String newValue)
	{
		int position = key % table.length;

		if (table[position] == null)
			put(key, newValue);
		else
			table[key % table.length].value = newValue;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder("[");

		for (SeparateChainingNode n : table)
			if (n != null)
				sb.append(n).append(", ");
		if (sb.toString().equals("["))
			return "[]";
		return sb.substring(0, sb.length() - 2) + "]";
	}

	public static void main(String[] args)
	{
		HashTableWithChaining hashTable = new HashTableWithChaining();

		hashTable.put(4, "Four");
		hashTable.put(3, "Three");
		hashTable.put(12, "Twelve");

		System.out.println(hashTable);

		System.out.println(hashTable.get(20));
		System.out.println(hashTable.get(3));
		System.out.println(hashTable.get(4));
		System.out.println(hashTable.get(12));

		hashTable.update(12, "Not Twelve");
		System.out.println(hashTable);
	}
}
