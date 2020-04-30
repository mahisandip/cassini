package interviewprep;

import java.util.ArrayList;
import java.util.List;

public class StockBuySellToMaxProfit {

	public static int getMaxProfit(int[] arr) {

		int min = Integer.MAX_VALUE;
		int profit = 0;

		for (int i = 0; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
			if (profit < arr[i] - min) {
				profit = arr[i] - min;
			}

		}

		return profit;
	}
	
	public static List<Integer> getMaxProfits(int[] arr) {
	    
	    int min = arr[0];
	    int profit = 0;
	    List<Integer> res = new ArrayList<>(); 
	    
	     for(int i=1; i<arr.length; i++) {
	      if(arr[i] < min) {
	    	  min = arr[i];
		      res.add(profit);
	      }
	      if(profit < arr[i]-min){
	        profit = arr[i]-min;
	      }
	        
	    }
	    res.add(profit);
	    return res;
	  }
	
	public static void stockBuySell() {
		
	}

}
