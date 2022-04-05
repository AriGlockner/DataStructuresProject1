import java.util.Arrays;
import java.util.Random;

public class Sort
{
	/**
	 * Sorts the array using an insertion sort algorithm.
	 * Runtimes:
	 * Worse Case: O(N^2)
	 * Best Case: O(N)
	 * Average Case: O(N^2)
	 *
	 * @param arr integer array to sort
	 */
	public static void insertionSort(int[] arr)
	{
		int len = arr.length;

		for (int i = 1; i < len; i++)
			if (arr[i] < arr[i - 1])
			{
				int min = arr[i];
				int j = i;
				while (j > 0 && min < arr[j - 1])
					arr[j] = arr[--j];
				arr[j] = min;
			}
	}

	/**
	 * Sorts the array using a quick sort algorithm
	 * Runtime:
	 * Worst Case: O(N^2)
	 * Best Case: O(N log(N))
	 * Average Case: O(N log(N))
	 *
	 * @param arr Integer array to sort
	 */
	public static void quickSort(int[] arr)
	{
		qSort(arr, 0, arr.length - 1);
	}

	/**
	 * Helper method for the quickSort method. Calls the partition method and sorts each half of the partition
	 *
	 * @param arr  array to sort.
	 * @param low
	 * @param high
	 */
	private static void qSort(int[] arr, int low, int high)
	{
		if (low < high)
		{
			// Create a partition to divide the array into 2
			int partition = partition(arr, low, high);

			// Sort each side of the array
			qSort(arr, low, partition - 1);
			qSort(arr, partition + 1, high);
		}
	}

	/**
	 * Helper method for the qSort method. Finds the partition for the array.
	 *
	 * @param arr
	 * @param low
	 * @param high
	 * @return the partition for the array
	 */
	private static int partition(int[] arr, int low, int high)
	{
		int pivot = arr[high];
		int i = low - 1;


		for (int j = low; j < high; j++)
			if (arr[j] <= pivot)
			{
				i++;

				// swap array's i & j index's
				int placeholder = arr[i];
				arr[i] = arr[j];
				arr[j] = placeholder;
			}

		int temp = arr[++i];
		arr[i] = arr[high];
		arr[high] = temp;

		return i;
	}


	/**
	 * Sorts the array using a merge sort algorithm. This method preforms the split function, and then calls the merge
	 * helper method to do the merging of the arrays after they have been separated.
	 * Runtime:
	 * Worst Case: O(N log(N))
	 * Best Case: O(N log(N))
	 * Average Case: O(N log(N))
	 *
	 * @param arr Integer array to sort
	 */
	public static void mergeSort(int[] arr)
	{

		int len = arr.length;
		if (len < 2)
			return;
		int mid = len/2;

		// split into left and right
		int[] left = Arrays.copyOfRange(arr, 0, mid);
		int[] right = Arrays.copyOfRange(arr, mid, len);
		mergeSort(left);
		mergeSort(right);

		// combine the sub-arrays together
		merge(arr, left, right);
	}

	/**
	 * Helper method for the mergeSort method that merges the two arrays and stores the new array created as arr
	 *
	 * @param arr array to sort
	 * @param left left sub-array
	 * @param right right sub-array
	 */
	private static void merge(int[] arr, int[] left, int[] right)
	{
		// Create index pointers
		int lIndex = 0;
		int rIndex = 0;
		int i = 0;

		// Iterate through left and right and pulling out the smallest value to add to arr
		while (i < arr.length)
		{
			// Can't compare any longer
			if (lIndex == left.length || rIndex == right.length)
				break;

			// add from left list
			if (left[lIndex] <= right[rIndex])
			{
				arr[i++] = left[lIndex++];
				if (lIndex == left.length)
					break;
			}

			// add from right list
			else
			{
				arr[i++] = right[rIndex++];
				if (rIndex == right.length)
					break;
			}
		}

		// add remaining items from left list
		while (lIndex < left.length)
			arr[i++] = left[lIndex++];
		// add remaining items from right list
		while (rIndex < right.length)
			arr[i++] = right[rIndex++];
	}

	/**
	 * @param n length of the array
	 * @param a lower bounds of the random numbers
	 * @param b upper bounds of the random numbers
	 * @return an array of n random integers in the interval [a, b]
	 */
	public static int[] randomArray(int n, int a, int b)
	{
		Random rand = new Random();
		int[] array = new int[n];

		for (int i = 0; i < n; i++)
			array[i] = rand.nextInt(a, b);

		return array;
	}

	/**
	 * Demo
	 *
	 * @param args arguments
	 */
	public static void main(String[] args)
	{
		// Initialize Arrays
		int[] array = randomArray(10, 0, 100);
		int[][] arrays = new int[][] {array.clone(), array.clone(), array.clone(), array.clone()};

		// Sort Arrays
		insertionSort(arrays[1]);
		quickSort(arrays[2]);
		mergeSort(arrays[3]);

		// Print Arrays
		String[] arrayTypes = new String[] {"Unsorted", "Insertion", "Quick", "Merge"};
		for (int i = 0; i < arrayTypes.length; i++)
			System.out.println(arrayTypes[i] + ":\n" + Arrays.toString(arrays[i]));
	}
}
