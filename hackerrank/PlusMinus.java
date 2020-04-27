package hackerrank;

public class PlusMinus {

    static void plusMinus(int[] arr) {

    	int pos = 0;
    	int neg = 0;
    	int zer = 0;
    	int size = arr.length;

    	for(int i=0; i<size; i++) {

    		if(arr[i] < 0)
    			neg++;
    		else if(arr[i] > 0)
    			pos++;
    		else zer++;
    	}

    	float r1 = (float)pos/(float)size;
    	float r2 = (float)neg/(float)size;
    	float r3 = (float)zer/(float)size;

    	System.out.println(String.format("%.6f",r1));
    	System.out.println(String.format("%.6f",r2));
    	System.out.println(String.format("%.6f",r3));
    }

}
