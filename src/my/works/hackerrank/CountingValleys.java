package my.works.hackerrank;

public class CountingValleys {

	public static int countingValleys(int n, String s) {	
		int uCount = 0;
		int dCount = 0;
		int valleyCount = 0;
		for (char c : s.toCharArray()) {
			if(c == 'U') uCount++;
			else if(c == 'D') dCount++;
			
			if(uCount == dCount && c == 'U') {
				valleyCount++;
				uCount = 0;
				dCount = 0;
			}
		}
		return valleyCount;
	}
	
}
