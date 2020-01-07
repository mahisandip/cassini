package my.works.learning;

import java.io.UnsupportedEncodingException;

public class HexStringToASCII {
	
	public static String hexStringToASCII(String sb) throws UnsupportedEncodingException {
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
