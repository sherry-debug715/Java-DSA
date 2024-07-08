package DP.Coordinates;

public class MaximumProductSubarray {
    // Time: O(N);
    // Space: O(1);
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        int maxPos = nums[0], prevPos = nums[0];
        int maxNeg = nums[0], prevNeg = nums[0];

        for (int i = 1; i < n; i++) {
            maxPos = Math.max(nums[i], Math.max(prevPos * nums[i], prevNeg * nums[i]));
            maxNeg = Math.min(nums[i], Math.min(prevPos * nums[i], prevNeg * nums[i]));
            max = Math.max(max, maxPos);
            prevPos = maxPos;
            prevNeg = maxNeg;
        }
        return max;
    }
}
