package TwoPointers;
// Time: O(nlogn)
// Space: O(1)
public class KthSmallestNumInUnsortedArr {
    public int kthSmallest(int k, int[] nums) {
        // edge case 
        if (nums == null || nums.length == 0 || k > nums.length) {
            return 0;
        }
        // k - 1 because array index starts from 0.
        return quickSelect(k - 1, nums, 0, nums.length - 1);
    }

    private int quickSelect(int k, int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        int left = start, right = end;
        // pivot need to be declared outside the while loop, and should be the actual
        // number instead of array index 
        int pivot = nums[left + (right - left) / 2];
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left += 1;
            }
            while (left <= right && nums[right] > pivot) {
                right -= 1;
            }
            // swap only if left <= right
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left += 1;
                right -= 1;
            } 
        }

        // check which side is k is located on, if following situation occure, we can 
        // just return nums[k]
        //        s
        //              l
        // [1, 2, 3, 4, 5],
        //     r 
        //              e
        if (right >= start && k <= right) {
            return quickSelect(k, nums, start, right);
        } else if (left <= end && k >= left) {
            return quickSelect(k, nums, left, end);
        } else {
            return nums[k];
        }
    } 
}
