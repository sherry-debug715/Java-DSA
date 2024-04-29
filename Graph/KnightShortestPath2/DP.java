package Graph.KnightShortestPath2;

public class DP {
    int[][] POS = {
        {-1, -2},
        {1, -2},
        {-2, -1},
        {2, -1}
    };

    public int shortestPath2(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int rowSize = grid.length;
        int colSize = grid[0].length;
        // if start or destination is blocked 
        if (grid[0][0]|| grid[rowSize - 1][colSize - 1]) {
            return -1;
        }

        int[][] dp = new int[rowSize][colSize];
        // populate dp with max value 
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;
        for (int col = 0; col < colSize; col++) {
            for (int row = 0; row < rowSize; row++) {
                if (grid[row][col]) {
                    continue;
                }
                for (int[] pos : POS) {
                    int fromRow = row + pos[0];
                    int fromCol = col + pos[1];
                    if (inBound(fromRow, fromCol, grid, dp)) {
                        // it's necessary to make sure dp[row][col] != Integer.MAX_VALUE, in Java
                        // Integer.MAX_VALUE + 1 will cause integer overflow, and wraps around to Integer.MIN_VALUE.
                        dp[row][col] = Math.min(dp[row][col], dp[fromRow][fromCol] + 1);
                    }
                }
            }
        }
        if (dp[rowSize - 1][colSize - 1] == Integer.MAX_VALUE) {
            return -1;
        }

        return dp[rowSize - 1][colSize - 1];
    }

    private boolean inBound(int row, int col, boolean[][] grid, int[][] dp) {
        if (row < 0 || row >= grid.length) {
            return false;
        }
        if (col < 0 || col >= grid[0].length) {
            return false;
        }
        if (dp[row][col] == Integer.MAX_VALUE) {
            return false;
        }
        return true;
    }
}
