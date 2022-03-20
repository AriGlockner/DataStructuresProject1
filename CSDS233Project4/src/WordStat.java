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
		HashEntry ptr = table.getHashEntry(word);
		int count = 0;
		while (ptr != null)
		{
			ptr = ptr.getNext();
			count++;
		}

		return count;
	}

	/**
	 * @param w1 word 1
	 * @param w2 word 2
	 * @return the sum of the return of wordCount for w1 and w2
	 */
	public int wordPairCount(String w1, String w2)
	{
		return wordCount(w1) + wordCount(w2);
	}

	/**
	 * @param word the word to search for
	 * @return the rank of word, where rank 1 is the most common word
	 */
	public int wordRank(String word)
	{
		return 1;
	}

	/**
	 * @param w1 word 1
	 * @param w2 word 2
	 * @return the rank of the word pair w1 w2. Return 0 if the word pair is not in the table
	 */
	public int wordPairRank(String w1, String w2)
	{
		return 1;
	}

	/**
	 * @param k number of most common words
	 * @return a String array of the k most common words in decreasing order of their count
	 */
	public String[] mostCommonWords(int k)
	{
		return null;
	}

	/**
	 * @param k number of the least common words
	 * @return a String array of the k the least common words in increasing order of their count
	 */
	public String[] leastCommonWords(int k)
	{
		return null;
	}

	/**
	 * @param k number of words to return
	 * @return the k most common word pair in a String array, where each element is in the form of "word1 word2"
	 * separated by a single space
	 */
	public String[] mostCommonWordPairs(int k)
	{
		return null;
	}

	/**
	 *
	 * @param k number of words to return
	 * @param baseWord word to check for
	 * @param i relative position to baseWord
	 * @return the k most common words at a given relative position i to baseWord. These are called "collocations." The
	 * relative position can be either +1 or -1 to indicate words following or preceding the base word. For example,
	 * mostCommonCollocs(10, "crash", -1) would return the 10 most common words that precede "crash"
	 */
	public String[] mostCommonCollocs(int k, String baseWord, int i)
	{
		return null;
	}

}
