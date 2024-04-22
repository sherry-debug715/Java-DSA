package Sort;
// Lintcode problem 31: https://www.lintcode.com/problem/31/
public class PartitionArr {
    public int partitionArray(int[] nums, int k) {
        // edge case 
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        int mid = 0;

        while (mid <= right) {
            if (nums[mid] < k) {
                swap(nums, left, mid);
                left += 1;
                mid += 1;
            } else if (nums[mid] > k) {
                swap(nums, right, mid);
                right -= 1;
            } else {
                mid += 1;
            }
        }
        return left;
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}
