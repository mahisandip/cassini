package my.works.codility;

import java.util.Arrays;

public class PermCheck {

	public int solution(int[] A){
		int n = A.length;
		if(n==1&&A[0]!=1)
			return 0;
		else if (n==1&&A[0]==1)
			return 1;
		else {
			Arrays.sort(A);
			for(int i=0;i<n;i++) {
				if(A[i]!=i+1)
					return 0;
			}
			return 1;
		}
	}

}
