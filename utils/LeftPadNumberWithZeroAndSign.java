package utils;

import java.math.BigDecimal;

public class LeftPadNumberWithZeroAndSign {
	
	public static String retrieveLeftPaddedAmountWithSign(
			BigDecimal amountToBeLeftPadded, int fieldLength) {

		String signForAmount = amountToBeLeftPadded.signum() < 0 ? "-" : "+";

		String leftPaddedResult = leftPadGivenAmount(amountToBeLeftPadded,
				fieldLength - 1);

		return signForAmount + leftPaddedResult;
	}
	
	private static String leftPadGivenAmount(BigDecimal amountToBeLeftPadded,
			int fieldLength) {

		String amountToBeLeftPaddedStr = amountToBeLeftPadded.toString()
				.replace(".", "");

		amountToBeLeftPaddedStr = amountToBeLeftPadded.signum() < 0 ? amountToBeLeftPaddedStr
				.replace("-", "") : amountToBeLeftPaddedStr;

		StringBuilder leftPaddedAmount = new StringBuilder();

		while (leftPaddedAmount.length() < fieldLength
				- amountToBeLeftPaddedStr.length()) {
			leftPaddedAmount.append('0');
		}

		leftPaddedAmount.append(amountToBeLeftPaddedStr);

		return leftPaddedAmount.toString();
	}

}
