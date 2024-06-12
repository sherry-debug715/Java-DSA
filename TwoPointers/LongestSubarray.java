package TwoPointers;

import java.util.ArrayDeque;
import java.util.Deque;
// Lintcode 3493 
// Time: O(N)
// Space: O(N)
public class LongestSubarray {
    public int longestSubarray(int[] nums, int d, int p) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // prefix sum for O(1) compute on sum of subarray 
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i < prefixSum.length; i++) { // O(N)
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        // dSum[i] is the sum of subarray dSum[i:i + d] 
        int[] dSum = new int[n - d + 1];
        for (int i = 0; i < dSum.length; i++) { // O(N)
            dSum[i] = prefixSum[i + d] - prefixSum[i];
        }
        // l maintains the left edge of a valid subarray, r is for explore 
        // ans == d is valid because changing d substring to 0 will be the shortest valid substring.
        int l = 0, r = d, ans = d;
        // leftDSum keep track all valid d sum in the current subarray 
        Deque<Integer> leftDSum = new ArrayDeque<>();
        Deque<Integer> maxDSum = new ArrayDeque<>(); // peekFirst returns the max value of d sum in the current subarray 
        leftDSum.addFirst(dSum[0]);
        maxDSum.addFirst(dSum[0]);
        while (r < n) { // stop at the last second index of prefixSum 
            // the current 2DSum 
            int curDSum = dSum[r - d + 1];
            // maintain the largest 2d sum in current subarray in the first position
            while (!maxDSum.isEmpty() && curDSum > maxDSum.peekLast()) {
                maxDSum.removeLast();
            }
            leftDSum.addLast(curDSum);
            maxDSum.addLast(curDSum);
            // compute current sum of subarray 
            int subArrSum = prefixSum[r + 1] - prefixSum[l];
            if (subArrSum - maxDSum.peekFirst() <= p) {
                ans = Math.max(ans, r - l + 1);
            } else {
                // increment left pointer 
                l += 1;
                // discard expired 2d SUM from both deque 
                if (leftDSum.peekFirst().equals(maxDSum.peekFirst())) {
                    maxDSum.removeFirst();
                }
                leftDSum.removeFirst();
            }
            r += 1;
        }
        return ans;
    }
}
