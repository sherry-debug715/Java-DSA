package DP.Coordinates;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// lintcode 1221 
// Time: O(n * m^2), n is the length of input words, m is the length of each word
public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] w) {
        // build trie 
        Set<String> words = new HashSet<>();
        for (String word : w) {
            words.add(word);
        }

        List<String> res = new ArrayList<>();
        for (String word : w) {
            if (isConcatWord(word, words)) {
                res.add(word);
            }
        }
        return res;
    } 

    private boolean isConcatWord(String word, Set<String> words) {
        int n = word.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) { // check if word[i:j) is a word from words 
            if (!dp[i]) continue;
            for (int j = i + 1; j <= n; j++) {
                if (j - i < n && words.contains(word.substring(i, j))) {
                    dp[j] = true;
                }
            }
            if (dp[n]) {
                return true;
            }
        } 
        return false;
    }
}
