package my.works.codility;

public class PassingCars {

	public int solution(int[] A) {
		int n = A.length;
		if (n < 2)
			return 0;
		int count = 0;
		long res = 0;
		for (int i : A) {
			if (i == 0)
				count++;
			else {
				res += count;
			}
		}
		long size = 1000000000;
		if (res > size)
			return -1;
		return (int) res;
	}
}
