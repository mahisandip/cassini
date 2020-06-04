package interviewprep;

public class Atoi {

	public static Integer atoi(String n) {

		if (n == null || n.isEmpty())
			new IllegalArgumentException("Empty String");

		boolean isNeg = false;

		if (n.startsWith("-")) {
			isNeg = true;
			n = n.substring(1);

		} else if (n.startsWith("+")) {
			n = n.substring(1);
		}

		if (!n.matches("[0-9]+")) {
			throw new NumberFormatException("Not a number");
		}

		long result = 0;
		for (char c : n.toCharArray())
			result = result * 10 + (c - '0');

		if (isNeg)
			result *= -1;

		if (result > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		else if (result < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		return (int) result;
	}

}
