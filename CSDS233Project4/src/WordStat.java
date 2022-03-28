import java.util.*;

public class WordStat
{
	private final HashTable table;
	private HashEntry[] mostCommonEntries;
	private String[] mostCommonWords;
	//private String[] mostCommonWordPairs;
	private String[] wordPairStrings;

	/**
	 * @param file to compute statistics from
	 */
	public WordStat(String file)
	{
		Tokenizer t = new Tokenizer(file);
		table = new HashTable(t.getSize());
		initializeHashTable(t);
	}

	/**
	 * @param words list of Strings to compute from
	 */
	public WordStat(String[] words)
	{
		Tokenizer t = new Tokenizer(words);
		table = new HashTable(words.length);
		initializeHashTable(t);
	}

	/**
	 * Helper method for both constructors to initialize the HashTable
	 *
	 * @param t tokenizer
	 */
	private void initializeHashTable(Tokenizer t)
	{
		// Add every unique word from the Tokenizer to the HashTable with a value of the number of instances in the list
		ArrayList<String> words = t.wordList();
		for (String word : words)
			table.update(word, wordCount(word) + 1);

		setMostCommonWords();
	}

	/**
	 * Helper method for initializeHashTable that sets the value associated with each word to the number of instances
	 * of the word and sets the most common words as an array
	 */
	private void setMostCommonWords()
	{
		// Create a heap to generate the most common words
		Heap<HashEntry> heap = new Heap<>();

		// Add items to the heap
		for (HashEntry hashEntry : table.getTable())
			if (hashEntry != null)
				heap.insert(hashEntry);

		// Remove all items from the heap and add them to the mostCommonEntries
		mostCommonEntries = new HashEntry[heap.size()];
		for (int i = 0; i < mostCommonEntries.length; i++)
			mostCommonEntries[i] = heap.delete();

		mostCommonWords = new String[mostCommonEntries.length];
		for (int i = 0; i < mostCommonWords.length; i++)
			mostCommonWords[i] = mostCommonEntries[i].getKey();

		setMostCommonWordPairs();
	}

	//TODO: Fix Method
	private void setMostCommonWordPairs()
	{
		// Create max heap for most common word pairs
		Heap<HashEntry> heap = new Heap<>();

		// Add values to heap
		for (int i = 0; i < mostCommonEntries.length - 1; i++)
			for (int j = i + 1; j < mostCommonEntries.length; j++)
				heap.insert(new HashEntry(mostCommonEntries[i].getKey() + " " + mostCommonEntries[j].getKey(),
						mostCommonEntries[i].getValue() + mostCommonEntries[j].getValue()));

		// Remove HashEntries from the heap and add it to wordPairStrings
		wordPairStrings = new String[heap.size()];
		for (int i = 0; i < heap.size(); i++)
			wordPairStrings[i] = heap.delete().getKey();

		/*
		// Calculate size of word pairs size
		int len = 0;
		for (int i = mostCommonWords.length - 1; i > 0; i--)
			len += i;
		HashEntry[] wordPairs = new HashEntry[len];

		// Add every 2 word combination from mostCommonWordPair to the wordPairs
		int index = 0;
		for (int i = 0; i < mostCommonWords.length - 1; i++)
			for (int j = i + 1; j < mostCommonWords.length; j++)
				wordPairs[index++] = new HashEntry(mostCommonWords[i] + " " + mostCommonWords[j],
						wordPairCount(mostCommonWords[i], mostCommonWords[j]));

		// Sorts the most common word pairs
		Arrays.sort(wordPairs, Collections.reverseOrder());

		// Create an array of strings from most common word pair
		wordPairStrings = new String[len];
		index = 0;
		for (HashEntry hashEntry : wordPairs)
			wordPairStrings[index++] = hashEntry.getKey();
		 */
	}

	/**
	 * @param word to search for
	 * @return the count of the word argument. Return 0 if the word is not in the table
	 */
	public int wordCount(String word)
	{
		// get number of instances in table
		int count = table.get(word);
		// return 0 if not in table
		if (count == -1)
			return 0;
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
		word = normalize(word);
		int numInstances = table.get(word);

		int rank = 1;

		for (String str : mostCommonWords)
		{
			if (str.equals(word))
				return rank;
			else if (table.get(str) > numInstances)
				rank++;
		}
		return 0;
	}

	/**
	 * @param w1 word 1
	 * @param w2 word 2
	 * @return the rank of the word pair w1 w2. Return 0 if the word pair is not in the table
	 */
	public int wordPairRank(String w1, String w2)
	{
		/*
		String key = w1 + " " + w2;
		int index = 1;
		for (String wordPairs : wordPairStrings)
			if (wordPairs.equals(key))
				return index;
			else
				index++;

		return 0;
		 */
		//return wordRank(w1) + wordRank(w2);
		return wordRank(normalize(w1 + w2));
	}

	/**
	 * @param k number of most common words
	 * @return a String array of the k most common words in decreasing order of their count
	 */
	public String[] mostCommonWords(int k)
	{
		return Arrays.copyOf(mostCommonWords, Math.min(Math.abs(k), mostCommonWords.length));
	}

	/**
	 * @param k number of the least common words
	 * @return a String array of the k the least common words in increasing order of their count
	 */
	public String[] leastCommonWords(int k)
	{
		k = Math.min(Math.abs(k), mostCommonWords.length);
		return Arrays.copyOfRange(mostCommonWords, mostCommonWords.length - k, mostCommonWords.length);
	}

	/**
	 * @param k number of words to return
	 * @return the k most common word pair in a String array, where each element is in the form of "word1 word2"
	 * separated by a single space
	 */
	//TODO: Might Need to Fix Method depending on what fixing setMostCommonWordPairs fix looks like
	public String[] mostCommonWordPairs(int k)
	{
		return Arrays.copyOf(wordPairStrings, k);
	}

	/**
	 * @param k        number of words to return
	 * @param baseWord word to check for
	 * @param i        relative position to baseWord
	 * @return the k most common words at a given relative position i to baseWord. These are called "collocations." The
	 * relative position can be either +1 or -1 to indicate words following or preceding the base word. For example,
	 * mostCommonCollocs(10, "crash", -1) would return the 10 most common words that precede "crash"
	 */
	public String[] mostCommonCollocs(int k, String baseWord, int i)
	{
		baseWord = normalize(baseWord);

		if (Math.abs(i) != 1)
			return null;

		String[] words = new String[k];

		int wordPosition = -1;

		for (int index = 0; index < mostCommonWords.length; index++)
			if (mostCommonWords[index].equals(baseWord))
			{
				wordPosition = index;
				break;
			}

		if (wordPosition == -1)
			return null;


		for (int index = 0; k > 0; k--, wordPosition += i, index++)
		{
			if (wordPosition < 0 || wordPosition > mostCommonWords.length)
				return words;
			words[index] = mostCommonWords[wordPosition];
		}

		return words;
	}

	/**
	 * This method has the same functionality as mostCommonCollocs except that it excludes from consideration any words
	 * in the String array exclusions. This provides a means to obtain collocations that exclude common word pairs such
	 * as "of the" or "in a"
	 *
	 * @param k          number of words to return
	 * @param baseWord   word to check for
	 * @param i          relative position to baseWord
	 * @param exclusions list of words to exclude
	 * @return the k most common words excluding words in the parameter exclusions at a given relative position i to
	 * baseWord. These are called "collocations." The relative position can be either +1 or -1 to indicate words
	 * following or preceding the base word. For example, mostCommonCollocs(10, "crash", -1) would return the 10 most
	 * common words that precede "crash"
	 */
	public String[] mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions)
	{
		baseWord = normalize(baseWord);

		if (Math.abs(i) != 1)
			return null;

		String[] possibleWords = mostCommonCollocs(k + exclusions.length, baseWord, i);
		String[] words = new String[k];

		int position = 0;
		for (int index = 0; index < possibleWords.length; index++)
		{
			// temp must be used. It is not allowed to just put index inside the lambda shortcut because it is not final
			int temp = index;
			if (Arrays.stream(exclusions).noneMatch(foo -> foo.equals(possibleWords[temp])))
			{
				words[position++] = possibleWords[index];
				if (position == k)
					return words;
			}
		}

		return words;
	}

	/**
	 * @param k         number of words to return
	 * @param startWord first word to return
	 * @return a string composed of k words from startWord w2 w3 ... wk. The string is generated by finding w2, the
	 * most common word following the startWord, then w3, the most common word following w2, and so on. Each word
	 * should be separated by a single space
	 */
	public String generateWordString(int k, String startWord)
	{
		startWord = normalize(startWord);

		if (k < 1)
			return "";

		StringBuilder sb = new StringBuilder();
		boolean generateString = false;

		for (String str : mostCommonWords)
			if (generateString || str.equals(startWord))
			{
				generateString = true;
				sb.append(str).append(" ");
				if (--k == 0)
					return sb.substring(0, sb.length() - 1);
			}
		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * @return the HashTable.toString()
	 */
	@Override
	public String toString()
	{
		return table.toString();
	}

	/**
	 * Helper method for both methods to normalize parameters to:
	 * Convert words to lower case
	 * Removes punctuation and spaces
	 */
	private static String normalize(String str)
	{
		// Make String LowerCase
		str = str.toLowerCase();
		// Remove all Punctuation
		str = str.replaceAll("\\p{Punct}", "");
		// Remove Leading/Trailing Spaces
		//str = str.trim();
		// Add every word to the ArrayList
		return str.replaceAll(" ", "");
	}

	/**
	 * Demo
	 *
	 * @param args arguments
	 */
	public static void main(String[] args)
	{
		/*
		 Methods to test:
		 both constructors
		 wordRank -> done
		 wordPairRank
		 mostCommonWords -> done
		 leastCommonWords -> done
		 mostCommonWordPairs -> done
		 mostCommonCallocsExc -> done
		 generateWordStrings
		 */

		String[] words = new String[] {"The ", "duck ", "does ", "not ", "eat ", "the ", "bear.", " \\. duck ,; ",
				" du/ck", "duck", "bear"};
		WordStat wordStat1 = new WordStat(words);

		// Word Rank
		System.out.println("Word Rank:");
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordRank("the"));
		System.out.println("Expected:\n1\nActual:\n" + wordStat1.wordRank("duck"));
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordRank("bear"));
		System.out.println("Expected:\n4\nActual:\n" + wordStat1.wordRank("does"));
		System.out.println("Expected:\n0\nActual:\n" + wordStat1.wordRank("Word Not There"));

		// Word Pair Rank
		System.out.println("\nWord Pair Rank:");
		System.out.println("Expected:\n\nActual:\n" + wordStat1.wordPairRank("duck", "duck"));
		System.out.println("Expected:\n\nActual:\n" + wordStat1.wordPairRank("duck", "bear"));

		// Most Common Word
		System.out.println("\nMost Common Word:");
		System.out.println("Expected:\n[duck, the, bear, does, not, eat]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonWords(Integer.MAX_VALUE)));
		System.out.println("Expected:\n[duck, the]\nActual:\n" + Arrays.toString(wordStat1.mostCommonWords(2)));

		// Least Common Word
		System.out.println("\nLeast Common Word:");
		System.out.println("Expected:\n0\nActual:\n" +
				Arrays.compare(wordStat1.mostCommonWords(Integer.MAX_VALUE),
						wordStat1.leastCommonWords(Integer.MAX_VALUE), Collections.reverseOrder()));

		// Most Common Word Pairs:
		System.out.println("\nMost Common Word Pairs:");
		System.out.println("Expected:\n[duck the, duck bear, duck does, duck not, duck eat]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonWordPairs(5)));
		System.out.println("Expected:\n[duck the, duck bear, duck does, duck not, duck eat, the bear, the does, the " +
				"not, the eat, bear does, bear not, bear eat, does not, does eat, not eat]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonWordPairs(15)));
		System.out.println("Expected:\n[duck the]\nActual:\n" + Arrays.toString(wordStat1.mostCommonWordPairs(1)));
		System.out.println("Expected:\n[]\nActual:\n" + Arrays.toString(wordStat1.mostCommonWordPairs(0)));

		// Most Common Collocs Exclusions
		System.out.println("\nMost Common Collocs");
		System.out.println("Expected:\n[duck, bear]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonCollocsExc(2, "duck", 1, new String[] {"the"})));
		System.out.println("Expected:\n[duck, null]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonCollocsExc(2, "duck", -1, new String[] {"the"})));

		System.out.println("Expected:\n[duck, null]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonCollocsExc(2, "duck", -1, new String[] {})));
		System.out.println("Expected:\n[duck, the, bear, does, not]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonCollocsExc(5, "duck", 1, new String[] {})));

		// Generate Word String
		System.out.println("\nGenerate Word String:");
		System.out.println("Expected:\n\nActual:\n" + wordStat1.generateWordString(0, "duck"));
		System.out.println("Expected:\nduck\nActual:\n" + wordStat1.generateWordString(1, "duck"));
		System.out.println("Expected:\nduck the bear\nActual:\n" + wordStat1.generateWordString(3, "duck"));
		System.out.println("Expected:\nduck the bear does not eat\nActual:\n"
				+ wordStat1.generateWordString(10, "duck"));

		// Constructor 2
		System.out.println("\nTest Constructor from file:");
		WordStat wordStat2 = new WordStat("foobar.txt");
		System.out.println(Arrays.toString(wordStat2.mostCommonWords(50)));
	}
}