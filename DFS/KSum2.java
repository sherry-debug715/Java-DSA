package DFS;

import java.util.ArrayList;
import java.util.List;

public class KSum2 {
    public List<List<Integer>> kSumII(int[] a, int k, int target) {
        ArrayList<List<Integer>> output = new ArrayList<>();
        // edge case 
        if (a == null || a.length == 0 || k == 0) {
            return output;
        }

        dfs(a, k, target, output, new ArrayList<Integer>(), 0);
        return output;
    }
    
    private void dfs(int[] a,
                     int k,
                     int target,
                     ArrayList<List<Integer>> output,
                     ArrayList<Integer> curSet,
                     int startIdx) {
        if (curSet.size() == k && sumUp(curSet) == target) {
            output.add(new ArrayList<Integer>(curSet));
            return;
        }

        if (curSet.size() > k) {
            return;
        }

        for (int i = startIdx; i < a.length; i++) {
            curSet.add(a[i]);
            dfs(a, k, target, output, curSet, i + 1);
            curSet.remove(curSet.size() - 1);
        }
    }

    private int sumUp(ArrayList<Integer> arr) {
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        return sum;
    }

}
