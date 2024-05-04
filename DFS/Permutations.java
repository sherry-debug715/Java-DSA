package DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        if (nums == null) {
            return output;
        }
        dfs(nums, output, new ArrayList<Integer>(), new HashSet<Integer>());
        return output;
    }

    private void dfs(int[] nums,
                     List<List<Integer>> output,
                     List<Integer> curSet,
                     HashSet<Integer> visited) {
        // find a permutation 
        if (curSet.size() == nums.length) {
            output.add(new ArrayList<>(curSet));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited.contains(nums[i])) {
                continue;
            }
            curSet.add(nums[i]);
            visited.add(nums[i]);
            dfs(nums, output, curSet, visited);
            curSet.remove(curSet.size() - 1);
            visited.remove(nums[i]);
        }
    }
}
