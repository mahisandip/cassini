package hackerrank;

import java.util.HashMap;
import java.util.Map;

/**
 * refer to
 * https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
 *
 */
public class ClimbingTheLeaderBoard {
	
	public static int[] climbingLeaderboard(int[] scores, int[] alice) {
		
		Map<Integer,Integer> map = new HashMap<>();
		int rank = 0;
		
		for(int i=0; i< scores.length; i++) {
			if(!map.containsKey(scores[i])) {
				map.put(scores[i], ++rank);
			}
		}
		
		int[] ranks = new int[alice.length];
		int count = 0;
		for(int i : alice) {
			int score = getScore(i, scores, 0, scores.length-1);
			if(i<score)
				ranks[count++] = map.get(score) + 1;
			else
				ranks[count++] = map.get(score);
		}
		
		return ranks;
	}
	
	private static int getScore(int score, int[] scores, int low, int high) {
		
		if(low == high)
			return scores[low];
		int mid = (low + high) / 2;
		if(score ==  scores[mid])
			return scores[mid];
		if(score < scores[mid]) 
			return getScore(score, scores, mid+1, high);
		return getScore(score, scores, low, mid);
		
	}

}
