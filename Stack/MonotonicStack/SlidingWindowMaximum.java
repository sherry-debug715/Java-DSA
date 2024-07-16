package Stack.MonotonicStack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// lintcode 362 
// Time: O(n)
// Space: O(k)
public class SlidingWindowMaximum {
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> output = new ArrayList<>();
        if (nums == null || nums.length == 0 || k == 0) {
            return output;
        }

        int n = nums.length;
        Deque<Integer> queue = new ArrayDeque<>();
        int l = 0, r = 0;

        while (r <= n) {
            while (r <= n && r - l < k) {
                while (r < n && !queue.isEmpty() && nums[r] > queue.peekLast()) {
                    queue.removeLast();
                }
                if (r < n) {
                    queue.addLast(nums[r]);
                }
                r += 1;
            }
            if (r <= n && r - l == k) {
                output.add(queue.peekFirst());
            }
            // move left pointer 
            if (l < n && nums[l] == queue.peekFirst()) {
                queue.removeFirst();
            }
            l += 1;
        }
        return output;
    }
}
