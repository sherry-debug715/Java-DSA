package TwoPointers;

import java.util.Arrays;

// Lintcode problem 382: https://www.lintcode.com/problem/382/?fromId=161&_from=collection
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
