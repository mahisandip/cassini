package my.works.codility;

public class PermMissingElem {

	public int solution(int[] A) {

		int n = A.length;
		if (n == 0)
			return 1;
		long sum = ((n + 1) * (n + 2)) / 2;

		for (int i : A) {
			sum = sum - (long) i;
		}
		return (int) sum;
	}
}
