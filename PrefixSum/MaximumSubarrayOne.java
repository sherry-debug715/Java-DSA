package PrefixSum;

public class MaximumSubarrayOne {
    public int maxSubArray(int[] nums) {
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < prefixSum.length; i++) {
            int curMaxSum = Math.max(prefixSum[i - 1] + nums[i], nums[i]);
            prefixSum[i] = curMaxSum;
            maxSum = Math.max(maxSum, curMaxSum);
        }
        return maxSum;

    }
}
