package interviewprep;

/**
 * Decode a message into characters, where the message is in number format. 
 * Given that
 *  a = 1
 *  b = 2
 *  .
 *  .
 *  .
 *  z = 26
 * 
 * 
 * eg: 
 * msg = "12345"
 * This msg can be decoded into "abcde", "lcde", "awde"
 * Output = 3
 *
 */
public class NumWaysToDecodeMessage {
	
	public static int decodeMessage(String msg) {
		Integer[] memo = new Integer[msg.length()+1];
		return decode(msg, msg.length(), memo);
		
	}
	
	private static int decode(String msg, int k, Integer[] memo) {
		
		if(k == 0)
			return 1;
		
		int s = msg.length() - k;
		if(msg.charAt(s) == '0')
			return 0;
		
		if(memo[k] != null) {
			return memo[k];
		}
		
		int result = decode(msg, k-1, memo);
		if(k>1 && Integer.parseInt(msg.substring(s, s+2)) <= 26) {
			result += decode(msg, k-2, memo);
		}
		memo[k] = result;
		return result;
	}

}
