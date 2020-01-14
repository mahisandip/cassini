package my.works.leetcode;

/**
 * Return the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * 
 * Example 1:
 * Input: haystack = "hello", needle = "ll" 
 * Output: 2 
 * 
 * Example 2:
 * Input: haystack = "aaaaa", needle = "bba" Output: -1
 * 
 * For the purpose of this problem, we will return 0 when needle is an empty string
 *
 */
public class NeedleInHaystack {
	
	public static int strStr(String haystack, String needle) {
		
		if(needle.isEmpty())
			return 0;
		
		if(haystack.length() < needle.length())
			return -1;
		
		for(int i=0; i < haystack.length(); i++) {
			for(int j=0; j < needle.length(); j++) {
				if(haystack.charAt(i+j) != needle.charAt(j))
					break;
				if(j+1 == needle.length())
					return i;
				if(i > (haystack.length() - needle.length()))
					break;
			}
		}
		return -1;
	}

}
