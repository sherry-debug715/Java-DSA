package DP.PartitionDP;
// lintcode 108
// time: O(n^2)
// Space: O(N^2)
public class PalindromePartitioning2 {
       // approach:
    // find out the minimum number of palindrome in the given string, substract 1 will be the min cuts 
    public int minCut(String ss) {
        char[] s = ss.toCharArray();
        if (s == null || s.length <= 1) {
            return 0;
        }

        boolean[][] palindromeMap = getPanlindromeLen(s);
        // palindromeMap[i][j] references if ss[i:j] is a valid palindrome 
        int n = s.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (palindromeMap[j][i - 1]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n] - 1;
    }

    private boolean[][] getPanlindromeLen(char[] s) { // time: o(n^2)
        int n = s.length;
        boolean[][] palindromeMap = new boolean[n + 1][n + 1];
        int left = 0, right = 0;
        for (int i = 0; i < n; i++) {
            // odd palindrome 
            left = right = i;
            while (left >= 0 && right < n && s[left] == s[right]) {
                palindromeMap[left][right] = true;
                left -= 1;
                right += 1;
            }

            // even palindrome 
            right = i;
            left = i - 1;
            while (left >= 0 && right < n && s[left] == s[right]) {
                palindromeMap[left][right] = true;
                left -= 1;
                right += 1;
            } 
        }

        return palindromeMap;
    }
}
