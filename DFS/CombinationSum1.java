package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum1 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // sort the candidates so when sum of curSet + candidates[i] > tareget
        // return function because there is no need to explore candidates[i + 1:]
        Arrays.sort(candidates);
        List<List<Integer>> output = new ArrayList<>();
        // edge case 
        if (candidates == null || candidates.length == 0) {
            return output;
        }

        dfs(0, 0, candidates, target, new ArrayList<Integer>(), output);
        return output;
    }

    private void dfs(int startIdx,
                     int currentSum,
                     int[] candidates,
                     int target,
                     List<Integer> curSet,
                     List<List<Integer>> output) {
        if (currentSum == target) {
            output.add(new ArrayList<>(curSet));
            return;
        }

        for (int i = startIdx; i < candidates.length; i++) {
            // candidates[i - 1] + candidates[i] is already computed when i == 
            // startIdx.
            if (i > startIdx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (currentSum > target) {
                break;
            }
            curSet.add(candidates[i]);
            currentSum += candidates[i];
            dfs(i, currentSum, candidates, target, curSet, output);
            // after dfs return, backtracking 
            curSet.remove(curSet.size() - 1);
            currentSum -= candidates[i];
        }
    }
}
