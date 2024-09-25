package DFS.WordBreak;
public class WordBreak1 {
       // Time: O(n * maxLen)
    // Space: O(n + 1)
    public boolean wordBreakDP(String s, Set<String> wordSet) {
        if ((s == null || s.length() == 0) && (wordSet == null || wordSet.isEmpty())) {
            return true;
        }
        if (s.length() == 0) {
            return false;
        }
        if (wordSet.isEmpty()) {
            return false;
        }

        // count the longest String from wordSet to shorten left pointer iteration 
        int maxLen = 0;
        for (String word : wordSet) {
            maxLen = Math.max(maxLen, word.length());
        }

        int n = s.length();
        boolean[] dp = new boolean[n + 1]; // dp[i] represent if dp[i - l: i] is in wordSet 
        dp[0] = true; // we can view the 0th substring is in wordSet

        for (int i = 1; i <= n; i++) { // substring[l: i)
            for (int l = 1; l < l + maxLen; l++) {
                if (l > i) {
                    break;
                }
                // if the i - l th letter don't form a valid substring, continue
                if (!dp[i - l]) {
                    continue;
                }
                String sub = s.substring(i - l, i);
                if (wordSet.contains(sub)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    } 
}
