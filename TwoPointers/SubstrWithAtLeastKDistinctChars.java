package TwoPointers;
// Lintcode problem 1375: https://www.lintcode.com/course/101/learn/1375?chapterId=534&sectionId=3979&ac=true

public class SubstrWithAtLeastKDistinctChars {
    public long kDistinctCharacters(String s, int k) {
        long output = 0;
        Map<Character, Integer> counter = new HashMap<>();
        int n = s.length();
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (counter.size() < k && j < n) {
                char currentChar = s.charAt(j);
                counter.put(currentChar, counter.getOrDefault(currentChar, 0) + 1);
                j += 1;
            }
            if (counter.size() >= k) {
                output += (long) n - j + 1;
                // update i pointer in counter 
                char iChar = s.charAt(i);
                counter.put(iChar, counter.get(iChar) - 1);
                if (counter.get(iChar) == 0) {
                    counter.remove(iChar);
                }
            }
        }
        return output;
    }
}
