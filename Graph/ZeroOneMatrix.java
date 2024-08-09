package Graph;
// Lintcode 974 two solutions below 
// Time: O(n * m)
// DP solution
public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][0];
        }

        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int r = 0; r < n; r++) { // O(n * m)
            for (int c = 0; c < m; c++) {
                if (matrix[r][c] == 1) {
                    dp[r][c] = Integer.MAX_VALUE / 2;
                }
            }
        }

        // left to right and up to down 
        for (int r = 0; r < n; r++) { // O(n * m)
            for (int c = 0; c < m; c++) {
                if (r  - 1 >= 0) {
                    dp[r][c] = Math.min(dp[r][c], dp[r - 1][c] + 1);
                }
                if (c - 1 >= 0) {
                    dp[r][c] = Math.min(dp[r][c], dp[r][c - 1] + 1);
                }
            }
        } 

        // left to right, bottom up 
        for (int r = n - 1; r >= 0; r--) { // O(n * m)
            for (int c = 0; c < m; c++) {
                if (r + 1 < n) {
                    dp[r][c] = Math.min(dp[r][c], dp[r + 1][c] + 1);
                }
                if (c - 1 >= 0) {
                    dp[r][c] = Math.min(dp[r][c], dp[r][c - 1] + 1);
                }
            }
        } 

        // right to left, bottom up 
        for (int r = n - 1; r >= 0; r--) { // O(n * m)
            for (int c = m - 1; c >= 0; c--) {
                if (r + 1 < n) {
                    dp[r][c] = Math.min(dp[r][c], dp[r + 1][c] + 1);
                }
                if (c + 1 < m) {
                    dp[r][c] = Math.min(dp[r][c], dp[r][c + 1] + 1);
                }
            }
        } 

        // right to left and up to down 
        for (int r = 0; r < n; r++) { // O(n * m)
            for (int c = m - 1; c >= 0; c--) {
                if (r  - 1 >= 0) {
                    dp[r][c] = Math.min(dp[r][c], dp[r - 1][c] + 1);
                }
                if (c + 1 < m) {
                    dp[r][c] = Math.min(dp[r][c], dp[r][c + 1] + 1);
                }
            }
        } 

        return dp;

    }
}
// BFS solution 
class Solution {
    /**
     * @param matrix: a 0-1 matrix
     * @return: return a matrix
     */
    int[] POS = new int[]{-1, 0, 1, 0, -1};
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][0];
        }

        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n][m]; 
        boolean[][] visited = new boolean[n][m];
        Deque<int[]> queue = new ArrayDeque<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (matrix[r][c] == 0) {
                    queue.offer(new int[]{r, c, 0});
                    visited[r][c] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.removeFirst();
            int row = curr[0], col = curr[1], dis = curr[2];
            // look for 0s 
            for (int i = 0; i < POS.length - 1; i++) {
                int newR = row + POS[i], newC = col + POS[i + 1];
                if (valid(newR, newC, visited, n, m)) {
                    queue.offer(new int[]{newR, newC, dis + 1});
                    dp[newR][newC] = dis + 1;
                    visited[newR][newC] = true;
                }
            }
        }
        return dp;
    }

    private boolean valid(int r, int c, boolean[][] visited, int n, int m) {
        if (r < 0 || r >= n) {
            return false;
        }
        if (c < 0 || c >= m) {
            return false;
        }
        if (visited[r][c]) {
            return false;
        }
        return true;
    }
}
