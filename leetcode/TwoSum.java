package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 *Given nums = [2, 7, 11, 15], target = 9,
 *Because nums[0] + nums[1] = 2 + 7 = 9,
 *return [0, 1].
 */
public class TwoSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int nums[] = {2,4,6,15};

		SolutionTwoSum sol = new SolutionTwoSum();

		int result1[] = sol.twoSum1(nums, 8);
		for (int i = 0; i < result1.length; i++) {
			System.out.print(result1[i]+", ");
		}
		System.out.println();

		int result2[] = sol.twoSum2(nums, 8);
		for (int i = 0; i < result2.length; i++) {
			System.out.print(result2[i]+", ");
		}
		System.out.println();

		int result3[] = sol.twoSum3(nums, 8);
		for (int i = 0; i < result3.length; i++) {
			System.out.print(result3[i]+", ");
		}
	}

}

class SolutionTwoSum {
    public int[] twoSum1(int[] nums, int target) {
    	for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i] == (target - nums[j])) {
					return new int[]{i,j};
				}
			}
		}
        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
    	Map<Integer, Integer> map = new HashMap();
    	for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
    	for (int i = 0; i < nums.length; i++) {
			int var = target - nums[i];
			if(map.containsKey(var) && map.get(var)!=i)
				return new int[]{i, map.get(var)};
		}
    	return null;
    }

    public int[] twoSum3(int[] nums, int target) {
    	Map<Integer, Integer> map = new HashMap();
    	for (int i = 0; i < nums.length; i++) {
    		int var = target - nums[i];
    		if(map.containsKey(var)) {
    			return new int[]{map.get(var),i};
    		}
    		map.put(nums[i],i);
		}
    	return null;
    }
}