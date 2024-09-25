package DFS.WordBreak;
// lintcode 582
// Time: O(2^n)
// Space: O(2^n)
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak2 {
    // Time: O(2^n) because the number of possible sentences grows exponentially)
    // Space: O(n) where n is the length of s, at the worst case, every substring of s is stored in memo

    public List<String> wordBreak(String s, Set<String> wordDict) {
        // edge case 
        if (s == null || s.length() == 0 || wordDict == null || wordDict.isEmpty()) {
            return new ArrayList<String>();
        }
        Map<String, List<String>> memo = new HashMap<>();
        // Initialize memoization with an empty string "".
        // This represents the base case: if the remaining substring is empty, 
        // it indicates a valid sentence has been fully formed, so we add an empty sentence ("") 
        // to represent the end of a valid sentence chain.
        memo.put("", new ArrayList<String>());
        memo.get("").add("");

        return dfs(s, wordDict, memo);
    }

    private List<String> dfs(String s, Set<String> wordDict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> ans = new ArrayList<>();
        // iterate over s string looking for a left substring that exist in wordDict 
        for (int i = 1; i <= s.length(); i++) {
            String leftSubstring = s.substring(0, i);
            String rightSubstring = s.substring(i);
            if (wordDict.contains(leftSubstring)) {
                List<String> innerAns = dfs(rightSubstring, wordDict, memo);
                for (String str : innerAns) {
                    // entire leftSubstring is in wordDict, the reason for giving memo a key of "" value of [""]
                    if (str.equals("")) {
                        ans.add(leftSubstring);
                    } else {
                        ans.add(leftSubstring + " " + str);
                    }
                }
            }
        }

        memo.put(s, ans);
        return ans;
    }
}
