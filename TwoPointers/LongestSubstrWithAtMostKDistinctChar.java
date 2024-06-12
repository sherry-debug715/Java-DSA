package TwoPointers;
// lintcode 386: https://www.lintcode.com/problem/386/
// Time: O(N)
// Space: O(1)
public class LongestSubstrWithAtMostKDistinctChar {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int output = 0;
        int[] charCounter = new int[256];
        for (int l = 0, r = 0, unique = 0; r < s.length();) {
            if (unique <= k) {
                char c = s.charAt(r);
                r += 1;
                charCounter[c] += 1;
                if (charCounter[c] == 1) {
                    unique += 1;
                }

            } else {
                char cLeft = s.charAt(l);
                l += 1;
                charCounter[cLeft] -= 1;
                if (charCounter[cLeft] == 0) {
                    unique -= 1;
                }
            }
            if (unique <= k) {
                output = Math.max(output, r - l);
            }
        }
        return output;
    }
}
