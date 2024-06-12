package TwoPointers;
// Time: O(N)
// Space: O(1)
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] charMap = new int[26];
        int maxCharLen = 1;
        int l = 0;
        int maxLen = 0;

        for (int r = 0; r < s.length(); r++) { // O(N)
            char cr = s.charAt(r);
            charMap[cr - 'A'] += 1;
            maxCharLen = Math.max(maxCharLen, charMap[cr - 'A']);
            while (l < s.length() && r - l + 1 - maxCharLen > k) {
                // while incrementing left pointer, there is no need to 
                // update maxCharLen, because right pointer is not moved
                // therefore the current substring length will never exceed maxLen.
                char cl = s.charAt(l);
                charMap[cl - 'A'] -= 1;
                l += 1;
            }
            maxLen = Math.max(r - l + 1, maxLen);
        }
        return maxLen;
    }
}
