package Stack.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;

// lintcode 1507 
// Time: O(N)
// Space: O(N)
public class ShortestSubarrWithSumAtLeastK {
       public int shortestSubarray(int[] a, int k) {
        if (a == null || a.length == 0) {
            return -1;
        }

        // compute prefix sum 
        int n = a.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + a[i - 1];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int shortLen = Integer.MAX_VALUE;
        for (int i = 0; i < n + 1; i++) {
            // add to the end 
            while (!deque.isEmpty() && prefixSum[i] < prefixSum[deque.peekLast()]) {
                deque.removeLast();
            }
            // remove from head if sum >= k
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                shortLen = Math.min(shortLen, i - deque.removeFirst());
            }
            deque.addLast(i);
        }

        return shortLen == Integer.MAX_VALUE ? -1 : shortLen;
    }
}
