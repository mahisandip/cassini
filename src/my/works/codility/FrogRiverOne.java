package my.works.codility;

import java.util.HashSet;

public class FrogRiverOne {

	public int solution(int X, int[] A) {
		int sum = (X*(X+1))/2;
		HashSet<Integer> set = new HashSet<>();
		for(int i=0;i<A.length;i++) {
			if (A[i] <= X && !set.contains(A[i])) {
				set.add(A[i]);
				sum = sum - A[i];
			}
			if(sum==0)
				return i;
		}
		return -1;
	}
}
