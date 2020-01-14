package my.works.learning;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Find the K smaller elements in an array
 * 
 * eg: 
 * arr : [5, 2, 7, 1, 10, 15, 8]
 * k = 3
 * 
 * Output = 5
 *
 */
public class KthSmallestElement {
	
	public static int kthSmallestElement(int arr[], int k) {
		
		if(k > arr.length || k < 1)
			throw new IllegalArgumentException("Invalid value for k");
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0; i<k; i++) 
			pq.add(arr[i]);
		
		for(int i=k; i<arr.length; i++) {
			if(arr[i] < pq.peek()) {
				pq.poll();
				pq.add(arr[i]);
			}
		}
		
		return pq.peek();
	}

}
