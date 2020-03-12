package my.works.hackerrank;

/**
 *
 * Dynamic Programming...
 * Given an array of integers, find the subset of non-adjacent elements with the maximum sum.
 * Calculate the sum of that subset.For example, given an array [-2,1,3,-4,5]  we have the
 * following possible subsets:
 *
 * 	Subset      Sum
	[-2, 3, 5]   6
	[-2, 3]      1
	[-2, -4]    -6
	[-2, 5]      3
	[1, -4]     -3
	[1, 5]       6
	[3, 5]       8
	[]			 0
 *
 *
 *@author sandeep.b.nair
 */

public class MaxArraySum {

	public int maxSubsetSum(int[] arr) {

		if(arr.length == 0) return 0;
		arr[0] = Math.max(0, arr[0]);
		if(arr.length == 1 ) return arr[0];
		arr[1] = Math.max(arr[0], arr[1]);
		for(int i=2; i<arr.length; i++)
			arr[i] = Math.max(arr[i-1], arr[i]+arr[i-2] );

		return arr[arr.length-1];
	}

}
