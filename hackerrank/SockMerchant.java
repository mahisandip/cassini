package hackerrank;

import java.util.HashSet;

public class SockMerchant {

	public static int sockMerchant(int n, int[] ar) {
		
		if (n == 1) {
			return 0;
		}
		if (n == 2) {
			return ar[0] == ar[1] ? 1 : 0;
		}

		HashSet<Integer> set = new HashSet<>();
		for (int i : ar) {
			if (set.contains(i))
				set.remove(i);
			else
				set.add(i);
		}
		System.out.println(set.size());
		return (n - set.size())/2;
	}

}
