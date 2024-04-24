package TwoPointers;

import java.util.Arrays;
// Time: O(NlogN)
// Space: O(1)
public class TwoSumClosestToTarget {
    public int twoSumClosest(int[] nums, int target) {
        // edge case 
        if (nums == null || nums.length < 2) {
            return -1;
        }
        Arrays.sort(nums);
        // if nums is filled with the same value 
        if (nums[0] == nums[nums.length - 1]) {
            return Math.abs(target - (2 * nums[0]));
        }

        int min_diff = Integer.MAX_VALUE;
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int twoSum = nums[left] + nums[right];
            if (twoSum == target) {
                return 0;
            }
            // get the difference 
            int diff = Math.abs(target - twoSum);
            min_diff = Math.min(min_diff, diff);
            if (twoSum < target) {
                left += 1;
                // take care of dups 
                while (left < right && nums[left] == nums[left - 1]) {
                    left += 1;
                }
            } else {
                right -= 1;
                while (left < right && nums[right] == nums[right + 1]) {
                    right -= 1;
                }
            }
        }

        return min_diff;
    }
}
