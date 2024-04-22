package String;

import java.util.HashSet;
import java.util.Set;
// Time: O(N)
// Space: O(N)
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        // edge case 
        if (s == null || s.length() == 0) {
            return 0;
        }
        // singleChar is a map of single letters from s 
        Set<Character> singleChar = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (singleChar.contains(c)) {
                singleChar.remove(c);
            } else {
                singleChar.add(c);
            }
        }

        int needToRemove = singleChar.size();
        if (needToRemove > 0) {
            needToRemove -= 1;
        }
        return s.length() - needToRemove;
    }
}
