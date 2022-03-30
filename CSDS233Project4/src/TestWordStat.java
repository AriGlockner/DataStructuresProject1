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
				"breaking", " ~* ^   breaking      ", "breaking", "&@#)&%*", "invention."};
		WordStat wordStat = new WordStat(words);

		/* Start Tests */
		// WordCount
		Assert.assertEquals(0, wordStat.wordCount("zero"));
		Assert.assertEquals(1, wordStat.wordCount("one"));
		Assert.assertEquals(2, wordStat.wordCount("two"));
		Assert.assertEquals(3, wordStat.wordCount("three"));
		Assert.assertEquals(4, wordStat.wordCount("four"));
		Assert.assertEquals(2, wordStat.wordCount("a"));

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

		// Most Common Words
		//System.out.println(Arrays.toString(wordStat.mostCommonWords(23)));

		// Least Common Words

		// Most Common Collocs

		// Most Common Collocs Exclusions

		// Generate Word String
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
		//Assert.assertEquals(wordStat.);

	}
}
