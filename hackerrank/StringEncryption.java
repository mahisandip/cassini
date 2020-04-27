package hackerrank;

/**
 * refer to
 * https://www.hackerrank.com/challenges/encryption/problem
 */
public class StringEncryption {
	
	public static String encryption(String s) {
		
		s = s.replaceAll("\\s", "");
		int length = s.length();
		int col = (int)Math.ceil(Math.sqrt(length));
		
		StringBuilder sb = new StringBuilder();
		for(int i=0, count=0; i<col; i++) {
			sb.append(s.charAt(i));
			while((count+=col) < length) {
				sb.append(s.charAt(count));
			}
			count = i+1;
			sb.append(" ");
		}
		return sb.toString();
	}

}
