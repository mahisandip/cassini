package interviewprep;

public class LongestCommonSubsequence {
	
	public static int byRecursion(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		return lcs(s1, s2, n, m);
	}
	
	private static int lcs(String p, String q, int n, int m) {
		
		int result = 0;
		if(n==0 ||m==0) {
			result = 0;
		} else if(p.charAt(n-1) == q.charAt(m-1)) {
			result = 1 + lcs(p, q, n-1, m-1);
		} else if(p.charAt(n-1) != q.charAt(m-1)) {
			int temp1 = lcs(p, q, n-1, m);
			int temp2 = lcs(p, q, n, m-1);
			result = Math.max(temp1, temp2);
		}
		return result;
	}
	
	public static int byDP(String s1, String s2) {
		
		int n = s1.length();
		int m = s2.length();
		Integer[][] mat = new Integer[n][m];
		return lcs(mat, s1, s2, n, m);
	}
	
	private static int lcs(Integer[][] mat, String p, String q, int n, int m) {
		
		if(mat[n-1][m-1] != null) {
			return mat[n][m];
		}
		int result = 0;
		if(n==0 ||m==0) {
			result = 0;
		} else if(p.charAt(n-1) == q.charAt(m-1)) {
			result = 1 + lcs(p, q, n-1, m-1);
		} else if(p.charAt(n-1) != q.charAt(m-1)) {
			int temp1 = lcs(p, q, n-1, m);
			int temp2 = lcs(p, q, n, m-1);
			result = Math.max(temp1, temp2);
		}
		mat[n-1][m-1] = result;
		return result;
	}

}
