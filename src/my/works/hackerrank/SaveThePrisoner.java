package my.works.hackerrank;

public class SaveThePrisoner {
	
	public static int saveThePrisoner(int n, int m, int s) {
		
		for (int i=s; i<=n; i++, m--) {
			if(m==1)
				return i;		
			if(i==n) 
				i=0;
		}
		return 0;
	}
	
	public static int enhancedSaveThePrisoner(int n, int m, int s) {
		
		int given = n-s+1;
		int bal = m-given;
		if(bal<1) 
			return n-Math.abs(bal);
		else
			return bal%n==0 ? n : bal%n;

	}

}
