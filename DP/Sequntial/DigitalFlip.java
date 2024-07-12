package DP.Sequntial;
// lintcode 843
// Time: O(n)
// Space: O(n)
public class DigitalFlip {
    public int flipDigit(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n + 1][2];
        dp[0][0] = dp[0][1] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                int t = 0; // check if nums[i - 1] need to flip to be j
                if (nums[i - 1] != j) {
                    t += 1;
                }
                dp[i][j] = Integer.MAX_VALUE;
                // for the previous i - 1 th number, the cost of converting the kth state to j
                for (int k = 0; k < 2; k++) {
                    if (k == 0 && j == 1) { // cannot convert a 0 to 1 
                        continue;
                    }

                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + t);
                } 
            }
        }
        return Math.min(dp[n][0], dp[n][1]);
    }
}
