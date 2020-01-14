package my.works.learning;

import java.util.PriorityQueue;

/**
 * Merge k sorted arrays
 * 
 * eg: 
 * a = [2,6,9]
 * b = [1,7,8]
 * c = [0,3,4,5]
 * 
 * Output = [0,1,2,3,4,5,6,7,8,9]
 *
 */
public class MergeKSortedArrays {
	
	public static int[] mergeKSortedArrays(int[]... arrays) {		
		return merge(arrays);
	}
	
	private static int[] merge(int[]... arrays) {
		PriorityQueue<QueueNode> pq = new PriorityQueue<>();
		int size = 0;
		for(int i=0; i<arrays.length; i++) {
			size += arrays[i].length;
			if(arrays[i].length > 0) 
				pq.add(new QueueNode(i, 0, arrays[i][0]));
		}
		
		int[] res = new int[size];
		for(int i=0; !pq.isEmpty(); i++) {
			QueueNode n = pq.poll();
			res[i] = n.value;
			int newIndex = n.index+1;
			if(newIndex < arrays[n.array].length) {
				pq.add(new QueueNode(n.array, newIndex, arrays[n.array][newIndex]));
			}
		}
		
		return res;
	}
	
	private static class QueueNode implements Comparable<QueueNode> {
		
		// to keep track of the array in which least element is in
		private Integer array;
		private Integer index;
		private Integer value;
				
		public QueueNode(Integer array, Integer index, Integer value) {
			this.array = array;
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(QueueNode o) {
			if(value > o.value) return 1;
			if(value < o.value) return -1;		
			return 0;
		}
		
	}
}
