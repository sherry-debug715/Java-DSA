package DFS;

import java.util.ArrayList;
import java.util.List;

// lintcode 427
public class GenerateParentheses {
    List<String> output = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return output;
        }
        dfs(n, 0, 0, new StringBuilder());
        return output;
    }

    private void dfs(int n, int open, int close, StringBuilder curSet) {
        if (open == n && close == n) {
            output.add(curSet.toString());
            return;
        }

        if (close > open || open > n) {
            return;
        }

        if (open < n && open >= close) {
            curSet.append("(");
            dfs(n, open + 1, close, curSet);
            curSet.deleteCharAt(curSet.length() - 1);
        }
        if (close < open) {
            curSet.append(")");
            dfs(n, open, close + 1, curSet);
            curSet.deleteCharAt(curSet.length() - 1);
        }
    }
}
