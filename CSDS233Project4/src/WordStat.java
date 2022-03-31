import java.util.*;

public class WordStat
{
	/* Words */
	private HashTable wordsByNumInstances;
	private HashTable wordRanks;
	private String[] mostCommonWords;
	private String[] leastCommonWords;

	/* Word Pairs */
	private HashTable wordPairsByNumInstances;
	private HashTable wordPairRanks;
	private String[] mostCommonWordPairs;

	//
	private String[] words;

	/**
	 * @param file to compute statistics from
	 */
	public WordStat(String file)
	{
		Tokenizer t = new Tokenizer(file);
		initializeAndAssignFields(t);
	}

	/**
	 * @param words list of Strings to compute from
	 */
	public WordStat(String[] words)
	{
		Tokenizer t = new Tokenizer(words);
		initializeAndAssignFields(t);
	}

	/**
	 * Helper method for both constructors to initialize the HashTable
	 *
	 * @param t tokenizer
	 */
	private void initializeAndAssignFields(Tokenizer t)
	{
		// List of words in order of how they appear
		ArrayList<String> words = t.wordList();
		this.words = words.toArray(new String[0]);
		int len = words.size();

		/* Single Words */
		// Initialize fields
		wordsByNumInstances = new HashTable(len);
		wordRanks = new HashTable(len);
		mostCommonWords = new String[len];

		// Add items into wordsByNumInstances
		for (String word : words)
			wordsByNumInstances.update(word, wordCount(word) + 1);

		// Add items into a heap
		Heap heap = new Heap();
		for (HashEntry hashEntry : wordsByNumInstances.getTable())
			if (hashEntry != null)
				heap.update(hashEntry);


		// Remove items from the heap and add items into wordRanks and mostCommonWords
		int index = 0;
		HashEntry lastEntry = null;
		int lastRank = -1;
		while (!heap.isEmpty())
		{
			HashEntry foobar = heap.delete();
			if (lastEntry == null || lastEntry.getValue() != foobar.getValue())
				wordRanks.put(foobar.getKey(), lastRank = index + 1);
			else
				wordRanks.put(foobar.getKey(), lastRank);

			mostCommonWords[index++] = foobar.getKey();
			lastEntry = foobar;
		}
		// Remove null Strings from mostCommonWords
		mostCommonWords = Arrays.stream(mostCommonWords).filter(Objects::nonNull).toArray(String[]::new);

		leastCommonWords = mostCommonWords.clone();
		Collections.reverse(Arrays.asList(leastCommonWords));




		/* Word Pairs */
		// Initialize fields
		wordPairsByNumInstances = new HashTable(len);
		wordPairRanks = new HashTable(len);
		mostCommonWordPairs = new String[len];

		// Add items into wordPairsByNumInstances
		for (int i = 1; i < words.size(); i++)
		{
			int value = 1 + wordPairsByNumInstances.get(words.get(i - 1) + " " + words.get(i));
			wordPairsByNumInstances.update(words.get(i - 1) + " " + words.get(i), Math.max(value, 1));
		}

		// Add items into a heap
		// Clear All shouldn't be necessary. But can't hurt
		//TODO: Remove clearAll
		heap.clearAll();
		for (HashEntry hashEntry : wordPairsByNumInstances.getTable())
			if (hashEntry != null)
			{
				HashEntry ptr = hashEntry;
				while (ptr != null)
				{
					heap.update(ptr);
					ptr = ptr.getNext();
				}
			}

		// Reset the variables used for insertion into the HashTable
		index = 1;
		lastEntry = null;
		lastRank = -1;
		// Add every HashTable from the heap into wordPairRanks and mostCommonWordPairs
		while (!heap.isEmpty())
		{
			// Loop is used due to handling collisions in the HashTable
			HashEntry ptr = heap.delete();
			while (ptr != null)
			{
				// Add to wordPairRanks HashTable. Store the item's rank as its value
				if (lastEntry == null || lastEntry.getValue() != ptr.getValue())
				{
					wordPairRanks.put(ptr.getKey(), index);
					lastRank = index;
				}
				// If the value is the same as the last value, it should store the same value as the one prior
				else
					wordPairRanks.put(ptr.getKey(), lastRank);
				// Add String from heap deletion to mostCommonWordPairs array
				mostCommonWordPairs[index++ - 1] = ptr.getKey();
				// Go to the next HashEntry in the Linked List in case of collision
				lastEntry = ptr;
				ptr = ptr.getNext();
			}
		}

		// Remove null Strings from mostCommonWordPairs
		mostCommonWordPairs = Arrays.stream(mostCommonWordPairs).filter(Objects::nonNull).toArray(String[]::new);
	}

	/**
	 * Helper method for initializeHashTable that sets the value associated with each word to the number of instances
	 * of the word and sets the most common words as an array
	 * <p>
	 * private void setMostCommonWords()
	 * {
	 * // Create a heap to generate the most common words
	 * Heap<HashEntry> heap = new Heap<>();
	 * <p>
	 * // Add items to the heap
	 * for (HashEntry hashEntry : words)
	 * if (hashEntry != null)
	 * heap.insert(hashEntry);
	 * <p>
	 * // Remove all items from the heap and add them to the mostCommonEntries
	 * HashEntry[] mostCommonEntries = new HashEntry[heap.size()];
	 * for (int i = 0; i < mostCommonEntries.length; i++)
	 * mostCommonEntries[i] = heap.delete();
	 * <p>
	 * mostCommonWords = new String[mostCommonEntries.length];
	 * for (int i = 0; i < mostCommonWords.length; i++)
	 * mostCommonWords[i] = mostCommonEntries[i].getKey();
	 * <p>
	 * setMostCommonWordPairs(mostCommonEntries);
	 * }
	 * <p>
	 * // TODO: Do most common word pairs for word pairs, not all possible combinations
	 * private void setMostCommonWordPairs(HashEntry[] mostCommonEntries)
	 * {
	 * // Create max heap for most common word pairs
	 * Heap<HashEntry> heap = new Heap<>();
	 * <p>
	 * // Add values to heap
	 * for (int i = 0; i < mostCommonEntries.length; i++)
	 * for (int j = i + 1; j < mostCommonEntries.length; j++)
	 * heap.insert(new HashEntry(mostCommonEntries[i].getKey() + " " + mostCommonEntries[j].getKey(),
	 * mostCommonEntries[i].getValue() + mostCommonEntries[j].getValue()));
	 * <p>
	 * // Remove HashEntries from the heap and add it to mostCommonWordPairs
	 * mostCommonWordPairs = new String[heap.size()];
	 * for (int i = 0; i < mostCommonWordPairs.length; i++)
	 * mostCommonWordPairs[i] = heap.delete().getKey();
	 * }
	 * <p>
	 * /**
	 *
	 * @param word to search for
	 * @return the count of the word argument. Return 0 if the word is not in the table
	 */
	public int wordCount(String word)
	{
		return Math.max(0, wordsByNumInstances.get(word));
	}

	/**
	 * @param w1 word 1
	 * @param w2 word 2
	 * @return the sum of the return of wordCount for w1 and w2
	 */
	public int wordPairCount(String w1, String w2)
	{
		// return count if in table, otherwise, return 0
		return Math.max(0, wordPairsByNumInstances.get(w1 + " " + w2));
	}

	/**
	 * @param word the word to search for
	 * @return the rank of word, where rank 1 is the most common word
	 */
	public int wordRank(String word)
	{
		// if word is in table return word rank, otherwise, return 0
		return Math.max(0, wordRanks.get(word));
	}

	/**
	 * @param w1 word 1
	 * @param w2 word 2
	 * @return the rank of the word pair w1 w2. Return 0 if the word pair is not in the table
	 */
	public int wordPairRank(String w1, String w2)
	{
		// return count if in table, otherwise, return 0
		//return Math.max(0, 1 + wordPairRanks.get(w1 + " " + w2));
		return Math.max(0, wordPairRanks.get(w1 + " " + w2));
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
		return Arrays.copyOf(leastCommonWords, Math.min(Math.abs(k), leastCommonWords.length));
	}

	/**
	 * @param k number of words to return
	 * @return the k most common word pair in a String array, where each element is in the form of "word1 word2"
	 * separated by a single space
	 */
	// TODO: Do most common word pairs for word pairs, not all possible combinations
	public String[] mostCommonWordPairs(int k)
	{
		return Arrays.copyOf(mostCommonWordPairs, Math.min(Math.abs(k), mostCommonWords.length));
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
		if (k < 1)
			return "";

		StringBuilder sb = new StringBuilder();
		boolean generateString = false;

		// Start at startWord and add the next k - 1 most common words to the string separated by a space
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
		return Arrays.toString(words);
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
		String[] words = new String[] {"The ", "duck ", "does ", "not ", "eat ", "the ", "bear.", " \\. duck ,; ",
				" du/ck", "duck", "bear"};
		WordStat wordStat1 = new WordStat(words);

		// Word Rank
		/*
		System.out.println("Word Rank:");
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordRank("the"));
		System.out.println("Expected:\n1\nActual:\n" + wordStat1.wordRank("duck"));
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordRank("bear"));
		System.out.println("Expected:\n4\nActual:\n" + wordStat1.wordRank("does"));
		System.out.println("Expected:\n0\nActual:\n" + wordStat1.wordRank("Word Not There"));
		*/
		// Word Pair Rank
		//System.out.println("\nWord Pair Rank:");
		//System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordPairRank("duck", "duck"));
		//System.out.println("Expected:\n1\nActual:\n" + wordStat1.wordPairRank("duck", "bear"));

		// Most Common Word
		/*
		System.out.println("\nMost Common Word:");
		System.out.println("Expected:\n[duck, bear, the, does, eat, not]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonWords(Integer.MAX_VALUE)));
		System.out.println("Expected:\n[duck, bear]\nActual:\n" + Arrays.toString(wordStat1.mostCommonWords(2)));

		// Least Common Word
		System.out.println("\nLeast Common Word:");
		System.out.println("Expected:\n0\nActual:\n" +
				Arrays.compare(wordStat1.mostCommonWords(Integer.MAX_VALUE),
						wordStat1.leastCommonWords(Integer.MAX_VALUE), Collections.reverseOrder()));

		// Most Common Word Pairs:
		System.out.println("\nMost Common Word Pairs:");
		System.out.println("Expected:\n[duck bear, duck the, duck does]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonWordPairs(3)));
		System.out.println("Expected:\n[does not, duck bear, duck does, not eat, the bear, the duck, null]\nActual:\n"
				+ Arrays.toString(wordStat1.mostCommonWordPairs(7)));
		System.out.println("Expected:\n[duck bear]\nActual:\n" + Arrays.toString(wordStat1.mostCommonWordPairs(1)));
		System.out.println("Expected:\n[]\nActual:\n" + Arrays.toString(wordStat1.mostCommonWordPairs(0)));

		// Most Common Collocs Exclusions
		System.out.println("\nMost Common Collocs:");
		System.out.println("Expected:\n[duck, bear]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonCollocsExc(2, "duck", 1, new String[] {"the"})));
		System.out.println("Expected:\n[duck, null]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonCollocsExc(2, "duck", -1, new String[] {"the"})));

		System.out.println("Expected:\n[duck, null]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonCollocsExc(2, "duck", -1, new String[] {})));
		System.out.println("Expected:\n[duck, bear, the, does, eat]\nActual:\n" +
				Arrays.toString(wordStat1.mostCommonCollocsExc(5, "duck", 1, new String[] {})));

		// Generate Word String
		System.out.println("\nGenerate Word String:");
		System.out.println("Expected:\n\nActual:\n" + wordStat1.generateWordString(0, "duck"));
		System.out.println("Expected:\nduck\nActual:\n" + wordStat1.generateWordString(1, "duck"));
		System.out.println("Expected:\nduck bear the\nActual:\n" + wordStat1.generateWordString(3, "duck"));
		System.out.println("Expected:\nduck bear the does eat not\nActual:\n"
				+ wordStat1.generateWordString(10, "duck"));
		*/
		// Constructor 2
		/*
		System.out.println("\nTest Constructor from file:");
		WordStat wordStat2 = new WordStat("foobar.txt");
		System.out.println("Expected:\n[foobar, bar, foo]\nActual:\n" + Arrays.toString(wordStat2.mostCommonWords(50)));
		*/

		System.out.println("\n");
		System.out.println(Arrays.toString(wordStat1.mostCommonWordPairs));
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordPairRank("the", "duck") + "\n"); //
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordPairRank("duck", "does") + "\n");
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordPairRank("does", "not") + "\n");
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordPairRank("not", "eat") + "\n");
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordPairRank("eat", "the") + "\n");
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordPairRank("the", "bear") + "\n"); //
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordPairRank("bear", "duck") + "\n"); //
		System.out.println("Expected:\n1\nActual:\n" + wordStat1.wordPairRank("duck", "duck") + "\n"); //
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordPairRank("duck", "bear") + "\n");
		//System.out.println("Expected:\n0\nActual:\n" + wordStat1.wordPairRank("not", "here") + "\n");
		/*
		//System.out.println(wordStat1.wordPairsByNumInstances);
		System.out.println(wordStat1);
		System.out.println(Arrays.toString(wordStat1.wordPairRanks.getTable()));
		*/
		/*
		System.out.println("Expected:\n1\nActual:\n" + wordStat1.wordPairCount("the", "duck") + "\n"); //
		System.out.println("Expected:\n1\nActual:\n" + wordStat1.wordPairCount("duck", "does") + "\n");
		System.out.println("Expected:\n1\nActual:\n" + wordStat1.wordPairCount("does", "not") + "\n");
		System.out.println("Expected:\n1\nActual:\n" + wordStat1.wordPairCount("not", "eat") + "\n");
		System.out.println("Expected:\n1\nActual:\n" + wordStat1.wordPairCount("eat", "the") + "\n");
		System.out.println("Expected:\n1\nActual:\n" + wordStat1.wordPairCount("the", "bear") + "\n"); //
		System.out.println("Expected:\n1\nActual:\n" + wordStat1.wordPairCount("bear", "duck") + "\n"); //
		System.out.println("Expected:\n2\nActual:\n" + wordStat1.wordPairCount("duck", "duck") + "\n"); //
		System.out.println("Expected:\n1\nActual:\n" + wordStat1.wordPairCount("duck", "bear") + "\n");
		*/
	}
}