package my.works.learning;

/**
 * Find the nth Fibonacci number
 * 1. Using recursion
 * 2. Using Bottom up approach
 *
 */
public class Fibonacci {
	
	public static int recursiveFibonacci(int n) {
		
		if(n == 0) 
			return 0;
		
		int[] memo = new int[n+1];
		memo[1] = 1;
		memo[2] = 1;
		return fib(memo, n);
	}
	
	private static int fib(int[] memo, int n) {
		
		if(memo[n] != 0) {
			return memo[n]; 
		}
		int res;
		if(n==1 || n == 2)
			res = 1;
		
		res = fib(memo, n-1) + fib(memo, n-2);
		memo[n] = res;
		return res;
	}
	
	public static int bottomUpFibonacci(int n) {
		
		if(n == 0)
			return 0;
		int[] arr = new int[n+1];
		arr[1] = 1;
		arr[2] = 1;
		
		for(int i=3; i<=n; i++) {
			arr[i] = arr[i-1] + arr[i-2];
		}
		return arr[n];
	}

}
