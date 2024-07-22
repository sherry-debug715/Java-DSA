package Stack.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;

// lintcode 621 
// Time: O(N)
// Space: O(N)
public class MaximumSubarrV {
    public int maxSubarray5(int[] nums, int k1, int k2) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        int res = Integer.MIN_VALUE;

        for (int i = 1; i < n + 1; i++) {
            // populate prefixSum 
            prefixSum[i] = nums[i - 1] + prefixSum[i - 1];
            // make sure window size is valid 
            while (!queue.isEmpty() && i - k2 > queue.peekFirst()) {
                queue.removeFirst();
            }

            if (i >= k1) {
                while (!queue.isEmpty() && prefixSum[i - k1] < prefixSum[queue.peekLast()]) {
                    queue.removeLast();
                }
                queue.addLast(i - k1); 
            }

            if (!queue.isEmpty()) {
                res = Math.max(res, prefixSum[i] - prefixSum[queue.peekFirst()]);
            }
        }
        return res;
    } 
}
