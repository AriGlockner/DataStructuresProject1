import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class sorting
{
	public static int[] getRandomIntegers(int amount, int maxBound)
	{
		int[] numbers = new int[amount];
		Random random = new Random();

		while (amount-- > 0)
			numbers[amount] = random.nextInt(maxBound);

		return numbers;
	}

	public static void printArray(int[] array, String type)
	{
		System.out.println(type + ":\n" + Arrays.toString(array));
	}

	private static void swap(int[] array, int a, int b)
	{
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	public static void selectionSort(int[] array)
	{

	}

	public static void bubbleSort(int[] array)
	{

	}

	public static void insertionSort(int[] array)
	{

	}

	public static void shellSort(int[] array)
	{

	}

	public static void quickSort(int[] array)
	{

	}

	public static void mergeSort(int[] array)
	{

	}

	public static void heapSort(int[] array)
	{

	}

	public static void bucketSort(int[] array)
	{

	}

	public static void main(String[] args)
	{
		int[] unsorted = getRandomIntegers(10, 100);
		int[][] sorted = new int[8][];

		for (int i = 0; i < sorted.length; i++)
			sorted[i] = unsorted.clone();

		printArray(unsorted, "Unsorted");
	}
}
