package DP.Coordinates;
// Lintcode 77 
// Time: O(m * n)
// Space: O(m * n)
public class LCS {
    public int longestCommonSubsequence(String aa, String bb) {
        char[] a = aa.toCharArray();
        char[] b = bb.toCharArray();
        int n = a.length, m = b.length;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // dp[i][j] 表示a array 前i个与b array前j个lcs长度
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[n][m];
    }
}
