package TwoPointers;

import java.util.Set;
import java.util.HashSet;

public class LongestSubstrWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int maxLen = Integer.MIN_VALUE;
        Set<Character> curChar = new HashSet<>();
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            j = Math.max(i, j);
            while (j < s.length() && !curChar.contains(s.charAt(j))) {
                curChar.add(s.charAt(j));
                j += 1;
            }
            // have a window 
            maxLen = Math.max(maxLen, j - i);
            // remove i char from set 
            curChar.remove(s.charAt(i));
        }
        return maxLen;
    }
}
