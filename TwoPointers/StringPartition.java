package TwoPointers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// lintcode 328
// Description
// Given a string with all characters in uppercase, please divide the string into as many parts as possible so that each letter appears in only one part. Return an array containing the length of each part.
// Example:
    // Input:
    // "MPMPCPMCMDEFEGDEHINHKLIN"
    // Output:
    // [9,7,8]
    // Explanation:
    // "MPMPCPMCM"
    // "DEFEGDE"
    // "HINHKLIN"
public class StringPartition {
    // Time: O(N)
    // Space: O(N)
    public List<Integer> splitString(String s) {
        List<Integer> output = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return output;
        }
        // charMap.get(char) is the last position of char in s 
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charMap.put(c, i);
        }

        int lastPos = 0, left = 0, right = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);
            lastPos = Math.max(lastPos, charMap.get(ch));
            if (right == lastPos) {
                output.add(right - left + 1);
                left = right + 1;
            }
            right += 1;
        }
        // end while 
        return output;
    }
}
