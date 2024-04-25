package BinarySearch;

public class MaxNumberInMountainSequence {
        // Time: O(logN)
    // Space: O(1)
    public int mountainSequence(int[] nums) {
        // edge case 
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // mid is the peak 
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return nums[mid];
            }
            // mid is on uphill 
            if (nums[mid] < nums[mid + 1]) {
                left = mid;
            } else if (nums[mid] > nums[mid + 1]) {
                // mid is on downhill
                right = mid;
            }
        }

        return Math.max(nums[left], nums[right]);
    }
}
