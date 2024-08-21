package DP.Sequntial;
// Leetcode 664
// Time: O(N^2)
// Space: O(N^2)
public class StrangePrinter {
    public int strangePrinter(String s) {
        if (s.length() == 1) {
            return 1;
        }
        int n = s.length();
        char[] sArr = s.toCharArray();
        // dp[i][j] is the number of printing takes to finish printing s[i:j]
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            // it takes 1 print to finish printing dp[i][i]
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (sArr[i] == sArr[j]) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    /*
                    when they differs:
                    imaging there is a divider k that divide s[i:j] where i <= k < j
                    the number of printing needed should be min(number of printing of s[i:k] + s[k + 1: j])
                    */
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }
        return dp[0][n - 1];

    }
}
