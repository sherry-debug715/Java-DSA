package Sort;

import java.util.Arrays;

public class KthLargestNumbers {
    public int[] topk(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        quickSelect(0, nums.length - 1, nums, k);
        return Arrays.copyOfRange(nums, 0, k);
    }

    private void quickSelect(int start, int end, int[] nums, int k) {
        if (start >= k) {
            return;
        }
        if (start >= end) {
            return;
        }

        int mid = nums[start + (end - start) / 2];
        int left = start, right = end;
        while (left <= right) {
            while (left <= right && nums[left] > mid) {
                left += 1;
            }
            while (left <= right && nums[right] < mid) {
                right -= 1;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left += 1;
                right -= 1;
            }
        } 

        quickSelect(start, right, nums, k);
        quickSelect(left, end, nums, k);
    }
}
