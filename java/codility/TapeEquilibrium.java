package my.works.codility;

public class TapeEquilibrium {

	public int solution(int[] A) {
		int n = A.length;
		int res =Integer.MAX_VALUE;
		for(int i=1;i<n;i++)
			A[i] = A[i-1]+A[i];
		for(int i=0;i<n-1;i++) {
			int sumRight = A[n-1]-A[i];
			int diff = Math.abs(A[i]-sumRight);
			if(res>diff)
				res=diff;
		}
		return res;
	}

}
