package Graph;
// Leetcode 363

import java.util.TreeSet;

public class MaxSumOfRectagleNoLargerThanK {
    // Time: O(m^2 * NlogN))
    // Space: O(2n) 
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int maxSum = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;

        for (int top = 0; top < m; top++) { // O(m) Time
            int[] columnSum = new int[n]; // O(n) Space 
            for (int row = top; row < m; row++) { // O(m) Time
                TreeSet<Integer> prefixSum = new TreeSet<>(); // O(n) Space 
                prefixSum.add(0); // O(logN) Time
                int curSum = 0;
                for (int col = 0; col < n; col++) { // O(n) Time 
                    columnSum[col] += matrix[row][col];
                    curSum += columnSum[col];
                    // update maxSum;
                    if (curSum == k) {
                        return k;
                    }
                    // find the smallest prefixSum >= curSum - k
                    Integer minPrefixSum = prefixSum.ceiling(curSum - k); // O(logN) Time 
                    if (minPrefixSum != null) {
                        maxSum = Math.max(maxSum, curSum - minPrefixSum);
                        if (maxSum == k) {
                            return k;
                        }
                    }

                    prefixSum.add(curSum); // O(logN) time
                }
            }
        }
        // System.out.println(maxSum);
        if (maxSum == Integer.MIN_VALUE) {
            return -1;
        }
        return maxSum;
    }
}
