package interviewprep;

import java.util.Arrays;

public class RemoveDuplicatesFromArray {
	
	static void eleminateDuplicates(int[] arrayWithDuplicates) {
		
		long st = System.currentTimeMillis();
		
		// Assuming all elements in input array are unique
		int noOfUniqueElements = arrayWithDuplicates.length;
		// get the size of the array
		int size = arrayWithDuplicates.length;
		// Comparing each element with all other elements
		for (int i = 0; i < size; i++) {
			// iterate over elements ahead of the current loop element
			for (int j = i + 1; j < size; j++) {
				// check for equality
				if (arrayWithDuplicates[i] == arrayWithDuplicates[j]) {
					// Replace duplicate element with last unique element
					arrayWithDuplicates[j] = arrayWithDuplicates[size - 1];
					/*
					 * Decrementing size as the element at last location has
					 * already been copied at some start location
					 */
					size--;
					/*
					 * decrement inner loop counter since the new element also
					 * needs to be compared
					 */
					j--;
					// reduce the number of unique elements since we have found
					// a duplicate element
					noOfUniqueElements--;
				}
			}
		}
		
		System.out.println("Time elapsed = " + (System.currentTimeMillis() - st) + " ms");
		
		// Copying only unique elements of arrayWithDuplicates into
		// arrayWithoutDuplicates
		int[] arrayWithoutDuplicates = Arrays.copyOf(arrayWithDuplicates,
				noOfUniqueElements);
		// Printing arrayWithoutDuplicates
		System.out.println("Array Without Duplicates : ");
		for (int i = 0; i < arrayWithoutDuplicates.length; i++) {
			System.out.print(arrayWithoutDuplicates[i]);
			System.out.print(" ");
		}
}

}
