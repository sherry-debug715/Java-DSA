package BinarySearch;

import java.util.Arrays;
import java.util.List;

// lintcode 390 Two Solution 
// Time: O(m + n)
// Space: O(1)
class Solution1 {
    /**
	1.	Start from the middle column: Find the maximum element in the middle column.
	2.	Compare with neighbors: Compare this element with its left and right neighbors to determine the direction to move.
	•	If the current element is greater than both left and right neighbors, it is a peak.
	•	If the current element is less than the left neighbor, move to the left half of the matrix.
	•	If the current element is less than the right neighbor, move to the right half of the matrix.
	3.	Continue narrowing down: Repeat the process, but now focusing only on the relevant half (left or right) until you find a peak.
     */
    public List<Integer> findPeakII(int[][] a) {
        int n = a.length, m = a[0].length;
        int left = 1, right = m - 2;
        while (left <= right) {
            int midC = left + (right - left) / 2;
            int maxRow = findMaxInColumn(a, midC); // O(n)

            if (a[maxRow][midC] > a[maxRow][midC - 1] && a[maxRow][midC] > a[maxRow][midC + 1]) {
                return Arrays.asList(maxRow, midC);
            }
            if (a[maxRow][midC] < a[maxRow][midC - 1]) {
                right = midC - 1;
            } else {
                left = midC + 1;
            }

        }
        return new ArrayList<>();
    }


    private int findMaxInColumn(int[][] a, int col) { // O(n)
        int maxRow = 0;
        int n = a.length;
        for (int r = 1; r <= n - 2; r++) {
            if (a[r][col] > a[maxRow][col]) {
                maxRow = r;
            }
        }
        return maxRow;
    }
}
// Solution 2
// Time: O(mlogn)
// Space: O(1)
public class FindPeakEle2 {
    public List<Integer> findPeakII(int[][] a) {
        int n = a.length, m = a[0].length;
        int left = 1, right = m - 2, top = 1, bottom = n - 2;
        while (top <= bottom) {
            int midRow = top + (bottom - top) / 2;
            int peakCol = findRowPeak(a[midRow], left, right); // O(m)
            // check peak 
            if (a[midRow - 1][peakCol] < a[midRow][peakCol] && a[midRow][peakCol] > a[midRow + 1][peakCol]) {
                return Arrays.asList(midRow, peakCol);
            }
            if (a[midRow][peakCol] < a[midRow + 1][peakCol]) {
                top = midRow + 1;
            } else {
                bottom = midRow - 1;
            }

        }
        return new ArrayList<>();
    }


    private int findRowPeak(int[] arr, int left, int right) {
        int maxIdx = left;
        for (int j = left; j <= right; j++) {
            if (arr[j] > arr[maxIdx]) {
                maxIdx = j;
            }
        }
        return maxIdx;
    }
}
