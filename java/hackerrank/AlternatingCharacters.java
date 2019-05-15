package my.works.hackerrank;

/**
 * You are given a string containing characters  and  only. Your task is to change it into a
 * string such that there are no matching adjacent characters. To do this, you are allowed to
 * delete zero or more characters in the string. Your task is to find the minimum number of
 * required deletions.
 * For example, given the string s = AABAAB, remove an A at positions 0 and 3 to make s = ABAB
 * in  deletions.
 *
 *
 * @author sandeep.b.nair
 *
 */

public class AlternatingCharacters {

	public static int alternatingCharacters(String s) {
		int len = s.length();
		int count = 0;
		if(len<2)
			return 0;
		char toggler = s.charAt(0);
		for(int i=1;i<s.length();i++){
			if(s.charAt(i) == toggler)
				count++;
			else
				toggler = s.charAt(i);
		}

		return count;
    }

}
