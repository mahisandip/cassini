package java.codesignal;

import java.util.HashSet;

//"abcabcbb"
//abc, bca, cab, abc, bc, cb

public class LongestSubset {

    public int lengthOfLongestSubstring(String s) {

    	HashSet<Character> set = new HashSet<>();
    	int dummy = 0;
    	char[] chars = s.toCharArray();
    	for (char c : chars) {
    		if(set.contains(c)) {
    			dummy  = set.size();
    			set.clear();
    			set.add(c);
    		}
    		else
    			set.add(c);
		}
    	return set.size()>dummy?set.size():dummy;
    }
}
