package DP.Coordinates;
// leetcode 91
// Time: O(length of s)
public class DecodeWays {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        
        int l = s.length();
        int[] dp = new int[l + 1];
        dp[0] = 1;
        
        char[] sArr = s.toCharArray();
        for (int i = 1; i <= l; i++) {
            if (sArr[i - 1] != '0') {
                dp[i] += dp[i - 1];
            }
            if (i >= 2) {
                int prevTwo = (sArr[i - 2] - '0') * 10 + (sArr[i - 1] - '0');
                if (prevTwo >= 10 && prevTwo <= 26) {
                    dp[i] += dp[i - 2];
                }
            }

        }
        return dp[l];
    }
}
