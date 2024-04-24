package TwoPointers;

import java.util.Arrays;
// Time: O(NlogN)
// Space: O(1)
public class TwoSumGreaterThanTarget {
    public int twoSum2(int[] nums, int target) {
        // edge case 
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int output = 0;
        // O(nlogn)
        Arrays.sort(nums);
        // right should be declared here instead of inside the while loop
        //            l
        // nums = [2, 7, 11, 15]. target = 10
        //                    r
        // nums[l] + nums[r] > target, so [2, 15], [7, 15], [11, 15] are all greater than 10.
        // output += 3; when l == 1, if right is started at the end again, [7, 15] and [11, 15]
        // are recalculated into output again.
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int twoSum = nums[left] + nums[right];
            // Sine nums is sorted, if twoSum > target, all numbers between left and right, 
            // inclusive, when paired with right, their sum will be greater than target.
            if (twoSum > target) {
                output += (right - left);
                right -= 1;
            } else {
                left += 1;
            }
        }
        return output;
    }
}
