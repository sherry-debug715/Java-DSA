package TwoPointers;
// Time: O(N)
// Space: O(1)
public class TwoSum2InputArrSorted {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int left = 0, right = nums.length - 1;
        // stops when left and right overlap 
        while (left < right) {
            int twoSum = nums[left] + nums[right];
            if (twoSum == target) {
                return new int[] {left + 1, right + 1};
            }
            if (twoSum < target) {
                left += 1;
            } else {
                right -= 1;
            }
        }
        return new int[] {-1, -1};
    }
}
