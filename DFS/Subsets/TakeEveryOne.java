package DFS.Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TakeEveryOne {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        if (nums == null) {
            return output;
        }
        Arrays.sort(nums);
        dfs(0, nums, output, new ArrayList<Integer>());
        return output;
    }

    private void dfs(int startIdx,
                     int[] nums,
                     List<List<Integer>> output,
                     ArrayList<Integer> curSet) {

        output.add(new ArrayList<>(curSet));

        for (int i = startIdx; i < nums.length; i++) {
            curSet.add(nums[i]);
            dfs(i + 1, nums, output, curSet);
            curSet.remove(curSet.size() - 1);
        }
    }
}
