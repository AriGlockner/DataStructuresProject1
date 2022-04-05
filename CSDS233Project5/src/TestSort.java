import org.junit.*;

import java.util.Arrays;

/**
 * This class tests the Sort class
 */
public class TestSort
{
	/**
	 * Tests the sort class. Calls generateRandomArray method with n = 10, 20, 50, 100, 200, 500, 1000, 2000, and 5000
	 */
	@Test
	public void testSort()
	{
		generateRandomArray(10);
		generateRandomArray(20);
		generateRandomArray(50);
		generateRandomArray(100);
		generateRandomArray(200);
		generateRandomArray(500);
		generateRandomArray(1000);
		generateRandomArray(2000);
		generateRandomArray(5000);
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

		// Use api sort as a default sort to compare sorts to
		Arrays.sort(sortedArray);

		// call methods that test sorts
		testInsertionSort(unsortedArray.clone(), sortedArray);
		testQuickSort(unsortedArray.clone(), sortedArray);
		testMergeSort(unsortedArray.clone(), sortedArray);
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
