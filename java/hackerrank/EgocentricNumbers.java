package my.works.hackerrank;

import java.util.HashMap;
import java.util.Map;

public class EgocentricNumbers {

    public int egocentricNumbers(int input) {

    	int sum = 0;
    	int length = String.valueOf(Math.abs(input)).length();
    	int i = 1;
    	int temp = input;

    	while(i<=length) {
    		int rem = temp%10;
    		temp = temp/10;

    		sum += Math.pow(rem, length);

    		i++;
    	}

    	if(sum == input)
    		return 1;
    	return 0;
    }

    public int enhancedEgocentricNumbers(int input) {

    	int sum = 0;
    	int length = String.valueOf(Math.abs(input)).length();
    	int i = 1;
    	int temp = input;

    	Map<Integer, Integer> map = new HashMap<>();

    	while(i<=length) {
    		int rem = temp%10;
    		temp = temp/10;

    		if(map.containsKey(rem))
    			sum += map.get(rem);
    		else {
    			int s = (int)Math.pow(rem, length);
    			map.put(rem, s);
    			sum+=s;
    		}
    		i++;
    	}

    	if(sum == input)
    		return 1;
    	return 0;
    }

}
