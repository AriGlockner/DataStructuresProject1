import org.junit.*;

import java.io.FileNotFoundException;

/**
 * This class tests the Tokenizer class
 */
public class TestTokenizer
{
	/**
	 * Tests the Tokenizer class for the constructor that is a file
	 */
	@Test
	public void testFile()
	{
		try
		{
			Tokenizer tokenizer = new Tokenizer("foobar.txt");
			System.out.println(tokenizer);

		} catch (Exception e)
		{
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * Tests the Tokenizer class for the constructor that is an array of words
	 */
	@Test
	public void testWords()
	{
		Tokenizer tokenizer = new Tokenizer(new String[] {"foobar.1", " \\ foo", "bar", " the duck ", " does", "not",
				"eat", "the", "bear"});

		Assert.assertEquals("[foobar1, foo, bar, the duck, does, not, eat, the, bear]",
				tokenizer.wordList().toString());
	}
}
