import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordStat
{
	HashTable table = new HashTable();

	/**
	 * @param file to compute statistics from
	 * @throws FileNotFoundException if unable to find the appropriate path
	 */
	public WordStat(String file) throws FileNotFoundException
	{
		initializeHashTable(new Tokenizer(file));
	}

	/**
	 *
	 * @param words list of Strings to compute from
	 */
	public WordStat(String[] words) throws FileNotFoundException
	{
		initializeHashTable(new Tokenizer(words));
	}

	/**
	 * Helper method for both constructors to initialize the HashTable
	 *
	 * @param t tokenizer
	 */
	private void initializeHashTable(Tokenizer t)
	{
		ArrayList<String> words = t.wordList();
		int index = 0;

		for (String word : words)
			table.put(word, index++);
	}

	/**
	 * @param word to search for
	 * @return the count of the word argument. Return 0 if the word is not in the table
	 */
	public int wordCount(String word)
	{
		return 1;
	}

	public int wordPairCount(String w1, String w2)
	{
		return 1;
	}

	public int wordRank(String word)
	{
		return 1;
	}

	public int wordPairRank(String w1, String w2)
	{
		return 1;
	}

	public String[] mostCommonWords(int k)
	{
		return null;
	}

	public String[] leastCommonWords(int k)
	{
		return null;
	}

	public String[] mostCommonWordPairs(int k)
	{
		return null;
	}

	public String[] mostCommonCollocs(int k, String baseWord, int i)
	{
		return null;
	}

}
