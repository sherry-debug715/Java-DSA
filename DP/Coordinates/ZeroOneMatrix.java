package DP.Coordinates;
// Time: O(m*n)
// Space: O(m*n)
public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int[][] dp = new int[n][m];
        for (int[] arr: dp) {
            Arrays.fill(arr, Integer.MAX_VALUE / 2);
        } 
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    dp[i][j] = 0;
                }
            }
        }
        
        // coming up and left
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (r - 1 >= 0) {
                    dp[r][c] = Math.min(dp[r][c], dp[r - 1][c] + 1);
                }
                if (c - 1 >= 0) {
                    dp[r][c] = Math.min(dp[r][c], dp[r][c - 1] + 1);
                }          
            }
        }
        
        // coming from bottom and right 
        for (int r = n - 1; r >= 0; r--) {
            for (int c = m - 1; c >= 0; c--) {
                if (r + 1 < n) {
                    dp[r][c] = Math.min(dp[r][c], dp[r + 1][c] + 1);
                }
                if (c + 1 < m) {
                    dp[r][c] = Math.min(dp[r][c], dp[r][c + 1] + 1);
                }          
            }
        }
        
        // coming from up and right 
        for (int r = 0; r < n; r++) {
            for (int c = m - 1; c >= 0; c--) {
                if (r - 1 >= 0) {
                    dp[r][c] = Math.min(dp[r][c], dp[r - 1][c] + 1);
                }
                if (c + 1 < m) {
                    dp[r][c] = Math.min(dp[r][c], dp[r][c + 1] + 1);
                }          
            }
        }
        
        // coming from bottom and left
        for (int r = n - 1; r >= 0; r--) {
            for (int c = 0; c < m; c++) {
                if (r + 1 < n) {
                    dp[r][c] = Math.min(dp[r][c], dp[r + 1][c] + 1);
                }
                if (c - 1 >= 0) {
                    dp[r][c] = Math.min(dp[r][c], dp[r][c - 1] + 1);
                }          
            }
        }
        

        return dp;
    }
}
