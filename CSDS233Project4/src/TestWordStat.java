import org.junit.*;
import java.util.Arrays;

/**
 * This class tests the WordStat class
 */
public class TestWordStat
{
	/**
	 * This method tests the WordStat class for everything except for the filepath constructor
	 */
	@Test
	public void testArray()
	{
		/* Initialize WordStat */
		String[] words = new String[] {"         o(*n!^&%e       ", "two", "three", "four", "two", "three", "four",
				"three", "four", "four.", "the", "shovel", "shovel", "shovel", "was", "a a", "ground", "breaking",
				"breaking", " ~* ^   breaking      ", "breaking", "&@#a)&%*", "invention."};
		WordStat wordStat = new WordStat(words);

		/* Start Tests */
		// WordCount
		Assert.assertEquals(0, wordStat.wordCount("zero"));
		Assert.assertEquals(1, wordStat.wordCount("one"));
		Assert.assertEquals(2, wordStat.wordCount("two"));
		Assert.assertEquals(3, wordStat.wordCount("three"));
		Assert.assertEquals(4, wordStat.wordCount("four"));
		Assert.assertEquals(3, wordStat.wordCount("a"));

		// Word Pair Count
		Assert.assertEquals(0, wordStat.wordPairCount("zero", "one"));
		Assert.assertEquals(1, wordStat.wordPairCount("one", "two"));
		Assert.assertEquals(2, wordStat.wordPairCount("two", "three"));
		Assert.assertEquals(3, wordStat.wordPairCount("three", "four"));
		Assert.assertEquals(1, wordStat.wordPairCount("a", "a"));
		Assert.assertEquals(1, wordStat.wordPairCount("a", "ground"));

		// Word Rank
		Assert.assertEquals(0, wordStat.wordRank("zero"));
		Assert.assertEquals(6, wordStat.wordRank("one"));
		Assert.assertEquals(5, wordStat.wordRank("two"));
		Assert.assertEquals(3, wordStat.wordRank("three"));
		Assert.assertEquals(1, wordStat.wordRank("four"));
		Assert.assertEquals(6, wordStat.wordRank("the"));
		Assert.assertEquals(1, wordStat.wordRank("breaking"));

		// Word Pair Rank
		Assert.assertEquals(0, wordStat.wordPairRank("zero", "one"));
		Assert.assertEquals(5, wordStat.wordPairRank("one", "two"));
		Assert.assertEquals(3, wordStat.wordPairRank("two", "three"));
		Assert.assertEquals(1, wordStat.wordPairRank("three", "four"));
		Assert.assertEquals(0, wordStat.wordPairRank("two", "a"));
		Assert.assertEquals(1, wordStat.wordPairRank("breaking", "breaking"));
		Assert.assertEquals(5, wordStat.wordPairRank("a", "a"));

		// Most Common Words
		Assert.assertEquals("[breaking, four, shovel, three, two, invention, one, the, was]",
				Arrays.toString(wordStat.mostCommonWords(Integer.MAX_VALUE)));
		Assert.assertEquals("[]", Arrays.toString(wordStat.mostCommonWords(0)));
		Assert.assertEquals("[breaking]", Arrays.toString(wordStat.mostCommonWords(1)));
		Assert.assertEquals("[breaking, four, shovel, three]", Arrays.toString(wordStat.mostCommonWords(4)));

		// Least Common Words
		Assert.assertEquals("[was, the, one, invention, two, three, shovel, four, breaking]",
				Arrays.toString(wordStat.leastCommonWords(Integer.MAX_VALUE)));
		Assert.assertEquals("[]",
				Arrays.toString(wordStat.leastCommonWords(0)));
		Assert.assertEquals("[was]",
				Arrays.toString(wordStat.leastCommonWords(1)));
		Assert.assertEquals("[was, the, one, invention]",
				Arrays.toString(wordStat.leastCommonWords(4)));

		// Most Common Word Pairs
		Assert.assertEquals("[breaking breaking, three four, shovel shovel, two three, a a, a ground, a " +
				"invention, breaking a, four four]", Arrays.toString(wordStat.mostCommonWordPairs(Integer.MAX_VALUE)));
		Assert.assertEquals("[breaking breaking, three four, shovel shovel, two three]",
				Arrays.toString(wordStat.mostCommonWordPairs(4)));

		// Most Common Collocs
		Assert.assertEquals("[shovel, three, two, invention, one]",
				Arrays.toString(wordStat.mostCommonCollocs(5, "shovel", 1)));
		Assert.assertEquals("[shovel, four, breaking, null]",
				Arrays.toString(wordStat.mostCommonCollocs(4, "shovel", -1)));

		// Most Common Collocs Exclusions
		Assert.assertEquals("[shovel, three, two, one, the]",
				Arrays.toString(wordStat.mostCommonCollocsExc(5, "shovel", 1,
						new String[] {"", "invention"})));
		Assert.assertEquals("[four, breaking]",
				Arrays.toString(wordStat.mostCommonCollocsExc(2, "shovel", -1, new String[] {"shovel"})));

		// Generate Word String
		Assert.assertEquals("breaking four shovel three two", wordStat.generateWordString(5, "breaking"));
		Assert.assertEquals("was", wordStat.generateWordString(1, "was"));
		Assert.assertEquals("breaking four shovel three two invention one the was", wordStat.generateWordString(9, "breaking"));
		Assert.assertEquals("invention one the", wordStat.generateWordString(3, "invention"));
		Assert.assertEquals("one the was", wordStat.generateWordString(3, "one"));
	}

	/**
	 * This method tests the WordStat class constructor with a filepath
	 */
	@Test
	public void testFile()
	{
		/* Initialize WordStat */
		WordStat wordStat = new WordStat("foobar.txt");

		/* Start Tests */
		Assert.assertEquals("[foobar, bar, foo]", Arrays.toString(wordStat.mostCommonWords(3)));
	}
}
