package TwoPointers;

import java.util.Map;

// Lintcode 32
// Time: O(N)
// Space: O(N)
public class MinWindowSubstr {
    public String minWindow(String source, String target) {
        int startIdx = 0, endIdx = 0;
        int minLen = Integer.MAX_VALUE;
        int targetSize = target.length();
        Map<Character, Integer> charMap = new HashMap<>();
        int curSize = 0;
        // populate charCounter with the number of char of target 
        for (int i = 0; i < targetSize; i++) {
            char c = target.charAt(i);
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }
        
        int r = 0;
        for (int l = 0; l < source.length(); l++) {
            if (!charMap.containsKey(source.charAt(l))) {
                continue;
            }
            r = Math.max(l, r);
            while (r < source.length() && curSize < targetSize) {
                char rc = source.charAt(r);
                if (charMap.containsKey(rc)) {
                    if (charMap.get(rc) > 0) {
                        curSize += 1;
                    }
                    charMap.put(rc, charMap.get(rc) - 1);
                }
                r += 1;
            }
            // end while 
            if (curSize == targetSize) {
                if (r - l < minLen) {
                    minLen = r - l;
                    startIdx = l;
                    endIdx = r;
                }
            }
            // move window to right;
            char lc = source.charAt(l);
            if (charMap.get(lc) >= 0) {
                curSize -= 1;
            }
            charMap.put(lc, charMap.get(lc) + 1);
        }
        // end for 
        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return source.substring(startIdx, endIdx);

    } 
}
