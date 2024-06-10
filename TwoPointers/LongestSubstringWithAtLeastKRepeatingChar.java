package TwoPointers;

// Lintcode 1261
// Time: O(26 * N)
// Space: O(1)
public class LongestSubstringWithAtLeastKRepeatingChar {
    public int longestSubstring(String s, int k) {
        int output = 0;
        for (int max = 1; max < 27; max++) {
            int[] charCounter = new int[26];
            for (int left = 0, right = 0, unique = 0, ok = 0; right < s.length();) {
                if (unique <= max) {
                    char c = s.charAt(right);
                    right += 1;
                    charCounter[c - 'a'] += 1;
                    if (charCounter[c - 'a'] == 1) {
                        unique += 1;
                    }
                    if (charCounter[c - 'a'] == k) {
                        ok += 1;
                    }
                    // found a substring. 
                    if (unique == max && ok == max) {
                        output = Math.max(output, right - left);
                    }
                } else { // move left pointer
                    char cLeft = s.charAt(left);
                    left += 1;
                    charCounter[cLeft - 'a'] -= 1;
                    if (charCounter[cLeft - 'a'] == 0) {
                        unique -= 1;
                    }
                    if (charCounter[cLeft - 'a'] == k - 1) {
                        ok -= 1;
                    }
                }
            }
            // end while 
        }
        // end for 
        return output;
    }
}
