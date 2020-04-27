package leetcode;

/**
 * 
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 */
public class ReveseInteger {
	
	public static int reverse(int x) {
		try {
			String s = String.valueOf(x);
			if (s.startsWith("-")) {
				return Integer.parseInt("-".concat(new StringBuilder(s.substring(1)).reverse().toString()));
			}
			return Integer.parseInt(new StringBuilder(s).reverse().toString());
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static long reverse(long x) {
		String s = String.valueOf(x);
		if(s.startsWith("-")) {
			return Long.parseLong("-".concat(new StringBuilder(s.substring(1)).reverse().toString()));
		}
		return Long.parseLong(new StringBuilder(s).reverse().toString());
    }
	
	public static int reverse1(int x) {
		String reversed = new StringBuilder().append(Math.abs(x)).reverse().toString();
		try {
		    return (x < 0) ? Integer.parseInt(reversed) * -1 : Integer.parseInt(reversed);
		} catch (NumberFormatException e) {
		    return 0;
		}
	}
	
	public static int reverse2(int x) {
		int rev = 0;
	    while (x != 0) {
	        int pop = x % 10;
	        x /= 10;
	        if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
	        if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
	        rev = rev * 10 + pop;
	    }
	    return rev;
	}
	
	public static int reverse3(int x) {
		try {
			if (x<0) {
				return Integer.parseInt(new StringBuilder().append(Math.abs(x)).reverse().toString()) * -1;
			}
			return Integer.parseInt(new StringBuilder().append(x).reverse().toString());
		} catch (Exception e) {
			return 0;
		}
	}
	
}
