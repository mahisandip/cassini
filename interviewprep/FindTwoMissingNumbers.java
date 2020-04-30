package interviewprep;

public class FindTwoMissingNumbers {
	
	public static int[] findTwoMissingNumbers(int[] arr) {
		
		int n = arr.length  + 2;
		
		long total = (n *(n+1)) /2;
		long arrTotal = 0;
		for(int i: arr) 
			arrTotal += i;
		
		int pivot = (int)(total - arrTotal) / 2;
		
		int totalLeftXor = totalXor(1, pivot);
		int totalRightXor = totalXor(pivot+1, n);
		int arrleftXor = arrXor(arr, 0, pivot);
		int arrRightXor = arrXor(arr, pivot, arr.length);
		
		return new int[] {totalLeftXor^arrleftXor, totalRightXor^arrRightXor};
	}
	
	private static int totalXor(int startIndex, int endIndex) {
		
		int totalXor = 0;
		for(int i=startIndex; i<=endIndex; i++) 
			totalXor ^= i;
		
		return totalXor;
	}
	
	private static int arrXor(int[] arr, int startIndex, int endIndex) {
		
		int arrXor = 0;
		for(int i=startIndex; i<endIndex; i++) 
			arrXor ^= arr[i];
		
		return arrXor;
	}

}
