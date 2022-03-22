import java.io.FileNotFoundException;
import java.util.*;

public class WordStat
{
	private final HashTable table = new HashTable();
	private String[] mostCommonWords;

	//TODO: Use the values of each HashEntry to create a list of most common words at initialization of this class
	//TODO: Use the TODO on line 10 to write the methods that use common words

	/*
	How to generate mostCommonWords:
	add every entry to a heap O(N)
	remove items from heap to generate mostCommonWords O(NlogN)
	total time O(NlogN)
	 */

	/**
	 * @param file to compute statistics from
	 */
	public WordStat(String file)
	{
		initializeHashTable(new Tokenizer(file));
	}

	/**
	 *
	 * @param words list of Strings to compute from
	 */
	public WordStat(String[] words)
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

		for (String word : words)
		{
			table.put(word, 1);
			table.updateEverythingWithKey(word, wordCount(word));
		}

		setMostCommonWords();
	}

	private void setMostCommonWords()
	{
		// Create max heap of HashEntries
		Heap<HashEntry> heap = new Heap<>();

		// Add every unique key from the HashTable to the Heap
		int numElements = 0;

		for (HashEntry hashEntry : table.getTable())
		{
			ArrayList<HashEntry> wordsInList = new ArrayList<>();
			while (hashEntry != null)
			{
				if (!wordsInList.contains(hashEntry))
				{
					HashEntry hashToAdd = hashEntry.get();
					heap.insert(hashToAdd);
					wordsInList.add(hashToAdd);
					numElements++;
				}
				hashEntry = hashEntry.getNext();
			}
		}

		// Remove the most common word from the heap and add it to the mostCommonWords list until the heap is empty
		mostCommonWords = new String[numElements];
		for (int i = 0; i < numElements; i++)
			mostCommonWords[i] = heap.delete().getKey();
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
			if (word.equals(ptr.getKey()))
				count++;
			ptr = ptr.getNext();
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
	//TODO: Write Method
	public int wordRank(String word)
	{
		return table.get(word);
	}

	/**
	 * @param w1 word 1
	 * @param w2 word 2
	 * @return the rank of the word pair w1 w2. Return 0 if the word pair is not in the table
	 */
	public int wordPairRank(String w1, String w2)
	{
		return wordRank(w1) + wordRank(w2);
	}

	/**
	 * @param k number of most common words
	 * @return a String array of the k most common words in decreasing order of their count
	 */
	public String[] mostCommonWords(int k)
	{
		if (k > mostCommonWords.length)
			k = mostCommonWords.length;
		k = Math.abs(k);
		return Arrays.copyOf(mostCommonWords, k);
	}

	/**
	 * @param k number of the least common words
	 * @return a String array of the k the least common words in increasing order of their count
	 */
	//TODO: Write Method
	public String[] leastCommonWords(int k)
	{
		return null;
	}

	/**
	 * @param k number of words to return
	 * @return the k most common word pair in a String array, where each element is in the form of "word1 word2"
	 * separated by a single space
	 */
	//TODO: Write Method
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
	//TODO: Write Method
	public String[] mostCommonCollocs(int k, String baseWord, int i)
	{
		return null;
	}

	/**
	 * This method has the same functionality as mostCommonCollocs except that it excludes from consideration any words
	 * in the String array exclusions. This provides a means to obtain collocations that exclude common word pairs such
	 * as "of the" or "in a"
	 * @param k number of words to return
	 * @param baseWord word to check for
	 * @param i relative position to baseWord
	 * @param exclusions list of words to exclude
	 * @return the k most common words excluding words in the parameter exclusions at a given relative position i to
	 * baseWord. These are called "collocations." The relative position can be either +1 or -1 to indicate words
	 * following or preceding the base word. For example, mostCommonCollocs(10, "crash", -1) would return the 10 most
	 * common words that precede "crash"
	 */
	//TODO: Write Method
	public String[] mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions)
	{
		return null;
	}

	/**
	 *
	 * @param k number of words to return
	 * @param startWord first word to return
	 * @return a string composed of k words from startWord w2 w3 ... wk. The string is generated by finding w2, the
	 * most common word following the startWord, then w3, the most common word following w2, and so on. Each word
	 * should be separated by a single space
	 */
	//TODO: Write Method
	public String generateWordString(int k, String startWord)
	{
		if (k < 1)
			return "";

		StringBuilder sb = new StringBuilder();

		return sb.substring(0, sb.length() - 1);
	}

	@Override
	public String toString()
	{
		return table.toString();
	}

	public static void main(String[] args)
	{
		WordStat wordStat = new WordStat("foobar.txt");
		/*
		System.out.println(wordStat.wordCount("foobar"));
		System.out.println(wordStat.wordCount("foo"));
		System.out.println(wordStat.wordCount("bar"));
		System.out.println(wordStat.wordCount("foobar!"));
		System.out.println(wordStat.wordPairCount("foo", "bar"));
		System.out.println(wordStat);
		 */
		//System.out.println(wordStat.table);
		System.out.println(Arrays.toString(wordStat.mostCommonWords));
		System.out.println(Arrays.toString(wordStat.mostCommonWords(2)));
	}
}