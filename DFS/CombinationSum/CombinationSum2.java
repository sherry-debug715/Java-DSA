package DFS.CombinationSum;

import java.util.Arrays;
import java.util.List;

// Lintcode 153
public class CombinationSum2 {
        List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        // edge case 
        if (num == null || num.length == 0) {
            return res;
        }
        // sort num array to handle duplicates numbers and dfs prunning 
        Arrays.sort(num);
        dfs(num, target, 0, 0, new ArrayList<Integer>());
        return res;
    }

    private void dfs(int[] num, int target, int idx, int curSum, List<Integer> curSet) {
        if (curSum > target) {
            return;
        }

        if (curSum == target) {
            res.add(new ArrayList<>(curSet));
            return;
        }

        for (int i = idx; i < num.length; i++) {
            // prunning, num is sorted, if current number is greater than target, stop 
            if (num[i] > target) {
                return;
            }
            // handle duplicates
            if (i > idx && num[i] == num[i - 1]) {
                continue;
            }
            curSet.add(num[i]);
            dfs(num, target, i + 1, curSum + num[i], curSet);
            curSet.remove(curSet.size() - 1);
        }
    }
}
