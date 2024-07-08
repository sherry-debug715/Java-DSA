package DP.Coordinates;
// lintcode 115 
// Time: O(m * n)
public class UniquePath2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        int rowSize = obstacleGrid.length;
        int colSize = obstacleGrid[0].length;
        int[][] dp = new int[rowSize][colSize];
        // populate first row and first col with 1s, if pos is 1 stop 
        for (int i = 0; i < rowSize; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 1; i < colSize; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }

        for (int i = 1; i < rowSize; i++) {
            for (int j = 1; j < colSize; j++) {
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[rowSize - 1][colSize - 1];
    }
}
