package TwoPointers;

import java.util.Map;

// Lintcode 32 
/// Leetcode 76
// Time: O(N)
// Space: O(N)
public class MinWindowSubstr {
    public String minWindow(String source, String target) {
        int startIdx = 0, endIdx = 0;
        int minLen = Integer.MAX_VALUE;
        int match = 0;
        int[] charMap = new int[256];
        
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            charMap[c] += 1;
            if (charMap[c] == 1) {
                match += 1;
            }
        }
        
        int r = 0;
        for (int l = 0; l < source.length(); l++) {
            r = Math.max(l, r);
            // expande window 
            while (r < source.length() && match != 0) {
                char rc = source.charAt(r);
                charMap[rc] -= 1;
                if (charMap[rc] == 0) {
                    match -= 1;
                }
                r += 1;
            }
            // end while
            if (match == 0) {
                if (r - l < minLen) {
                    minLen = r - l;
                    startIdx = l;
                    endIdx = r;
                }
            }
            // move window to right;
            char lc = source.charAt(l);
            charMap[lc] += 1;
            if (charMap[lc] > 0) {
                match += 1;
            }
        }
        // end for 
        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return source.substring(startIdx, endIdx);


    } 
}
