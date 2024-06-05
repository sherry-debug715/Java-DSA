package TwoPointers;

import java.util.ArrayList;
import java.util.List;

// lintcode 647
// Time: O(N)
// Space: O(N)
public class FindAllAnagramsInStr {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> output = new ArrayList<>();
        if (s.length() == 0 || p.length() == 0 || s.length() < p.length()) {
            return output;
        }

        int[] counter = new int[26];
        for (char c : p.toCharArray()) {
            counter[c - 'a'] += 1;
        }

        int sSize = s.length();
        int left = 0, end = 0;
        char[] charS = s.toCharArray();
        int match = 0;

        while (end < sSize) {
            if (counter[charS[end] - 'a'] >= 1) {
                match += 1;
            }
            counter[charS[end] - 'a'] -= 1;
            end += 1;

            // check if found a anagram 
            if (match == p.length()) {
                output.add(left);
            }
            // found a substring of the size as p but not a valid anagram. 
            if (end - left == p.length()) {
                // left point at a match 
                if (counter[charS[left] - 'a'] >= 0) {
                    match -= 1;
                }
                counter[charS[left] - 'a'] += 1;
                left += 1;
            }
        }
        return output;
    }
}
