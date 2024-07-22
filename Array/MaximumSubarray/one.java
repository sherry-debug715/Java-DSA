package Array.MaximumSubarray;
// lintcode 41
// Time: O(N)
// Space: O(1)
public class one {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int maxSum = nums[0], curSum = nums[0];

        for (int i = 1; i < n; i++) {
            curSum = Math.max(nums[i], curSum + nums[i]);
            maxSum = Math.max(maxSum, curSum);
        }

        return maxSum;
    }
}
