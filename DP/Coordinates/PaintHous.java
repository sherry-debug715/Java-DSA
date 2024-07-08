package DP.Coordinates;
// lintcode 515 
// Time: O(N)
// Space: O(N)
public class PaintHous {
    public int minCost(int[][] costs) {
        // write your code here
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int[][] dp = new int[n + 1][3];
        int minCost = Integer.MAX_VALUE;

        for (int r = 1; r <= n; r++) {
            for (int c = 0; c < 3; c++) {
                dp[r][c] = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    if (c == k) {
                        continue;
                    }
                    dp[r][c] = Math.min(dp[r][c], dp[r - 1][k] + costs[r - 1][c]);
                }
                if (r == n) {
                    minCost = Math.min(minCost, dp[r][c]);
                }
            }
        }
        return minCost;
        
    }
}
