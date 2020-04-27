package interviewprep;

/**
 * Find the median elements of two sorted arrays. The two sorted arrays are of same length
 * <p>
 * eg:
 *   arr1 = {1,3,5}
 *   arr2 = {2,4,6}
 *   
 *   output = {1,2,3,4,5,6} = (3+4)/2 = 3.5
 * </p>
 *
 */
public class MedianOfTwoSortedArrays {

	private static class SubArray {
		
		private int[] underlying;
		private int start;
		private int size;
		
		private static SubArray fromArray(int[] arr) {
			SubArray s = new SubArray();
			s.underlying = arr;
			s.start = 0;
			s.size = arr.length;
			return s;
		}
	
		private SubArray subArray(int i, int j) {
			if(i>j) throw new IllegalArgumentException();
			if(j>this.size) throw new IndexOutOfBoundsException();
			SubArray s = new SubArray();
			s.underlying = this.underlying;
			s.start = this.start+i;
			s.size = j-1;
			return s;
		}
		
		private int getSize() {
			return this.size;
		}
		
		private int getFirst() {
			return this.underlying[start];
		}
		
		private int getLast() {
			return underlying[start+size-1];
		}
		
		private double getMedian() {
			if(this.size%2 == 0) {
				return (underlying[start + size / 2 -1] + underlying[start + size / 2]) / 2.;
			} 
			return underlying[start+size/2];
		}
	}
	
	public static double median(int[] arr1, int[] arr2) {
		if(arr1.length == 0 || arr1.length != arr2.length)
			throw new IllegalArgumentException();
		return median(SubArray.fromArray(arr1), SubArray.fromArray(arr2));
	}
	
	public static double median(SubArray arr1, SubArray arr2) {
		if(arr1.getSize() == 1)
			return (arr1.getFirst() + arr2.getFirst()) / 2;
		if(arr1.getSize() == 2)
			return ((Math.max(arr1.getFirst(), arr2.getFirst()) + Math.min(arr1.getLast(), arr1.getLast())) /2 );
		
		double median1 = arr1.getMedian();
		double median2 = arr2.getMedian();
		
		if(median1 == median2)
			return median1;
		
		if(median1 > median2)
			return median(arr1.subArray(0, arr1.getSize()/2 + 1), 
					arr2.subArray((arr2.getSize()-1) / 2 , arr2.getSize()));
		
		return median(arr1.subArray((arr1.getSize()-1) / 2, arr2.getSize()), 
				arr2.subArray(0, arr2.getSize()/2 + 1));
	}
}
