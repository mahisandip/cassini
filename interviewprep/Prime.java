package interviewprep;

public class Prime {

	public static boolean isPrime(int n) {
		for (int i = 2; 2 * i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static boolean isPrimeEnhanced(int n) {

		if (n == 2)
			return true;

		// check if n is a multiple of 2
		if (n % 2 == 0)
			return false;

		// if not, then just check the odds
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

}
