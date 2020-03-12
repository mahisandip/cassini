package my.works.learning;

import java.io.UnsupportedEncodingException;

public class HexStringToASCII {
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
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
	
	public static String stringToHexString(String str) throws UnsupportedEncodingException {
		byte[] bytes = str.getBytes("CP1047");
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

}
