package DFS;

import java.util.ArrayList;
import java.util.List;

// lintcode 427
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> output = new ArrayList<>();
        dfs(n, 0, 0, new ArrayList<String>(), output);
        return output;
    }

    private void dfs(int n, int open, int close, List<String> curSet, List<String> output) {
        if (close > open) {
            return;
        }
        if (open > n) {
            return;
        }
        if (open == n && close == n) {
            output.add(String.join("", curSet));
            return;
        }

        if (open < n && open >= close) {
            curSet.add("(");
            dfs(n, open + 1, close, curSet, output);
            curSet.remove(curSet.size() - 1);
        }
        if (close < open) {
            curSet.add(")");
            dfs(n, open, close + 1, curSet, output);
            curSet.remove(curSet.size() - 1);
        }
    }
}
