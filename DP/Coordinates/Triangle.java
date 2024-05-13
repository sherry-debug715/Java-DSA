package DP.Coordinates;

public class Triangle {
    public int minimumTotal(int[][] triangle) {
        if (triangle == null || triangle.length == 0) {
            return 0;
        }

        int n = triangle[triangle.length - 1].length;
        int[] dp = new int[n];
        // populate dp with numbers of last row 
        for (int i = 0; i < n; i++) {
            dp[i] = triangle[triangle.length - 1][i];
        }

        // iterate over triangle from the last second row 
        for (int i = triangle.length - 2; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle[i][j];
            }
        }
        return dp[0];
    }
}
