import java.io.*;
import java.util.*;

/**
 * This class provides the functionality to read lines of a text file, extract and normalize the words, and store them
 * using Java's ArrayList class composed of String elements
 */
public class Tokenizer
{
	private ArrayList<String> wordList;

	/**
	 * @param file specifies the file from which to obtain the words.
	 */
	public Tokenizer(String file) // throws FileNotFoundException
	{
		try
		{
			// Initialize scanner and wordList
			Scanner scanner = new Scanner(new FileInputStream(file));
			wordList = new ArrayList<>();

			// Read each line in the file, normalize the words and add them to wordList
			while (scanner.hasNextLine())
				normalize(scanner.nextLine());

		} catch (Exception e)
		{
			// Initialize wordList as an empty list
			wordList = new ArrayList<>(0);

			e.printStackTrace();
		}
	}

	/**
	 * @param words obtains the words directly from the String array. No file is read.
	 */
	public Tokenizer(String[] words)
	{
		// Normalize each word in the array and add it to the ArrayList
		wordList = new ArrayList<>(words.length);
		for (String str : words)
			normalize(str);
	}

	/**
	 * Helper method for both constructors to:
	 * Convert words to lower case
	 * Removes leading and trailing whitespace
	 * Removes punctuation
	 */
	private void normalize(String str)
	{
		// Make String LowerCase
		str = str.toLowerCase();
		// Remove all Punctuation
		str = str.replaceAll("\\p{Punct}","");
		// Remove Leading/Trailing Spaces
		str = str.trim();
		// Add every word to the ArrayList
		wordList.addAll(List.of(str.split("\\s+")));
	}

	/**
	 * @return the word list created from the constructors
	 */
	public ArrayList<String> wordList()
	{
		return wordList;
	}

	/**
	 * Helps initialize wordStat class HashTable
	 * @return size of wordList
	 */
	public int getSize()
	{
		return wordList.size();
	}

	@Override
	public String toString()
	{
		return wordList.toString();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		Tokenizer t = new Tokenizer("foobar.txt");
		System.out.println(t);
	}
}
