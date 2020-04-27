package main;

import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
	}
	
	static void printIntArray(int[] arr) {
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	static void printIntList(List<Integer> results) {
		for (int i : results) {
			System.out.println(i + " ");
		}
	}
	
	static void printStringList(List<String> results) {
		for (String string : results) {
			System.out.println(string);
		}
	}
	
	static String deleteLastTwoWordsFromString(String s) {
		s = s.replaceFirst(".{2}$", "");
		return s;
	}
	
}

