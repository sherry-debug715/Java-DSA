package HashMap.KDifference;

import java.util.Arrays;
// Time: O(NlogN)
// Space: O(1)
public class TwoPointer {
    public int kDifference(int[] nums, int target) {
        Arrays.sort(nums);
        int counter = 0;
        int left = 0;
        int right = 1;

        while (right < nums.length) {
            int diff = nums[right] - nums[left];
            if (diff == Math.abs(target)) {
                counter += 1;
                left += 1;
                right += 1;
            } else if (diff < target) {
                right += 1;
            } else {
                left += 1;
                right = left + 1;
            }
        }
        return counter;
    }
}
