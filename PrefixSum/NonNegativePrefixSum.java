package PrefixSum;

public class NonNegativePrefixSum {
    public int getPrefixSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // prefixSum is for array nums = [-3,5,1,2,-3,5,1]
        int[] prefixSum = new int[2 * n];
        for (int i = 1; i < 2 * n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[(i - 1) % n];
        }
        Deque<Integer> queue = new ArrayDeque<>();
        int ans = 0;
        for (int i = -n + 1, j = 0; j < 2 * n; j++, i++) {
            // smallest number from queue is expired 
            if (i > 0 && queue.peekFirst() == prefixSum[i - 1]) {
                queue.pollFirst();
            }
            while (i > 0 && queue.peekLast() > prefixSum[j]) {
                queue.pollLast();
            }
            queue.addLast(prefixSum[j]);
            // if smallest prefix - the rotated out number >= 0, we have an answer
            if (i > 0 && queue.peekFirst() - prefixSum[i - 1] >= 0) {
                ans += 1;
            }
        }
        return ans;

    }
}
