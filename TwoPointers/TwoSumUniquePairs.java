package TwoPointers;

import java.util.Arrays;

public class TwoSumUniquePairs {
    public int twoSum6(int[] nums, int target) {
        // edge case 
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int output = 0;
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int twoSum = nums[left] + nums[right];
            if (twoSum == target) {
                output += 1;
                left += 1;
                right -= 1;
                while (left < right && nums[left] == nums[left - 1]) {
                    left += 1;
                } 
                while (left < right && nums[right] == nums[right + 1]) {
                    right -= 1;
                }
            } else if (twoSum < target) {
                left += 1;
            } else {
                right -= 1;
            }
        }

        return output;
    }
}
