package Sort;
// leetcode 581: https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
public class ShortestUnsortedContinuousSubarray {
    int leftMost = Integer.MAX_VALUE;
    int rightMost = 0;
    public int findUnsortedSubarray(int[] nums) {
        leftMost = nums.length - 1;
        if (nums == null || nums.length == 1) {
           return 0;    
        } 
        mergeSort(0, nums.length - 1, nums);
        if (rightMost == 0) {
            return 0;
        }
       return rightMost - leftMost + 1;
    }
    
    private void mergeSort(int start, int end, int[] nums) {
        if (start >= end) {
            return;
        }
        
        int mid = start + (end - start) / 2;
        mergeSort(start, mid, nums);
        mergeSort(mid + 1, end, nums);
        merge(start, end, nums);
    }
    
    private void merge(int start, int end, int[] nums) {
        int[] temp = new int[nums.length];
        int left = start;
        int mid = start + (end - start) / 2;
        int right = mid + 1;
        int idx = start;
        
        while (left <= mid && right <= end) {
            if (nums[left] <= nums[right]) {
                temp[idx++] = nums[left++];
            } else {
                leftMost = Math.min(left, leftMost);
                rightMost = Math.max(right, rightMost);
                temp[idx++] = nums[right++];
            }
        }
        
        while (left <= mid) {
            temp[idx++] = nums[left++];
        }
        while (right <= end) {
            temp[idx++] = nums[right++];
        }
        
        for (int i = start; i <= end; i++) {
            nums[i] = temp[i];
        }
    }
}
