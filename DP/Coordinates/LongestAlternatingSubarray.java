package DP.Coordinates;

import java.util.Arrays;

// Leetcode 2765
// Time: O(N)
// Space: O(N)
public class LongestAlternatingSubarray {
    public int alternatingSubarray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int longest = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if (Math.abs(diff) != 1) {
                continue;
            }
            if (diff == 1) {
                if (i - 2 >= 0 && nums[i - 1] - nums[i - 2] == -1) {
                    dp[i] += dp[i - 1];
                } else {
                    dp[i] += 1;
                }
                longest = Math.max(longest, dp[i]);
            } else if (diff == -1) {
                if (i - 2 >= 0 && nums[i - 1] - nums[i - 2] == 1) {
                    dp[i] += dp[i - 1];
                    longest = Math.max(longest, dp[i]);
                } 
            }
        }
        if (longest == Integer.MIN_VALUE) {
            return -1;
        }
        return longest;
    }
}
