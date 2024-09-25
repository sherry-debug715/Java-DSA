package DFS.WordBreak;
// Lintcode 683 
// Time: O(n * maxLen)
// Space: O(2n)
public class WordBreak3 {
    public int wordBreak3(String s, Set<String> dict) {
        if (dict.size() == 0) {
            return 0;
        }

        // Because the result is case insensitive, form a new dict and convert all strings form dict to lowercase 
        Set<String> newDict = new HashSet<>(); // dict -> lowercase 
        // find the longest string from dict to shorten left pointer iteration when updating dp array 
        int maxStrLen = 0;
        for (String word : dict) {
            newDict.add(word.toLowerCase());
            maxStrLen = Math.max(maxStrLen, word.length());
        }

        // dp[i] is the number of ways the substring of s[:i + 1] can be split into strings from newDict, case insensitive. 
        int n = s.length();
        int[] dp = new int[n + 1];
        // dp[0] represents number of ways to split an empty string
        dp[0] = 1;
        // i and j start from 1, start checking the s[i - l: i]th number, when i == j, check s[0: i]
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < maxStrLen + 1; j++) {
                if (i < j) {
                    break;
                }
                if (dp[i - j] == 0) { // there is no sensences can be formed from s[:i-j].
                    continue;
                }
                String curStr = s.substring(i-j, i);

                if (newDict.contains(curStr.toLowerCase())) {
                    dp[i] += dp[i - j];
                }
            }
        }
        return dp[n];
    }
}
