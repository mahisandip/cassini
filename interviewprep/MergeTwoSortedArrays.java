package interviewprep;

/**
 * Merge two sorted arrays
 * 
 * eg: a = [2,6,9] b = [1,7,8]
 * 
 * Output = [1,2,6,7,8,9]
 *
 */
public class MergeTwoSortedArrays {

	public static int[] mergeTwoSortedArrays(int[] a, int[] b) {

		int[] res = new int[a.length + b.length];
		int i = 0, j = 0;

		while (i < a.length && j < b.length) {

			if (a[i] < b[j]) {
				res[i + j] = a[i];
				i++;
			}

			else if (b[j] < a[i]) {
				res[i + j] = b[j];
				j++;
			}
		}

		for (; i < a.length; i++)
			res[i + j] = a[i];

		for (; j < b.length; j++)
			res[i + j] = b[j];

		return res;
	}

}
