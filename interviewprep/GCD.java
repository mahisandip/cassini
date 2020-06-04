package interviewprep;

public class GCD {

	public static int findGCDOfTwoNumbersUsingForLoop(int num1, int num2) {
		int gcd = 1;
		for (int i = 1; i <= num1 && i <= num2; i++) {
			if (num1 % i == 0 && num2 % i == 0)
				gcd = i;
		}
		return gcd;
	}

	public static int findGCDOfTwoNumbersUsingRecursion(int num1, int num2) {
		if (num1 != num2) {

			if (num1 > num2)
				num1 = num1 - num2;
			else
				num2 = num2 - num1;

			return findGCDOfTwoNumbersUsingRecursion(num1, num2);
		}
		return num1;
	}

	public static int findGCDOfTwoNumbers(int num1, int num2) {

		while (num1 != num2) {
			if (num1 > num2)
				num1 = num1 - num2;
			else
				num2 = num2 - num1;
		}
		return num2;
	}

	public static int findGCDOfNNumbers(int[] nums) {
		int result = findGCDOfTwoNumbers(nums[0], nums[1]);
		for (int i = 2; i < nums.length; i++)
			result = findGCDOfTwoNumbers(result, nums[i]);
		return result;
	}

}
