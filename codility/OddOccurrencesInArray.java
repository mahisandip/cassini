package codility;

import java.util.HashSet;

public class OddOccurrencesInArray {

	public int solution(int[] A) {

		HashSet<Integer> set = new HashSet<>();
		for (int i=0;i<A.length;i++) {
			if(!set.contains(A[i]))
				set.add(A[i]);
			else
				set.remove(A[i]);
		}

		return set.iterator().next();
	}

}
