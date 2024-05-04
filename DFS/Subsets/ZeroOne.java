package DFS.Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZeroOne {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        if (nums == null) {
            return output;
        }
        Arrays.sort(nums);
        dfs(0, nums, output, new ArrayList<Integer>());
        return output;
    }

    // 0, 1 
    private void dfs(int idx, 
                     int[] nums,
                     List<List<Integer>> output,
                     ArrayList<Integer> curSet) {
        // exit 
        if (idx == nums.length) {
            output.add(new ArrayList<>(curSet));
            return;
        }

        curSet.add(nums[idx]);
        dfs(idx + 1, nums, output, curSet);
        curSet.remove(curSet.size() - 1);
        dfs(idx + 1, nums, output, curSet);
    }
}
