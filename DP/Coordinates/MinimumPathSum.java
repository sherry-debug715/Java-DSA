package DP.Coordinates;
// Lintcode 110 
public class MinimumPathSum {
    public int minPathSumOptimized(int[][] grid) {
        // Time: O(m * n)
        // Space: O(m)
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
    
        int n = grid.length, m = grid[0].length;
        int[] dp = new int[m]; // O(m)
        dp[0] = grid[0][0];
        for (int c = 1; c < m; c++) {
            dp[c] = grid[0][c] + dp[c - 1];
        }
    
        for (int r = 1; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (c == 0) {
                    dp[c] += grid[r][c];
                    continue;
                }
                dp[c] = Math.min(dp[c - 1], dp[c]) + grid[r][c];
            }
        }
    
        return dp[m - 1];
    }
    // time: O(n * m)
     // space: O(n * m)
     public int minPathSumSolution1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m]; // O(n * m)
        dp[0][0] = grid[0][0];
        // for row one, robot and only travel from left to right 
        for (int c = 1; c < m; c++) {
            dp[0][c] = dp[0][c - 1] + grid[0][c];
        }
        // for col one, robot can only travel from up to down 
        for (int r = 1; r < n; r++) {
            dp[r][0] = dp[r - 1][0] + grid[r][0]; 
        }

        for (int r = 1; r < n; r++) {
            for (int c = 1; c < m; c++) {
                dp[r][c] = Math.min(dp[r - 1][c], dp[r][c - 1]) + grid[r][c];
            }
        }

        return dp[n - 1][m - 1];
    } 


    public int minPathSumSolution2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m]; // O(n * m)

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (r == 0 && c == 0) {
                    dp[r][c] = grid[r][c];
                    continue;
                }
                dp[r][c] = Integer.MAX_VALUE;
                if (r > 0) {
                    dp[r][c] = Math.min(dp[r][c], dp[r - 1][c] + grid[r][c]);
                }
                if (c > 0) {
                    dp[r][c] = Math.min(dp[r][c], dp[r][c - 1] + grid[r][c]);
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}
