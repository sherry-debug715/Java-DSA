package DFS;

import java.util.List;

// Lintcode 780
// Time: O(n * 2^n)
// Space: O(n) at worst case, the stack space is equal to the length of s.
public class RemoveInvalidParentheses {
        char[][] patterns = { {'(', ')'}, {')', '('} };
    List<String> res = new ArrayList<>();
    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() == 0) {
            res.add("");
            return res;
        }

        dfs(s, 0, 0, patterns[0]);
        return res;
    }

    private void dfs(String s, int startIdx, int lastRemovedIdx, char[] pattern) {
        int open = 0, n = s.length();

        for (int i = startIdx; i < n; i++) {
            char c = s.charAt(i);
            if (c == pattern[0]) {
                open += 1;
            } 
            if (c == pattern[1]) {
                open -= 1;
            }
            if (open < 0) {
                // if close is greater than open, remove any ')' from lastRemovedIdx to i
                for (int j = lastRemovedIdx; j <= i; j++) {
                    if (s.charAt(j) == pattern[1] && (j == lastRemovedIdx || s.charAt(j) != s.charAt(j - 1))) {
                        dfs(s.substring(0, j) + s.substring(j + 1), i, j, pattern);
                    }
                }
                return;
            } 
        }

        // if i is at the end of s and left to right is valid, check right to left 
        s = new StringBuilder(s).reverse().toString();
        if (pattern[0] == patterns[0][0]) {
            dfs(s, 0, 0, patterns[1]);
        } else {
            res.add(s);
        }
    }
}
