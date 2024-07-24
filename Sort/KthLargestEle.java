package Sort;
// lintcode 5
// Time: average O(N) worst O(N^2) 
// Space: O(logN) for stack space
public class KthLargestEle {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length){
            return -1;
        }
        return quickSelect(0, nums.length - 1, nums.length - k, nums);
    }
    
    private int quickSelect(int start, int end, int k, int[] nums) {
        if (start >= end) {
            return nums[k];
        }
        
        int pivot = nums[start + (end - start) / 2];
        int left = start, right = end;
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left += 1;
            }
            while (left <= right && nums[right] > pivot) {
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
        
        if (k <= right) {
            return quickSelect(start, right, k, nums);
        }
        if (k >= left) {
            return quickSelect(left, end, k, nums);
        }
        return nums[k];
    }
}
