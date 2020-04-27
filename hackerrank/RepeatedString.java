package hackerrank;

public class RepeatedString {

	public static long repeatedString(String s, long n) {
		long len = s.length();
		if(n<len) return getCountOfLetterA(s, n);
		if(len == n) return getCountOfLetterA(s, len);
		long countOfA = getCountOfLetterA(s, len);
		countOfA *= n/len;
		if((n%len)!=0)
			countOfA += getCountOfLetterA(s,n%len);
		
		return countOfA;
    }
	
	static long getCountOfLetterA(String s, long len) {
		long count = 0;
		for (int i=0; i<len; i++) 
			if(s.charAt(i)=='a') count++;
		return count;
	}
}
