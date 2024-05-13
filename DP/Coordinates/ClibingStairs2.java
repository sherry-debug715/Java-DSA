package DP.Coordinates;

public class ClibingStairs2 {
    public int climbStairs2(int n) {
        if (n < 2) {
            return 1;
        }
        int[] dp = new int[n + 1]; // i: steps, val: ways.
        dp[0] = 1; 
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}
