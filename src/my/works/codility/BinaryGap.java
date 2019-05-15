package my.works.codility;

public class BinaryGap {

    public int solution(int N) {

    	String binary = Integer.toString(N, 2);
    	String[] arr = binary.split("1");
    	int length = 0;
    	int result = 0;
    	if(binary.charAt(binary.length()-1)=='1')
    		length = arr.length;
    	else
    		length = arr.length-1;

    	for (int i=0;i<length;i++) {
			if (arr[i].isEmpty())
				continue;
			else
				result = result<arr[i].length()?arr[i].length():result;
		}

        return result;
    }

}
