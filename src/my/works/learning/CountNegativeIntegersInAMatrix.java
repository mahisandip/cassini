package my.works.learning;

/**
 * Count Negative Integers in Row/Column-Wise Sorted Matrix
 * 
 * Given a matrix
 * -3, -2, -1,  1
 * -2,  2,  3,  4
 *  4,  5,  7,  8
 * 
 * Output  = 4
 * 
 */
public class CountNegativeIntegersInAMatrix {
	
	public static int countNegativeIntegersInAMatrix(int[][] mat) {
		
		int n = mat.length;
		int m = mat[0].length;
		int i = 0;
		int j = m-1;
		int count = 0;
		
		while(j>=0 && i<n) {
			if(mat[i][j] < 0) {
				count += (j+1);
				i +=1;
			} else {
				j -= 1;
			}
		}
		
		return count;
	}

}
