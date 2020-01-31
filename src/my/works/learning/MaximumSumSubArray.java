package my.works.learning;

/**
 * Find the maximum sum of the sub array.
 * eg: [1, -3, 2, 1, -1]
 * 
 * Output = 3
 * 
 * Uses a variation of Kadane's algorithm
 * 
 */
public class MaximumSumSubArray {
	
	public static int getMaximumSumSubArray(int[] arr) {
		
		int max = arr[0];
		
		for(int i=1; i< arr.length; i++) {
			int tmp = Math.max(arr[i], arr[i] + max);
			if (tmp > max)
				max = tmp;
		}
		
		return max;
	}

}
