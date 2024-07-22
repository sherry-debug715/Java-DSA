package TwoPointers;

import java.util.Set;
import java.util.HashSet;
// Lintcode 384 
// Time: O(N)
// Space: O(1)  

public class LongestSubstrWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        
        int output = 0; 
        int[] count = new int[128];
        int n = s.length();
        for (int l = 0, r = 0; r < n; r++) {
            ++count[s.charAt(r)];
            while(count[s.charAt(r)] > 1) {
                count[s.charAt(l)] -= 1;
                l += 1;
            }
            output = Math.max(output, r - l + 1);
        }
        return output;
    }
}
