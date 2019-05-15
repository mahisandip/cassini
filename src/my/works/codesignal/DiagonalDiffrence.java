package my.works.codesignal;

public class DiagonalDiffrence {

    static int diagonalDifference(int[][] arr) {

    	int size = arr.length;
    	int i = 0;
    	int diag1 = 0;
    	int diag2 = 0;

    	while (i<size) {
    		diag1 += arr[i][i];
    		diag2 += arr[i][(size-1)-i];
    		i++;
    	}

    	return Math.abs(diag1-diag2);
    }

}
