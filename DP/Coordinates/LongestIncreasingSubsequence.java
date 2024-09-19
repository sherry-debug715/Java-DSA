package DP.Coordinates;

import java.util.Arrays;

// Find (2) solution below.
// Lintcode 76 
// Binary search solution
class Solution {
    /**
     * @param nums: An integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
     // Time: O(nlogn)
     // Space: O(n)
    public int longestIncreasingSubsequence(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        // At least one element is a valid subsequence
        int maxLen = 1;
        // lisTail[i - 1] represents the smallest number on the left of nums[i] 
        int[] lisTail = new int[n + 1];
        lisTail[maxLen] = nums[0]; 

        for (int i = 1; i < n; i++) {
            if (nums[i] > lisTail[maxLen]) {
                // If nums[i] extends the largest subsequence
                lisTail[++maxLen] = nums[i];
            } else { 
                // binary search on current LIS to find the smallest number in lisTail < nums[i]
                int l = 1, r = maxLen, pos = 0;
                while (l <= r) {
                    int m = l + (r - l) / 2;
                    if (lisTail[m] < nums[i]) {
                        pos = m;
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }
                // replace an existing LIS val with nums[i]
                lisTail[pos + 1] = nums[i];
            }
        }

        return maxLen;
    }
}





// Time: O(nums.length * i)
// Space: O(N)
// DP solution 
public class LongestIncreasingSubsequence {
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
        }
        return maxLen;
    }
}
