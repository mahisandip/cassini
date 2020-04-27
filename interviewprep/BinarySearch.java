package interviewprep;

public class BinarySearch {

	public static int binarySearch(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
		return binarySearch(a, key, low, high);
	}

	/**
	 * 
	 * implementation from Arrays.binarySearch
	 */
	public static int binarySearch(int[] a, int key, int from, int to) {

		while (from <= to) {
			int mid = (from + to) >> 1;
			@SuppressWarnings("rawtypes")
			Comparable midVal = (Comparable) a[mid];
			@SuppressWarnings("unchecked")
			int compare = midVal.compareTo(key);
			
			if (compare<0)
				from = mid + 1;
			else if (compare>0)
				to = mid - 1;
			else
				return mid; // returns the key if found
		}
		return -1;
	}

	public static int recursiveBinarySearch(int a[], int key) {

		int low = 0;
		int high = a.length-1;
		return recursiveBinarySearch(a, key, low, high);
	}

	public static int recursiveBinarySearch(int arr[], int key, int l, int r) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr[mid] == key)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] > key)
                return recursiveBinarySearch(arr, key, l, mid - 1);

            // Else the element can only be present
            // in right subarray
            return recursiveBinarySearch(arr, key, mid + 1, r);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

}
