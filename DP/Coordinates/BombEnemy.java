package DP.Coordinates;
// Leetcode problem 361: https://leetcode.com/problems/bomb-enemy/
// Time: O(m * n)
// Space: O(m * n)
public class BombEnemy {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length, m = grid[0].length;
        
        int[][] up = new int[n][m];
        int[][] down = new int[n][m];
        int[][] left = new int[n][m];
        int[][] right = new int[n][m];
        
        // up 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') {
                    continue;
                }
                if (grid[i][j] == 'E') {
                    up[i][j] += 1;
                }
                if (i > 0) {
                    up[i][j] += up[i - 1][j];
                }
            }
        }
        
        // down 
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') {
                    continue;
                }
                if (grid[i][j] == 'E') {
                    down[i][j] += 1;
                }
                if (i < n - 1) {
                    down[i][j] += down[i + 1][j];
                }
            }
        }
        
        // left 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') {
                    continue;
                }
                if (grid[i][j] == 'E') {
                    left[i][j] += 1;
                }
                if (j > 0) {
                    left[i][j] += left[i][j - 1];
                }
            }
        }
        
        // right 
        for (int i = 0; i < n; i++) {
            for (int j = m - 1; j >= 0; j--) {
                if (grid[i][j] == 'W') {
                    continue;
                }
                if (grid[i][j] == 'E') {
                    right[i][j] += 1;
                }
                if (j < m - 1) {
                    right[i][j] += right[i][j + 1];
                }
            }
        }
        
        // find solution 
        int bombPos = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0') {
                    bombPos= Math.max(bombPos, up[i][j] + down[i][j] + left[i][j] + right[i][j]);
                }
            }
        }
        return bombPos;
        
    }
}
