package Sort;
// Time: average O(N) worst O(N^2) 
// Space: O(logN) for stack space
public class KthLargestEle {
    public int kthLargestElement(int k, int[] nums) {
        // edge case 
        if (k > nums.length || nums.length == 0 || nums == null) {
            return -1;
        }

        return quickSelect(k, 0, nums.length - 1, nums);
    }

    private int quickSelect(int k, int start, int end, int[] nums) {
        if (start >= end) {
            return nums[start];
        }

        int left = start;
        int right = end;
        int mid = nums[start + (end - start) / 2];

        // sort numbers > mid to the left, nums < mid to the right 
        while (left <= right) {
            while (left <= right && nums[left] > mid) {
                left += 1;
            }
            while (left <= right && nums[right] < mid) {
                right -= 1;
            }
            if (left <= right) {
                // swap 
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left += 1;
                right -= 1;
            }
        }

        // check if k is on the left or right portion 
        if (start + k - 1 <= right) {
            return quickSelect(k, start, right, nums);
        }
        if (start + k - 1 >= left) {
            return quickSelect(k - (right - start + 1), left, end, nums);
        } 
        return nums[right + 1];
    }
}
