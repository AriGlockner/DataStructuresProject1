import org.junit.*;

import java.util.Arrays;

/**
 * This class tests the Sort class
 */
public class TestSort
{
	// index for indexing for insertion into nanoTimes
	private int nIndex = 0;
	// Sorts are: API, insertion, quick, merge
	private final long[][] nanoTimes = new long[9][6];

	/**
	 * Tests the sort class. Calls generateRandomArray method with n = 10, 20, 50, 100, 200, 500, 1000, 2000, and 5000
	 */
	@Test
	public void testSort()
	{
		int[] size = new int[] { 10, 20, 50, 100, 200, 500, 1000, 2000, 5000 };
		long[][][] allNanoTimes = new long[10][9][6];

		for (int i = 0; i < nanoTimes.length; i++)
		{
			// Generate nanoTimes and test
			int index = 0;
			for (int k : size)
				generateRandomArray(k, index++);

			allNanoTimes[i] = nanoTimes.clone();
		}

		for (int j = 0; j < allNanoTimes[0].length; j++)
		{
			for (int k = 0; j < allNanoTimes[0][0].length; k++)
			{
				long avg = 0;

				for (long[][] allNanoTime : allNanoTimes)
				{
					avg += allNanoTime[j][k];
				}

				avg /= allNanoTimes.length;

				nanoTimes[j][k] = avg;
			}
		}


		// Benchmarking
		System.out.println("\nRuntime using Java's System.nanoTime():");
		System.out.println("\t\t\t\tAPI:\t\tInsertion:\tQuick:\t\tMerge:\t\tBucket:\t\tHeap:");

		for (int i = 0; i < nanoTimes.length; i++)
		{
			System.out.print("size = " + size[i] + ":\t");
			// format
			if (size[i] < 1000)
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
	private void generateRandomArray(int n, int i)
	{
		// Generate random arrays
		int[] unsortedArray = Sort.randomArray(n, 0, n * 2);
		//int[] sortedArray = unsortedArray.clone();

		// Use api sort as a default sort to compare sorts to
		int[] sortedArray = apiSort(unsortedArray.clone());

		// call methods that test sorts
		testInsertionSort(unsortedArray.clone(), sortedArray);
		testQuickSort(unsortedArray.clone(), sortedArray);
		testMergeSort(unsortedArray.clone(), sortedArray);
		testBucketSort(unsortedArray.clone(), sortedArray);
		testHeapSort(unsortedArray.clone(), sortedArray);

		// update index in array
		nIndex++;
	}

	private int[] apiSort(int[] array)
	{
		long lastTime = System.nanoTime();
		Arrays.sort(array);
		long deltaTime = System.nanoTime() - lastTime;
		nanoTimes[nIndex][0] = deltaTime;
		return array;
	}

	/**
	 * Tests insertion sort algorithm. Compares already sorted array to unsorted array after calling sort
	 *
	 * @param unsorted array to sort
	 * @param sorted   already sorted array
	 */
	private void testInsertionSort(int[] unsorted, int[] sorted)
	{
		long lastTime = System.nanoTime();
		Sort.insertionSort(unsorted);
		long deltaTime = System.nanoTime() - lastTime;
		nanoTimes[nIndex][1] = deltaTime;
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
		long lastTime = System.nanoTime();
		Sort.quickSort(unsorted);
		long deltaTime = System.nanoTime() - lastTime;
		nanoTimes[nIndex][2] = deltaTime;
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
		long lastTime = System.nanoTime();
		Sort.mergeSort(unsorted);
		long deltaTime = System.nanoTime() - lastTime;
		nanoTimes[nIndex][3] = deltaTime;
		Assert.assertArrayEquals(unsorted, sorted);
	}

	/**
	 * Tests merge sort algorithm. Compares already sorted array to unsorted array after calling sort
	 *
	 * @param unsorted array to sort
	 * @param sorted   already sorted array
	 */
	private void testBucketSort(int[] unsorted, int[] sorted)
	{
		long lastTime = System.nanoTime();
		Sort.bucketSort(unsorted);
		long deltaTime = System.nanoTime() - lastTime;
		nanoTimes[nIndex][4] = deltaTime;
		Assert.assertArrayEquals(unsorted, sorted);
	}

	/**
	 * Tests heap sort algorithm. Compares already sorted array to unsorted array after calling sort
	 *
	 * @param unsorted array to sort
	 * @param sorted   already sorted array
	 */
	private void testHeapSort(int[] unsorted, int[] sorted)
	{
		long lastTime = System.nanoTime();
		Sort.heapSort(unsorted);
		long deltaTime = System.nanoTime() - lastTime;
		nanoTimes[nIndex][5] = deltaTime;
		Assert.assertArrayEquals(unsorted, sorted);
	}
}
