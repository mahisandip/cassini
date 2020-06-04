package interviewprep;

import java.util.HashMap;
import java.util.Map;

public class DistinctPairsSumUpToInteger {

	public static int SumPairs(Integer[] input, int k) {

		Map<Integer, Integer> map = new HashMap<>();
		int pairsCount = 0;

		for (int i = 0; i < input.length; i++) {

			int value = input[i];
			int complement = k - value;

			if (map.containsKey(complement)) {
				int freq = map.get(complement) - 1;
				pairsCount++;
				
				if (freq == 0) {
					map.remove(complement);
				} else {
					map.put(complement, freq);
				}

			} else {
				if (map.containsKey(value)) {
					map.put(value, map.get(value) + 1);
				} else {
					map.put(value, 1);
				}
			}
		}
		return pairsCount;
	}

}
