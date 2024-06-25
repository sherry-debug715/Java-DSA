package DFS.WordBreak;
// lintcode 582
// Time: O(2^n)
// Space: O(2^n)
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak2 {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        Map<String, List<String>> memo = new HashMap<>();
        memo.put("", new ArrayList<String>());
        memo.get("").add(""); // memo = {"": [""]}
        return dfs(s, wordDict, memo);
    }

    private List<String> dfs(String s, Set<String> dict,  Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        List<String> ans = new ArrayList<>();

        for (int i = 1; i <= s.length(); i++) { // i is the right pointer therefore ends at s.length() 
            String s1 = s.substring(0, i); // [0, i) // prefix 
            String s2 = s.substring(i);
            if (dict.contains(s1)) {
                List<String> other = dfs(s2, dict, memo);
                for (String str : other) {
                    if (str == "") {
                        ans.add(s1);
                    } else {
                        ans.add(s1 + " " + str);
                    }
                }
            }
        }

        memo.put(s, ans);
        return ans;
    }
}
