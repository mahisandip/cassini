package my.works.hackerrank;

/**
 *
 * Given a  2D Array, :
 *
 *  1 1 1 0 0 0
	0 1 0 0 0 0
	1 1 1 0 0 0
	0 0 0 0 0 0
	0 0 0 0 0 0
	0 0 0 0 0 0

 * We define an hourglass in  to be a subset of values with indices falling in 
 * this pattern in 's graphical representation:
 * a b c
     d
   e f g

 * There are  hourglasses in , and an hourglass sum is the sum of an hourglass' values.
 * Calculate the hourglass sum for every hourglass in , then print the maximum hourglass sum.
 *
 *@author sandeep.b.nair
 */
public class HourGlassesIn2DArray {

	public int hourglassSum(int[][] arr) {
		int result = Integer.MIN_VALUE;

		for (int i=1;i<5;i++) {
			for (int j=1;j<5;j++) {
				int temp = arr[i][j]+(arr[i-1][j-1]+arr[i-1][j]+arr[i-1][j+1])+(arr[i+1][j-1]+arr[i+1][j]+arr[i+1][j+1]);
				if(result<temp)
					result = temp;
			}
		}

		return result;
	}

}
