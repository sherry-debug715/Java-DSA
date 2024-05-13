package TwoPointers;
// Time: O(N)
// Space: O(N)
public class SlidingWindowMaximum {
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> output = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return output;
        }
        // double-ended queue 
        Deque<Integer> deque = new ArrayDeque<>();
        // populate queue with the first k - 1 th number 
        for (int i = 0; i < k - 1; i++) {
            inQueue(deque, nums, i);
        }

        // start iterating from the kth element 
        for (int i = k - 1; i < nums.length; i++) {
            inQueue(deque, nums, i);
            output.add(nums[deque.peekFirst()]);
            deQueue(deque, i - k + 1);
        }

        return output;
    }

    private void inQueue(Deque<Integer> deque, int[] nums, int i) {
        while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
            deque.pollLast();
        }
        deque.offerLast(i);
    }

    private void deQueue(Deque<Integer> deque, int i) {
        if (deque.peekFirst() == i) {
            deque.pollFirst();
        }
    }
}
