package DFS;

import java.util.ArrayList;
import java.util.List;

// Lintcode 652
// Time: It is closer to O(2^d), where d is the number of divisors of n, considering that for each divisor, a recursive call is made, leading to an exponential growth in the number of combinations to check.
public class Factorization {
    List<Integer> curFactor = new ArrayList<>();
    List<List<Integer>> output = new ArrayList<>();

    public List<List<Integer>> getFactors(int n) {
        dfs(2, n);
        return output;
    }

    private void dfs(int start, int remain) {
        if (remain == 1) {
            if (curFactor.size() != 1) {
                output.add(new ArrayList<>(curFactor));
            }
            return;
        }

        for (int i = start; i <= remain; i++) {
            if (i > remain / i) {
                break;
            }
            if (remain % i == 0) {
                curFactor.add(i);
                dfs(i, remain / i);
                curFactor.remove(curFactor.size() - 1);
            }
        }

        curFactor.add(remain);
        dfs(remain, 1);
        curFactor.remove(curFactor.size() - 1);
    }
}
