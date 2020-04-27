package hackerrank;

public class NumbersMood {

    static String numbers_mood(int number) {

    	while (number!=1 && number !=4){
    		int temp = 0;
    		int[]digits = Integer.toString(number).chars().map(c -> c-'0').toArray();
    		for(int i = 0; i<digits.length; i++) {
    			temp += Math.pow(digits[i], 2);
    		}
    		number = temp;
    	}

    	if (number == 1)
    		return "happy";
    	else if (number == 4)
    		return "sad";
    	else return "test";
    }

}
