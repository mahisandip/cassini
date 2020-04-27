package interviewprep;

/**
 * Merge two sorted arrays
 * 
 * eg: 
 * a = [2,6,9]
 * b = [1,7,8]
 * 
 * Output = [1,2,6,7,8,9]
 *
 */
public class MergeTwoSortedArrays {
	
	public static int[] mergeTwoSortedArrays(int[] a, int[] b) {
		
		int m = a.length;
		int n = b.length;
		int i = 0;
		int j = 0;
		int[] res = new int[m+n];
		
		while(i<m && j<n) {
			if(a[i] <= b[j]) {
				res[i+j] = a[i];
				i++;
			} else {
				res[i+j] = b[j];
				j++;
			}
		}
		if(i < m) {
			for(;i<m;i++) 
				res[i+j] = a[i];
		} else {
			for(;j<n;j++)
				res[i+j] = b[j];
		}
		
		return res;
	}

}
