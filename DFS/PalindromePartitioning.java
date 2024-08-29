package DFS;

import java.util.List;

// leetcode 131
// Time: O(n^2) for dp step 
// Space: O(n^2) for allPanlindrome 2d array
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        char[] sArr = s.toCharArray();
        boolean[][] allPanlindrome = checkPalindrome(sArr);
        dfs(s, 0, allPanlindrome, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(String s,
                     int startIdx,
                     boolean[][] allPanlindrome,
                     List<Integer> curSet,
                     List<List<String>> res) {
        if (startIdx == s.length()) {
            res.add(convertCurSet(curSet, s));
            return;
        }

        for (int i = startIdx; i < s.length(); i++) {
            if (!allPanlindrome[startIdx][i]) {
                continue;
            }
            curSet.add(i);
            dfs(s, i + 1, allPanlindrome, curSet, res);
            curSet.remove(curSet.size() - 1);
        }
    }

    private List<String> convertCurSet(List<Integer> curSet, String s) {
        int startIdx = 0;
        List<String> res = new ArrayList<>();
        for (int idx : curSet) {
            res.add(s.substring(startIdx, idx + 1));
            startIdx = idx + 1;
        }
        return res;
    }


    private boolean[][] checkPalindrome(char[] s) {
        int n = s.length;
        boolean[][] allPanlindrome = new boolean[n][n];

        // each char is a panlindrome 
        for (int i = 0; i < n; i++) {
            allPanlindrome[i][i] = true;
        }

        // for every two chars, check if they are palindrome 
        for (int i = 0; i < n - 1; i++) {
            allPanlindrome[i][i + 1] = s[i] == s[i + 1];
        }

        // starting from the back, the last 3 chars, check for all palindrome
        // if s[i:j] is a panlindrome, then s[i+1:j-1] should also be a valid panlindrome + s[i] == s[j]
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                allPanlindrome[i][j] = allPanlindrome[i + 1][j - 1] && s[i] == s[j];
            }
        }
        return allPanlindrome;
    }
}
