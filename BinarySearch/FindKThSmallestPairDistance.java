package BinarySearch;

import java.util.Arrays;

// Leetcode 719
public class FindKThSmallestPairDistance {
    // Time: O(NlogN) + O(NlogD) D is the difference between maxNum and minNum from nums array
    // Space: O(1)
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums); // sort nums to better calculate the distance 
        int n = nums.length;
        int left = 0, right = nums[n - 1]; // left == 0 because the smallest distance between pairs is 0 
        while (left + 1 < right) {
            int mid = left + (right -  left) / 2;
            if (countPairs(nums, mid) < k) {
                left = mid;
            } else {
                right = mid; // even if it equals to k we want to keep exploring left 
            }
        }

        if (countPairs(nums, left) >= k) {
            return left;
        } 
        return right;
    }

    private int countPairs(int[] nums, int distance) {
        int counter = 0; // count the total pairs with distance <= distance 
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && nums[j] - nums[i] <= distance) {
                j += 1;
            }
            counter += j - i - 1; // nums[i: j)
        }
        return counter;
    }
}
