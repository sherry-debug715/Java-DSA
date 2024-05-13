package DP.Coordinates;

public class WordBreak3 {
    public int wordBreak3(String s, Set<String> dict) {
        if (dict.size() == 0) {
            return 0;
        }

        Set<String> newDict = new HashSet<>(); // dict -> lowercase 
        int maxStrLen = 0;
        for (String word : dict) {
            newDict.add(word.toLowerCase());
            // compute the length 
            maxStrLen = Math.max(maxStrLen, word.length());
        }

        // dp[i] is the number of sentences can be formed from dict with s[:i + 1].
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        // i and j start from 1, start checking the s[i - 1, i]th number, when i == j, check s[0, i]
        for (int i = 1; i < s.length() + 1; i++) {
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
        return dp[dp.length - 1];
    }
}
