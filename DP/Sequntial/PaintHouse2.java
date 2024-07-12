package DP.Sequntial;
// Lintcode 516
// Time: O(nk)
// Space: O(k)
public class PaintHouse2 {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int rowSize = costs.length;
        int k = costs[0].length;
        int[] dp = new int[k];
        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        for (int c = 0; c < k; c++) {
            dp[c] = costs[0][c];
            if (costs[0][c] < min) {
                secondMin = min;
                min = costs[0][c];
            } else if (costs[0][c] < secondMin) {
                secondMin = costs[0][c];
            }
        }

        for (int r = 1; r < rowSize; r++) {
            int newMin = Integer.MAX_VALUE, newSecondMin = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                if (dp[j] != min) {
                    dp[j] = costs[r][j] + min;
                } else {
                    dp[j] = costs[r][j] + secondMin;
                }
                if (dp[j] < newMin) {
                    newSecondMin = newMin;
                    newMin = dp[j];
                } else if (dp[j] < newSecondMin) {
                    newSecondMin = dp[j];
                }
            }
            min = newMin;
            secondMin = newSecondMin;
        }
        // find the least costs in dp 

        int minCost = Integer.MAX_VALUE;
        for (int n : dp) {
            minCost = Math.min(minCost, n);
        }
        return minCost;
    }
}
