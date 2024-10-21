package Sort;
// Leetcode 280
// Time: O(n)
// Space: O(1)
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int n = nums.length;
        int left = 0, right = 1;
        while (left < n && right < n) {
            if (left % 2 == 0 && right % 2 != 0) {
                if (nums[left] > nums[right]) {
                    swap(left, right, nums);
                }
                left += 1;
                right += 1;
            } else if (left % 2 != 0 && right % 2 == 0) {
                if (nums[left] < nums[right]) {
                    swap(left, right, nums);
                }
                left += 1;
                right += 1;
            }
        }
    }

    private void swap (int left, int right, int[] nums) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
