package Array.MaximumSubarray;

import java.util.ArrayList;
import java.util.List;

// lintcode 42
public class Two {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    // Time: O(N) for iterate over nums list twice 
    // Space: O(N), storing leftToRight and rightToLeft.
    public int maxTwoSubArrays(List<Integer> nums) {
        if (nums == null || nums.size() == 0 || nums.size() == 1) {
            return 0;
        }

        int n = nums.size();
        // Calculate the max subarray sums from left to right and right to left.
        int[] leftToRight = getLeftToRightMaxSubarraySum(nums, n);
        int[] rightToLeft = getRightToLeftMaxSubarraySum(nums, n);

        int maxSum = Integer.MIN_VALUE;
        //  Max sum from two non-overlapping subarrays.
        for (int i = 0; i < n - 1; i++) {
            maxSum = Math.max(maxSum, leftToRight[i] + rightToLeft[i]);
        }
        return maxSum;
    }

    private int[] getLeftToRightMaxSubarraySum(List<Integer> nums, int n) {
        // leftToRight[i] is the subarray with maxSum of nums[:i] 

        // initialize leftToRight with n - 1 length to eliminate 0
        int[] leftToRight = new int[n - 1];
        int minPrefixSum = 0, sum = 0, maxSum = nums.get(0);
        for (int i = 1; i < n; i++) {
            sum += nums.get(i - 1);
            maxSum = Math.max(maxSum, sum - minPrefixSum);
            leftToRight[i - 1] = maxSum;
            // maintain the cumulative sum to the left 
            minPrefixSum = Math.min(minPrefixSum, sum);
        }

        return leftToRight;
    }

    private int[] getRightToLeftMaxSubarraySum(List<Integer> nums, int n) {
        // rightToLeft[i] is the subarray with maxSum of nums[i + 1:] 

        int[] rightToLeft = new int[n - 1];
        int minSuffixSum = 0, sum = 0, maxSum = nums.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            sum += nums.get(i + 1);
            maxSum = Math.max(maxSum, sum - minSuffixSum);
            rightToLeft[i] = maxSum;
            // maintain the cumulative sum of the right  
            minSuffixSum = Math.min(minSuffixSum, sum);
        }

        return rightToLeft;
    }
}
