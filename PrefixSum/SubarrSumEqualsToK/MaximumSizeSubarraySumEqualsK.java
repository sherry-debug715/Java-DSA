package PrefixSum.SubarrSumEqualsToK;
// Lintcode problem 911: https://www.lintcode.com/problem/911/description
public class MaximumSizeSubarraySumEqualsK {
     // Time: O(N)
     // Space: O(N)
     public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // key is the prefix, key is the earliest index which the prefixSum
        // has occured.
        Map<Integer, Integer> prefixSumIdx = new HashMap<>();
        // Initialize with 0 at index -1, helps to handle cases where prefix sum == k.
        prefixSumIdx.put(0, -1);
        int prefixSum = 0;
        int output = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            // checks if there's a previous prefixSum, when subtracted from the current 
            // prefixSum == k.
            if (prefixSumIdx.containsKey(prefixSum - k)) {
                output = Math.max(output, i - prefixSumIdx.get(prefixSum - k));
            }
            if (!prefixSumIdx.containsKey(prefixSum)) {
                prefixSumIdx.put(prefixSum, i);
            }
        }
        return output;
    }
}
