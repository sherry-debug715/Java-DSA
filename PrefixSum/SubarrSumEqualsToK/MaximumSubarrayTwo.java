package PrefixSum.SubarrSumEqualsToK;

public class MaximumSubarrayTwo {
    public int maxTwoSubArrays(List<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int n = nums.size();
        List<Integer> prefixMax = new ArrayList(nums);
        List<Integer> backfixMax = new ArrayList(nums);

        for (int i = 1; i < n; i++) {
            prefixMax.set(i, Math.max(nums.get(i), prefixMax.get(i - 1) + nums.get(i)));
        }

        for (int i = n - 2; i >= 0; i--) {
            backfixMax.set(i, Math.max(nums.get(i), backfixMax.get(i + 1) + nums.get(i)));
        }

        
        int maxSum = Integer.MIN_VALUE;
        // forwardMax[i]: max subarray sum until i 
        List<Integer> forwardMax = new ArrayList(prefixMax);
        List<Integer> backwardMax = new ArrayList(backfixMax);
        for (int i = 1; i < n; i++) {
            forwardMax.set(i, Math.max(forwardMax.get(i), forwardMax.get(i - 1)));
        }
        for (int i = n - 2; i >= 0; i--) {
            backwardMax.set(i, Math.max(backwardMax.get(i), backwardMax.get(i + 1)));
        }

        for (int i = 0; i < n - 1; i++) {
            maxSum = Math.max(maxSum, forwardMax.get(i) + backwardMax.get(i + 1));
        }
        return maxSum;
    }
}
