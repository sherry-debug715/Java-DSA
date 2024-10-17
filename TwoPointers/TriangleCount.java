package TwoPointers;

import java.util.Arrays;

// Lintcode problem 382: https://www.lintcode.com/problem/382/?fromId=161&_from=collection
// Two Solution below 
public class SolutionOne {
    // Time: O(nlogn)
    // Space: O(1)
    public int triangleCount(int[] s) {
        // edge case 
        if (s == null || s.length == 0) {
            return 0;
        }
        // sort array so when s[left] + s[mid] > s[right], we know all number from mid to right could form valid triangle
        Arrays.sort(s); // O(n log n)
        int output = 0;
        int n = s.length;
        int left = 0, right = n - 1;
        while (left < n - 2) { // O(nlogn)
            int idx = findTarget(s[right] - s[left], s, left + 1, right - 1); // find the smallest number > s[right] - s[left] // O(logN)
            if (idx != -1) {
                output += right - idx;
            }
            right -= 1;
            if (left + 1 == right) {
                left += 1;
                right = n - 1;
            }
        }
        return output;
    }

    private int findTarget(int target, int[] s, int left, int right) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (s[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (s[left] > target) {
            return left;
        }
        if (s[right] > target) {
            return right;
        }
        return -1;
    }
}
// Time: O(N^2)
// Space: O(1)
public class TriangleCount {
    public int triangleCount(int[] s) {
        // edge case 
        if (s == null || s.length == 0) {
            return 0;
        }
        Arrays.sort(s);
        int output = 0;
        // 枚举右边界
        for (int r = 2; r < s.length; r++) {
            int left = 0, mid = r - 1;
            // break when left and mid overlap
            while (left < mid) {
                if (s[left] + s[mid] > s[r]) {
                    // since array is sorted, any number between left and mid, 
                    // inclusive, when paired with mid should be greater than s[r]
                    output += (mid - left);
                    // decrement mid to look for other pairs 
                    mid -= 1;
                } else {
                    left += 1;
                }
            }
        }
        return output;
    }
}
