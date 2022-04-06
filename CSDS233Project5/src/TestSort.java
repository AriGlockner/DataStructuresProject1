import org.junit.*;

import java.util.Arrays;

/**
 * This class tests the Sort class
 */
public class TestSort
{
	// used for determining change in time
	private long lastNanoTime;
	// index for indexing for insertion into nanoTimes
	private int nIndex = 0;
	// Sorts are: API, insertion, quick, merge
	private final long[][] nanoTimes = new long[9][4];

	/**
	 * Tests the sort class. Calls generateRandomArray method with n = 10, 20, 50, 100, 200, 500, 1000, 2000, and 5000
	 */
	@Test
	public void testSort()
	{
		int[] size = new int[] { 10, 20, 50, 100, 200, 500, 1000, 2000, 5000 };
		lastNanoTime = System.nanoTime();

		for (int k : size)
			generateRandomArray(k);

		// Benchmarking
		System.out.println("\t\t\tAPI:\t\tInsertion:\tQuick:\t\tMerge:");

		for (int i = 0; i < nanoTimes.length; i++)
		{
			System.out.print("n = " + size[i] + ":\t");
			// format
			if (size[i] < 100)
				System.out.print("\t");

			for (int j = 0; j < nanoTimes[i].length; j++)
			{
				System.out.print(nanoTimes[i][j] + "\t");
				if (nanoTimes[i][j] < 10000000)
					System.out.print("\t");
			}
			System.out.println();
		}
	}

	/**
	 * Generates an array of random integers of size n between the numbers 0 and n * 2. Calls other methods to test
	 * the sorting algorithms
	 *
	 * @param n random input size
	 */
	private void generateRandomArray(int n)
	{
		// Generate random arrays
		int[] unsortedArray = Sort.randomArray(n, 0, n * 2);
		int[] sortedArray = unsortedArray.clone();
		lastNanoTime = System.nanoTime();

		// Use api sort as a default sort to compare sorts to
		Arrays.sort(sortedArray);
		updateNanoTime(0);

		// call methods that test sorts
		testInsertionSort(unsortedArray.clone(), sortedArray);
		updateNanoTime(1);
		testQuickSort(unsortedArray.clone(), sortedArray);
		updateNanoTime(2);
		testMergeSort(unsortedArray.clone(), sortedArray);
		updateNanoTime(3);

		nIndex++;
	}

	private void updateNanoTime(int i)
	{
		nanoTimes[nIndex][i] = System.nanoTime() - lastNanoTime;
		lastNanoTime = System.nanoTime();
	}


	/**
	 * Tests insertion sort algorithm. Compares already sorted array to unsorted array after calling sort
	 *
	 * @param unsorted array to sort
	 * @param sorted   already sorted array
	 */
	private void testInsertionSort(int[] unsorted, int[] sorted)
	{
		Sort.insertionSort(unsorted);
		Assert.assertArrayEquals(unsorted, sorted);
	}

	/**
	 * Tests quick sort algorithm. Compares already sorted array to unsorted array after calling sort
	 *
	 * @param unsorted array to sort
	 * @param sorted   already sorted array
	 */
	private void testQuickSort(int[] unsorted, int[] sorted)
	{
		Sort.quickSort(unsorted);
		Assert.assertArrayEquals(unsorted, sorted);
	}

	/**
	 * Tests merge sort algorithm. Compares already sorted array to unsorted array after calling sort
	 *
	 * @param unsorted array to sort
	 * @param sorted   already sorted array
	 */
	private void testMergeSort(int[] unsorted, int[] sorted)
	{
		Sort.mergeSort(unsorted);
		Assert.assertArrayEquals(unsorted, sorted);
	}
}
