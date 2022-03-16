import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * This class provides the functionality to read lines of a text file, extract and normalize the words, and store them
 * using Java's ArrayList class composed of String elements
 */
public class Tokenizer
{
	private final ArrayList<String> wordList;

	/**
	 * @param file specifies the file from which to obtain the words.
	 */
	public Tokenizer(String file) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new FileInputStream(file));
		ArrayList<String> words = new ArrayList<>();

		while (scanner.hasNextLine())
			words.add(normalize(scanner.nextLine()));

		wordList = words;
	}

	/**
	 * @param words obtains the words directly from the String array. No file is read.
	 */
	public Tokenizer(String[] words)
	{
		ArrayList<String> wordList = new ArrayList<>();

		for (String str : words)
			wordList.add(normalize(str));

		this.wordList = wordList;
	}

	/**
	 * Helper method for both constructors to:
	 * Convert words to lower case
	 * Removes leading and trailing whitespace
	 * Removes punctuation
	 */
	private static String normalize(String str)
	{
		// Make String LowerCase
		str = str.toLowerCase();
		// Remove all Punctuation
		str = str.replaceAll("\\p{Punct}","");
		// Remove Leading/Trailing Spaces
		return str.trim();
	}

	/**
	 * @return the word list created from the constructors
	 */
	public ArrayList<String> wordList()
	{
		return wordList;
	}

	@Override
	public String toString()
	{
		if (wordList == null)
			return "";

		StringBuilder stringBuilder = new StringBuilder();

		for (String str : wordList)
			stringBuilder.append(" ").append(str);

		return stringBuilder.substring(1);
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		Tokenizer t = new Tokenizer("foobar.txt");
		System.out.println(t);
	}
}
