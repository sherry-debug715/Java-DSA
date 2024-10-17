package Array.LongestSubsequence;

import Sort.SortTheJumbledNumbers.java.ArrayList;

// Lintcode 1582
// Time: / O(n * klog(j)) where n = wordDict.size(), k == subsequence of s with max length, j == most freq char of s 
// Space: O(26n) where n == s.length()
public class ForLargeInput {
        public String longestSubsequence(Set<String> wordDict, String s) {
        if (wordDict.isEmpty() || s.length() == 0) {
            return "";
        }
        List<Integer>[] indexTracker = new List[26]; 
        for (int i = 0; i < 26; i++) {
            indexTracker[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < s.length(); i++) { // O(s.length())
            int idx = s.charAt(i) - 'a';
            indexTracker[idx].add(i);
        }
        int maxLen = 0;
        String res = "";
        // iterate over wordDict, check if 1. each word is a subsequence of s 
        // 2. comare length then lexi order 
        for (String word: wordDict) { // O(n * klog(j)) where n = wordDict.size(), k == subsequence of s with max length, j == most freq char of s 
            int curIdx = -1;
            int i = 0;
            while (i < word.length()) {
                if (indexTracker[word.charAt(i) - 'a'].isEmpty()) {
                    break;
                }
                // binary search to find the first idx in indexTracker[idx] list > curIdx 
                int nextIdx = findNextIdx(indexTracker[word.charAt(i) - 'a'], curIdx);
                if (nextIdx == -1) {
                    break;
                }
                curIdx = nextIdx;
                i += 1;
            }
            // if i is at the end of word, word is a subsequence of s 
            if (i == word.length()) {
                if (word.length() > maxLen) {
                    maxLen = word.length();
                    res = word;
                } else if (word.length() == maxLen) {
                    int lexiOrder = res.compareTo(word);
                    if (lexiOrder > 0) {
                        res = word;
                    }
                }
            }
        }
        return res;
    }

    private int findNextIdx(List<Integer> idxList, int target) {
        int left = 0, right = idxList.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (idxList.get(mid) <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (idxList.get(left) > target) {
            return idxList.get(left);
        }
        if (idxList.get(right) > target) {
            return idxList.get(right);
        }
        return -1;
    }
}
