package TwoPointers;

public class LongestSubarray {
    public int longestSubarray(int[] nums, int d, int p) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int numSize = nums.length;
        // build prefix sum 
        int[] prefixSum = new int[numSize + 1];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        // System.out.println(Arrays.toString(prefixSum));
        // dSum[i] is the sum of d sized subarray of nums 
        int[] dSum = new int[numSize - d + 1];
        for (int i = 0; i < dSum.length; i++) {
            dSum[i] = prefixSum[i + d] - prefixSum[i];
        }
        // System.out.println(Arrays.toString(dSum));

        Deque<Integer> queue = new ArrayDeque<>();
        Deque<Integer> maxQueue = new ArrayDeque<>();
        queue.add(dSum[0]);
        maxQueue.add(dSum[0]);
        int left = 0, right = d;
        // change d numbers to 0, 0 <= p, therefore ans == d is valid.
        int ans = d;

        while (right <= numSize - 1) {
            // update queue and maxQueue 
            while (!maxQueue.isEmpty() && dSum[right - d + 1] > maxQueue.peekLast()) {
                maxQueue.pollLast();
            }
            maxQueue.add(dSum[right - d + 1]);
            queue.add(dSum[right - d + 1]);

            int sumLeftRight = prefixSum[right + 1] - prefixSum[left];
            // check if turning the maxDSubarray to 0 <= p 
            if (sumLeftRight - maxQueue.peekFirst() <= p) {
                ans = Math.max(ans, right - left + 1);
            } else {
                left += 1;
                // update queue 
                if (maxQueue.peekFirst().equals(queue.peekFirst())) {
                    maxQueue.pollFirst();
                }
                queue.pollFirst();
            }
            right += 1;
        }

        return ans;
    }
}
