package my.works.hackerrank;

import java.io.UnsupportedEncodingException;


public class SpecialStringAgain {

	//
	public static long substrCount( String s) {
	    long count = 0;
	    for (int i = 0; i < s.length(); i++) {
	        int innerCounter = 1;

	        int counterDown = 0;
	        int counterUp = 1;
	        while (i - innerCounter >= 0 && i + innerCounter < s.length()
	                && s.charAt(i - innerCounter) == s.charAt(i - 1) && s.charAt(i + innerCounter) == s.charAt(i - 1)) {
	            count++;
	            innerCounter++;
	        }

	        while (i - counterDown >= 0 && i + counterUp < s.length() && s.charAt(i - counterDown) == s.charAt(i)
	                && s.charAt(i + counterUp) == s.charAt(i)) {
	            count++;
	            counterDown++;
	            counterUp++;
	        }
	    }

	    return count + s.length();
	}
	
	// optimized one from above
	public static long substrCount(int length, String s) {
		long counter = 0;
		for (int i = 0; i < length; i++) {
			// if the current symbol is in the middle of palindrome, e.g. aba
			int offset = 1;
			while (i - offset >= 0 && i + offset < length && s.charAt(i - offset) == s.charAt(i - 1)
					&& s.charAt(i + offset) == s.charAt(i - 1)) {
				counter++;
				offset++;
			}
			// if this is repeatable characters aa
			int repeats = 0;
			while (i + 1 < length && s.charAt(i) == s.charAt(i + 1)) {
				repeats++;
				i++;
			}
			counter += repeats * (repeats + 1) / 2;
		}
		return counter + length;
	}

	public static String hexStringToASCII(String sb) throws UnsupportedEncodingException  {
		int countOfHexValues = sb.length() / 2;
		byte[] bytes = new byte[countOfHexValues];

		for (int i = 0; i < countOfHexValues; i++) {
			int hexValueIndex = i * 2;
			String hexValue = sb.substring(hexValueIndex, hexValueIndex + 2);
			bytes[i] = (byte) (Integer.parseInt(hexValue, 16) & 0xFF);
		}
		return new String(bytes, "CP1047");
	}
	
}
