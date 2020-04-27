package interviewprep;

import java.util.HashSet;
import java.util.Set;

public class LongestCommonSubstring {
	
	public static int byRec(String s1, String s2) {
		int i = s1.length();
		int j = s2.length();
		return lcs(s1, s2, i, j, 0);
	}
	
	private static int lcs(String p, String q, int i, int j, int count) { 
		  
        if (i == 0 || j == 0) { 
            return count; 
        } 
  
        if (p.charAt(i - 1) == q.charAt(j - 1)) { 
            count = lcs(p, q, i - 1, j - 1, count + 1); 
        } 
        count = Math.max(count, Math.max(lcs(p, q, i, j - 1, 0), 
                            lcs(p, q, i - 1, j, 0))); 
        return count; 
    } 
	
	public static Set<String> printLongestCommonSubstrings(String S1, String S2) {
		Integer match[][] = new Integer[S1.length()][S2.length()];

		int len1 = S1.length();
		int len2 = S2.length();
		int max = Integer.MIN_VALUE;
		HashSet<String> result = null;

		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				if (S1.charAt(i) == S2.charAt(j)) {
					
					if (i == 0 || j == 0) {
						match[i][j] = 1;
					} else {
						match[i][j] = match[i - 1][j - 1] + 1;
					}
					
					if (match[i][j] > max) {
						max = match[i][j];
						result = new HashSet<String>();
						result.add(S1.substring(i - max + 1, i + 1));
					} else if (match[i][j] == max) {
						result.add(S1.substring(i - max + 1, i + 1));
					}
				} else
					match[i][j] = 0;
			}
		}
		return result;
	}
	
	public static String longestCommonSubstring(String a, String b) {
		
		String out = "";
		
		if(a.length() == 0 || b.length() == 0)
			return out;
		
		int[][] cache = new int[a.length()][b.length()];
		
		for(int i=0; i<a.length(); i++) {
			for(int j=0; j<b.length(); j++) {
				if(a.charAt(i) == b.charAt(j)) {
					if(i==0 || j==0) {
						cache[i][j] = 1;
					} else {
						cache[i][j] = cache[i-1][j-1] + 1;
					}
					if(cache[i][j] > out.length()) {
						out = a.substring(i-cache[i][j]+1, i+1);
					}
				}
			}
		}
		return out;
	}

}
