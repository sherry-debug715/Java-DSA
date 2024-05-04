package DFS.Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        if (nums == null) {
            return output;
        }
        Arrays.sort(nums);
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(new ArrayList<>());
        while (!queue.isEmpty()) {
            List<Integer> curSet = queue.poll();
            output.add(curSet);
            for (int num : nums) {
                if (!curSet.isEmpty() && curSet.get(curSet.size() - 1) >= num) {
                    continue;
                }
                List<Integer> tempSet = new ArrayList<>(curSet);
                tempSet.add(num);
                queue.offer(tempSet);
            }
        }
        return output;
    }
}
