package Array.LongestSubsequence;
// Lintcode 1581
public class ForSmallInput {
    // Time: O(m * n) where m == wordDict.size() and n == s.length()
    // Space: O(1)
    public String longestSubsequence(Set<String> wordDict, String s) {
        String res = "";
        int maxLen = 0;
        for (String word : wordDict) {
            if (isSubsequence(word, s)) {
                if (word.length() > maxLen || (maxLen == word.length() && res.compareTo(word) > 0)) {
                    maxLen = word.length();
                    res = word;
                }
            }
        }
        return res;
    }

    private boolean isSubsequence(String word, String s) {
        int p1 = 0, p2 = 0; // p1: word pointer, p2: s pointer 
        while (p1 < word.length() && p2 < s.length()) {
            if (word.charAt(p1) == s.charAt(p2)) {
                p1 += 1;
            }
            p2 += 1;
        }
        return p1 == word.length();
    }
}
