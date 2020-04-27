package hackerrank;

import java.util.Arrays;

/**
 * Rotate an n x n matrix
 *
 */
public class MatrixRotation {
	
	/**
	 * Rotate a matrix in anti clock wise direction n times. 
	 * The space complexity is linear(O(n)), as this method creates a new output matrix
	 * 
	 * @param mat input matrix
	 * @param times number of time the matrix needs to be rotated
	 * @return rotated matrix
	 */
	public static int[][] rotateMatrix(int[][] mat, int times) {
		
		int n = mat.length; //Assuming that the matrix is of order n x n
		int[][] out = new int[n][n];
		
		// if n is  an odd  number, the center most element will not be populated into the output matrix.
		// So do it here explicitly
		if(n%2==1) {
			out[n/2][n/2] = mat[n/2][n/2];
		}

		while(times > 0) {
			int colStart = 0;
			int colEnd = n-1;
			int rowStart = 0;
			int rowEnd = n-1;
			
			while(colEnd > colStart) {
				
				for(int i=colStart; i<colEnd; i++) 
					out[rowStart][i] = mat[rowStart][i+1];
				
				for(int i=rowStart+1; i<=rowEnd; i++) 
					out[i][rowStart] = mat[i-1][rowStart];
				
				for(int i=colStart+1; i<=colEnd; i++) 
					out[rowEnd][i] = mat[rowEnd][i-1];
				
				for(int i=rowStart; i<rowEnd; i++) 
					out[i][rowEnd] = mat[i+1][rowEnd];
				
				rowStart++;
				colStart++;
				rowEnd--;
				colEnd--;
			}
			times--;
			
			//Copy the contents of output matrix into the input matrix in order to use it in the next iteration
			mat = Arrays.stream(out).map(int[]::clone).toArray(int[][]::new);
		}
		return out;
	}
	
	/**
	 * Rotate a matrix in anti clock wise direction n times. 
	 * The space complexity is constant(O(1)), as this method does not creates a new output matrix
	 * 
	 * @param mat input matrix
	 * @param times number of time the matrix needs to be rotated
	 * @return rotated matrix
	 */
	public static int[][] enhancedRotateMatrix(int[][] mat, int times) {
		
		int n = mat.length; //Assuming that the matrix is of order n x n

		while(times > 0) {
			int colStart = 0;
			int colEnd = n-1;
			int rowStart = 0;
			int rowEnd = n-1;
			
			while(colEnd > colStart) {
				
				int cache = mat[rowStart][colStart];
				for(int i=colStart; i<colEnd; i++) {
					mat[rowStart][i] = mat[rowStart][i+1];
				}
				
				for(int i=rowStart+1; i<=rowEnd; i++) {
					int temp = cache;
					cache = mat[i][rowStart];					
					mat[i][rowStart] = temp;
				}
				
				for(int i=colStart+1; i<=colEnd; i++) {
					int temp = cache;
					cache = mat[rowEnd][i];
					mat[rowEnd][i] = temp;
				}
				
				for(int i=rowEnd-1; i>=rowStart; i--) {
					int temp = cache;
					cache = mat[i][rowEnd];
					mat[i][rowEnd] = temp;
				}
				
				rowStart++;
				colStart++;
				rowEnd--;
				colEnd--;
			}
			times--;
		}
		return mat;
	}

}
