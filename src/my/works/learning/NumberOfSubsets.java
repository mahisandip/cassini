package my.works.learning;

/**
 * Given an array with unique elements. Print  all the 
 * subsets that can be made from that array.
 * 
 * eg:
 * arr =[1, 2]
 * 
 * Output = 
 * {}
 * {1}
 * {2}
 * {1,2}
 *
 */
public class NumberOfSubsets {
	
	public static void printAllSubsets(int[] arr) {
		Integer[] subsets = new Integer[arr.length];
		printAllSubsets(arr, subsets, 0);
	}
	
	private static void printAllSubsets(int[] arr, Integer[] subsets, int i) {
		if(i == subsets.length)
			print(subsets);
		else {
			subsets[i] = null;
			printAllSubsets(arr, subsets, i+1);
			subsets[i] = arr[i];
			printAllSubsets(arr, subsets, i+1);
		}
	}
	
	private static void print(Integer[] subsets) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Integer i : subsets) {
			if(i != null ) 
				sb.append(i + ",");
		}
		if(sb.length() > 1)
			sb.deleteCharAt(sb.length()-1);
		sb.append("}");
		System.out.println(sb.toString());
	}

}
