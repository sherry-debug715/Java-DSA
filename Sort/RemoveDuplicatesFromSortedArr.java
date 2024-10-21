package Sort;
// Lintcode 100 
// Time: O(n)
// Space: O(1)
public class RemoveDuplicatesFromSortedArr {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 1, right = 1;
        int n = nums.length;
        // right is always to look for the first num > nums[l]
        while (right < n) {
            while (right < n && nums[right] <= nums[left - 1]) {
                right += 1;
            }
            if (right < n) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left += 1;
            }
        }
        return left;
    }
}
