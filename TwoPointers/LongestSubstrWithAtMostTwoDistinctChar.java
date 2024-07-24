package TwoPointers;
// Lintcode 928 
// Time: O(N)
// Space: O(1)
public class LongestSubstrWithAtMostTwoDistinctChar {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() == 1) {
            return 1;
        }
        
        int k = 2;
        char[] sArr= s.toCharArray();
        int n = s.length();
        int[] charMap = new int[225];
        int l = 0;
        int output = 1;
        for (int r = 0; r < n; r++) {
            charMap[sArr[r]] += 1;
            if (charMap[sArr[r]] == 1) {
                k -= 1;
            }
            if (k >= 0 && k <= 2) {
                output = Math.max(output, r - l + 1);
            }
            while (k < 0 && l < r) {
                charMap[sArr[l]] -= 1;
                if (charMap[sArr[l]] == 0) {
                    k += 1;
                }
                l += 1;
            }
        }
        
        return output;
    }
}
