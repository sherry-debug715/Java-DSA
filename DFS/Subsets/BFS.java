package DFS.Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Deque;
import java.util.ArrayDeque;

public class BFS {
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     *          we will sort your return value in output
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // edge case 
        if (nums == null || nums.length == 0) {
            res.add(new ArrayList<Integer>());
            return res;
        }

        Deque<List<Integer>> queue = new ArrayDeque<>();
        queue.offer(new ArrayList<Integer>()); // empty list is also a valid subset 

        // sort the input array to handle duplicate numbers 
        Arrays.sort(nums);

        while (!queue.isEmpty()) {
            List<Integer> curSet = queue.removeFirst();
            // add current set to res 
            res.add(curSet);
            for (int i = 0; i < nums.length; i++) {
                // handle duplicate 
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                // only add number that's greater than the last number from curSet 
                if (!curSet.isEmpty() && nums[i] <= curSet.get(curSet.size() - 1)) {
                    continue;
                }
                List<Integer> newSet = new ArrayList<>(curSet);
                newSet.add(nums[i]);
                queue.offer(newSet);
            }
        } 
        return res;
    }
}
