package BinarySearch;
// Time: O(logN)
// Space: O(1)
public class LastPosition {
    public int lastPosition(int[] nums, int target) {
        // edge case 
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // binary search 
        int left = 0, right = nums.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }

        // first check right pointer 
        if (nums[right] == target) {
            return right;
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }
}
