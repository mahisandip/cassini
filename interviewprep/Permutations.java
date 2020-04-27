package interviewprep;

import java.util.ArrayList;

public class Permutations {
	
	public static ArrayList<String> getPermutations(String s) {
		ArrayList<String> results = new ArrayList<>();
		permutations("", s, results);
		return results;
	}
	
	private static void permutations(String prefix, String suffix, ArrayList<String> results) {
		if(suffix.length() == 0) {
			results.add(prefix);
		} else {
			for(int i=0; i<suffix.length(); i++) {
				String p = prefix + suffix.charAt(i);
				String s = suffix.substring(0, i) + suffix.substring(i+1, suffix.length());
				permutations(p, s, results);
			}
		}
	}
	
	public static ArrayList<int[]> getPermutations(int[] a) {
		
		ArrayList<int[]> results = new ArrayList<>();
		permutations(a, 0, results);
		return results;
	}
	
	private static void permutations(int[] a, int start, ArrayList<int[]> results) {
		if(start >= a.length) {
			results.add(a.clone());
		} else {
			for(int i=start; i<a.length; i++) {
				swap(a, start, i);
				permutations(a, start+1, results);
				swap(a, start, i);
			}
		}
	}
	
	private static void swap(int[] a, int i, int j) {
		if(i==j) return;
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
