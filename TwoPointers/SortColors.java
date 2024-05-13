package TwoPointers;
// Lintcode problem 148: https://www.lintcode.com/course/101/learn/148?chapterId=534&sectionId=3980&ac=true

public class SortColors {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int left = 0, mid = 0, right = nums.length - 1;
        while (mid <= right) {
            if (nums[mid] == 0) {
                swap(nums, mid, left);
                left += 1;
                mid += 1;
            } else if (nums[mid] == 2) {
                swap(nums, mid, right);
                right -= 1;
                // don't increment mid because we don't know the number swapped 
                // from right;
            } else {
                mid += 1;
            }
        }
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}
