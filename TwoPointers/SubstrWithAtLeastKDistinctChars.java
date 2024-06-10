package TwoPointers;
// Lintcode problem 1375: https://www.lintcode.com/course/101/learn/1375?chapterId=534&sectionId=3979&ac=true
// Time: O(N)
// Space: O(N) -> N is the number of unique characters in a substring
import java.util.Map;

public class SubstrWithAtLeastKDistinctChars {
    public long kDistinctCharacters(String s, int k) {
        long output = 0;
        if (s.length() == 0 || k == 0) {
            return output;
        }

        Map<Character, Integer> charMap = new HashMap<>();
        int uniqueCount = 0;
        int r = 0;
        for (int l = 0; l < s.length(); l++) {
            while (r < s.length() && uniqueCount < k) {
                charMap.put(s.charAt(r), charMap.getOrDefault(s.charAt(r), 0) + 1);
                if (charMap.get(s.charAt(r)) == 1) {
                    uniqueCount += 1;
                }
                r += 1;
            }
            if (uniqueCount >= k) {
                output += s.length() - r + 1;
            }
            charMap.put(s.charAt(l), charMap.get(s.charAt(l)) - 1);
            if (charMap.get(s.charAt(l)) == 0) {
                uniqueCount -= 1;
            }
        }
        return output;
    }
}
