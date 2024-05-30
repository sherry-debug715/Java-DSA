package BinarySearch;
// lintcode 617
// Time: O(N * log((maxNum - minNum) /0.00001))
// Space: O(1)
public class MaximumAverageSubarr2 {
    public double maxAverage(int[] nums, int k) {
        // find the min val and max val from nums, use their ave as mid 
        double minVal = nums[0], maxVal = nums[0];
        for (int n : nums) {
            minVal = Math.min(minVal, n);
            maxVal = Math.max(maxVal, n);
        }
        // find the max ave >= mid 
        while (minVal + 1e-5 < maxVal) {
            double mid = (minVal + maxVal) / 2;
            if (isGreater(nums, k, mid)) {
                minVal = mid;
            } else {
                maxVal = mid;
            }
        }
        return minVal;
    }

    private boolean isGreater(int[] nums, int k, double mid) {
        double rightSum = 0, leftSum = 0, minLeftSum = 0;
        for (int i = 0; i < k; i++) {
            rightSum += (nums[i] - mid);
        }

        for (int i = k; i <= nums.length; i++) {
            if (rightSum - minLeftSum >= 0) {
                return true;
            }
            if (i < nums.length) {
                rightSum += (nums[i] - mid);
                leftSum += (nums[i - k] - mid);
                minLeftSum = Math.min(minLeftSum, leftSum);
            }
        }
        return false;
    }
}
