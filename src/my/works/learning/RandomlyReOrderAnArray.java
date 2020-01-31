package my.works.learning;

import java.util.Random;

/**
 * Randomly re order a given array of integers
 * 
 * Also known as Fisher-Yates shuffle or Knuth shuffle
 *
 */
public class RandomlyReOrderAnArray {
	
	public static int[] randomlyReOrderAnArray(int[] arr) {

		Random r = new Random();
		for(int i=arr.length-1; i>0; i--) {
			int indexToSwap = r.nextInt(i+1);
			swap(arr, i, indexToSwap);
		}
		return arr;
	}
	
	private static void swap(int[] arr, int from, int to) {
		if(from == to)
			return;
		int temp = arr[from];
		arr[from] = arr[to];
		arr[to] = temp;
	}

}
