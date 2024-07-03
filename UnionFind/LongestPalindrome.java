package UnionFind;

import java.util.HashSet;
import java.util.Set;

// Lintcode 627
public class LongestPalindrome {
    // the answer is the count of characters that has even number of appereances.
    // for characters that has odd number of appereances,
    // their appereances minus 1 will make their apperances even.
    // And finally we can put an unused character in the middle of the palindrome
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
