package my.works.leetcode;

public class PalindromeNumber {

	public static boolean isPalindrome(int x) {
        
		String s = String.valueOf(x);
		return s.equals(new StringBuilder(s).reverse().toString());
    }
	
}
