package TwoPointers;

import java.util.List;

// lintcode 1879 
// Time: O(N)
// Space: This list will contain at most n/2 pairs of indices (each pair being of size 2). Hence, its space complexity is O(n).
public class TwoSum6 {
    public List<List<Integer>> twoSumVII(int[] nums, int target) {
         List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        // Initialize left to min value index and right to max value index
        int n = nums.length;
        int left = 0, right = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[left]) {
                left = i;
            }
            if (nums[i] > nums[right]) {
                right = i;
            }
        } 

        while (nums[left] < nums[right]) {
            int twoSum = nums[left] + nums[right];
            if (twoSum < target) {
                left = findNextLeft(left, nums);
                if (left == -1) {
                    break;
                }
            } else if (twoSum > target) {
                right = findNextRight(right, nums);
                if (right == -1) {
                    break;
                }
            } else {
                List<Integer> tmp = new ArrayList<>();
                if (left < right) {
                    tmp.add(left);
                    tmp.add(right);
                } else {
                    tmp.add(right);
                    tmp.add(left);
                }
                res.add(tmp);
                left = findNextLeft(left, nums);
                if (left == -1) {
                    break;
                }
            }
        }

        return res;
    }


    private int findNextLeft(int left, int[] nums) {
        int n = nums.length;
        if (nums[left] < 0) {
            // look for the first negative number to the left.
            // negative number on the left side of left is greater than nums[left]
            for (int i = left - 1; i >= 0; i--) {
                if (nums[i] < 0) {
                    return i;
                }
            } 
             // If no negative number is found, start from the beginning and look for the first non-negative number
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] >= 0) {
                    return i;
                }
            }
            return -1;
        } 
        // If nums[left] is non-negative, look for the next non-negative number to the right
        for (int i = left + 1; i < nums.length; i++) {
            if (nums[i] >= 0) {
                return i;
            }
        }
        return -1;
    }

    private int findNextRight(int right, int[] nums) {
        int n = nums.length;
        if (nums[right] > 0) {
            // look for the next positive num to the left, this number is less than nums[right].
            for (int i = right - 1; i >= 0; i--) {
                if (nums[i] > 0) {
                    return i;
                }
            }
            // if no positive number < nums[right] exist on the left side, look for the 
            // largest negative number which should be the first negative number on the left side 
            for (int i = 0; i < n; i++) {
                if (nums[i] <= 0) {
                    return i;
                }
            }
            return -1;
        }
        // if nums[right] is negative or zero, the next number smaller than itself should be on the right side.
        for (int i = right + 1; i < n; i++) {
            if (nums[i] <= 0) {
                return i;
            }
        }
        return -1;
    }
}
