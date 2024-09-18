package Array;
// Lintcode 45
// Time: O(N)
// Space: O(N)
public class MaximumSubarrayDifference {
        /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two substrings
     */
    class PrefixMaxMin {
        int[] prefixMax;
        int[] prefixMin;
        public PrefixMaxMin(int[] _prefixMax, int[] _prefixMin) {
            prefixMax = _prefixMax;
            prefixMin = _prefixMin;
        }
    }

    class SuffixMaxMin {
        int[] suffixMax;
        int[] suffixMin;
        public SuffixMaxMin(int[] _suffixMax, int[] _suffixMin) {
            suffixMax = _suffixMax;
            suffixMin = _suffixMin;
        }
    }

    public int maxDiffSubArrays(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int n = nums.length;
        PrefixMaxMin prefix = getPrefixMaxMin(nums, n);
        SuffixMaxMin sufix = getSuffixMaxMin(nums, n);
        // prefixMax[i] represents the max subarray sum of nums[:i + 1]
        int[] prefixMax = prefix.prefixMax;
        // prefixMin[i] represents the min subarray sum of nums[:i + 1]
        int[] prefixMin = prefix.prefixMin;
        // suffixMax[i] represents the max subarray sum of nums[i + 1 : n]
        int[] suffixMax = sufix.suffixMax;
        // suffixMin[i] represents the min subarray sum of nums[i + 1: n]
        int[] suffixMin = sufix.suffixMin; 


        int res = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, Math.abs(prefixMax[i] - suffixMin[i - 1]));
            res = Math.max(res, Math.abs(prefixMin[i] - suffixMax[i - 1]));
        }

        return res;
    }

    private PrefixMaxMin getPrefixMaxMin(int[] nums, int n) {
        int[] prefixMax = new int[n];
        int[] prefixMin = new int[n];

        // populate prefixMax 
        int sum = 0, minPrefixSum = 0, maxPrefixSum = nums[0];
        for (int i = 1; i < n; i++) {
            sum += nums[i - 1];
            maxPrefixSum = Math.max(maxPrefixSum, sum - minPrefixSum);
            prefixMax[i] = maxPrefixSum;
            minPrefixSum = Math.min(minPrefixSum, sum);
        }

        // populate prefixMin 
        sum = 0; minPrefixSum = nums[0]; maxPrefixSum = 0; 
        for (int i = 1; i < n; i++) {
            sum += nums[i - 1];
            minPrefixSum = Math.min(minPrefixSum, sum - maxPrefixSum);
            prefixMin[i] = minPrefixSum;
            maxPrefixSum = Math.max(maxPrefixSum, sum);
        }

        return new PrefixMaxMin(prefixMax, prefixMin);
    }

    private SuffixMaxMin getSuffixMaxMin(int[] nums, int n) {
        int[] suffixMax = new int[n];
        int[] suffixMin = new int[n];

        // populate suffixMax 
        int sum = 0, minSuffixSum = 0, maxSuffixSum = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sum += nums[i + 1];
            maxSuffixSum = Math.max(maxSuffixSum, sum - minSuffixSum);
            suffixMax[i] = maxSuffixSum;
            minSuffixSum = Math.min(minSuffixSum, sum);
        }

        // populate suffixMin 
        sum = 0; minSuffixSum = nums[n - 1]; maxSuffixSum = 0;
        for (int i = n - 2; i >= 0; i--) {
            sum += nums[i + 1];
            minSuffixSum = Math.min(minSuffixSum, sum - maxSuffixSum);
            suffixMin[i] = minSuffixSum;
            maxSuffixSum = Math.max(maxSuffixSum, sum);
        }

        return new SuffixMaxMin(suffixMax, suffixMin);
    }
}
