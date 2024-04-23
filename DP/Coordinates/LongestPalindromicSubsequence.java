package DP.Coordinates;
// Time: O(m * n)
// Space: O(m * n)
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // edge case 
        if (s == null || n == 0) {
            return 0;
        }
        // dp[i][j]represent the longest palindromic subsequence from s[i:j+1]  
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                // if dp[i] == dp[j], need to check the length of palindromn between 
                // i + 1 and j - 1.
                if (s.charAt(j) == s.charAt(i)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // if dp[i] != dp[j], we take the max length of 1. remove i, 2. remove j.
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
