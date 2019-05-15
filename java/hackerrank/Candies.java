package my.works.hackerrank;

import java.util.Arrays;

/**
 * Alice is a kindergarten teacher. She wants to give some candies to the children in her class.
 * All the children sit in a line and each of them has a rating score according to his or her performance in the class.
 * Alice wants to give at least 1 candy to each child. If two children sit next to each other, then the one with the
 * higher rating must get more candies. Alice wants to minimize the total number of candies she must buy.
 * For example, assume her students' ratings are [4, 6, 4, 5, 6, 2].
 * She gives the students candy in the following minimal amounts: [1, 2, 1, 2, 3, 1].
 * She must buy a minimum of 10 candies.
 *
 * @author sandeep.b.nair
 *
 */

public class Candies {

	static long candies(int n, int[] arr) {
        long sum = 0;
        int[] left2right = new int[n];
        int[] right2left = new int[n];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }

}
