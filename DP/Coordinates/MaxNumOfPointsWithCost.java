package DP.Coordinates;

import java.util.Arrays;

// Leetcode 1937 
// Time: O(n * m)
// Space: O(n)
public class MaxNumOfPointsWithCost {
        public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        long[] dp = new long[n];
        // first row of dp has no previous row, therefore value stays the same
        for (int i = 0; i < n; i++) {
            dp[i] = points[0][i];
        }

        long[] left = new long[n];
        long[] right = new long[n];

        for (int r = 1; r < m; r++) {
            // left[i] is from left to right of r - 0 th row, comparing the value of 1. subtract 1 from dp[r - 1][c - 1] or 2. no 
            // palnalty taking, value of dp[r - 1][c] 
            left[0] = dp[0];
            for (int i = 1; i < n; i++) {
                left[i] = Math.max(left[i - 1] - 1, dp[i]);
            } 
            right[n - 1] = dp[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                right[i] = Math.max(right[i + 1] - 1, dp[i]);
            }

            for (int c = 0; c < n; c++) {
                dp[c] = points[r][c] + Math.max(left[c], right[c]);
            }
            Arrays.fill(left, 0);
            Arrays.fill(right, 0);
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
