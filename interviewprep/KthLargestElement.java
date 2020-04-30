package interviewprep;

import java.util.PriorityQueue;

public class KthLargestElement {
	
	public static int kthLargestElement(int[] array, int k) {
		
		if(k > array.length || k < 1)
			throw new IllegalArgumentException("Invalid value for k");
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0; i<k; i++) {
			pq.add(array[i]);
		}
		
		for(int i=k; i<array.length; i++) {
			if(array[i] > pq.peek()) {
				pq.poll();
				pq.add(array[i]);
			}
		}
		
		return pq.peek();
	}

}
