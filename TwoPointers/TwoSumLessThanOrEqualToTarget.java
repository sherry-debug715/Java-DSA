package TwoPointers;

import java.util.Arrays;

public class TwoSumLessThanOrEqualToTarget {
    public int twoSum5(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        Arrays.sort(nums);
        int output = 0;
        int left = 0, right = nums.length - 1;
        // while left and right don't overlap 
        while (left < right) {
            int twoSum = nums[left] + nums[right];
            if (twoSum <= target) {
                // it means any numbers between left and right, when pairs with left
                // their sum is <= 24 
                output += (right - left);
                left += 1; 
            } else {
                right -= 1;
            }
        }
        return output;
    }
}
