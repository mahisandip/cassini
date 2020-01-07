package my.works.learning;

import java.util.HashMap;
import java.util.Map;

/**
 * Find the maximum number of subsets which adds up to
 * a certain total
 * 
 * eg: 
 * arr = [2,4,6,10]
 * total = 16
 * 
 * The subsets which can be added up to 16 are
 * {6, 10}
 * {2, 4, 10}
 * 
 * Output = 2
 *
 */
public class MaxNumberOfSubsets {
	
	public static int maxNumberOfSubsets(int[] arr, int total) {
		Map<String, Integer> map = new HashMap<>();
		return dp(arr, total, arr.length-1, map);
	}
	
	private static int dp(int[] arr, int total, int i, Map<String, Integer> map) {
		String key = String.valueOf(total) + " : " + String.valueOf(i);
		Integer val = 0;
		if(map.containsKey(key))
			return map.get(key);
		else if(total == 0)
			return 1;
		else if(total < 0) 
			return 0;
		else if(i < 0)
			return 0;
		else if(total < arr[i])
			val = dp(arr, total, i-1, map);
		else 
			val = dp(arr, total-arr[i], i-1, map) +
				dp(arr, total, i-1, map);
		
		map.put(key, val);
		return val;
	}
}
