package my.works.learning;

import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Find the K smaller elements in an array
 * 
 * eg: 
 * arr : [5, 2, 7, 1, 10, 15, 8]
 * k = 3
 * 
 * Output = [1, 2, 5]
 *
 */
public class KSmallerElements {
	
	public static int[] findKSmallerElements(int[] arr, int k) {
		
		int[] res = new int[k];
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0; i<k; i++) 
			pq.add(arr[i]);
		
		for(int i=k; i<arr.length; i++) {
			if(arr[i] < pq.peek()) {
				pq.poll();
				pq.add(arr[i]);
			}
		}
		
		Iterator<Integer> itr = pq.iterator();
		for(int j=0; itr.hasNext(); j++)
			res[j] = itr.next();
		
		return res;
	}

}
