package codesignal;

import java.util.HashMap;
import java.util.Map;

public class CommonCharacterCount {
	
	public static int commonCharacterCount(String s1, String s2) {
		
		Map<Character, Integer> m1 = getMap(s1);
		Map<Character, Integer> m2 = getMap(s2);
		if(m1.size() > m2.size()) 
			return getCount(m1, m2);
		return getCount(m2, m1);
	}
	
	private static Map<Character, Integer> getMap(String s) {
		
		Map<Character, Integer> map = new HashMap<>();
		for(Character c : s.toCharArray()) {
			if(map.containsKey(c)) {
				Integer n = map.get(c);
				map.replace(c, ++n);
			} else 
				map.put(c, 1);
		} 
		
		return map;
	}
	
	private static int getCount(Map<Character, Integer> bigger, Map<Character, Integer> smaller) {
		int count = 0;
		for(Map.Entry<Character, Integer> entry : bigger.entrySet()) {
			char c = entry.getKey();
			if(smaller.containsKey(c)) {
				count += Math.min(entry.getValue(), smaller.get(c));
			}
		}
		return count;
	}
	
	public static void printMap(Map<Character, Integer> map) {

		for (Map.Entry<Character, Integer> entry : map.entrySet())  
            System.out.println("Key = " + entry.getKey() + 
                             ", Value = " + entry.getValue());
	}

}
