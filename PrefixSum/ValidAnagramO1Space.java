package PrefixSum;
// Lintcode 158
// Time: O(N)
// Space: O(1)
public class ValidAnagramO1Space {
    public boolean anagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] charTracker = new int[26];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                charTracker[Character.toLowerCase(s.charAt(i)) - 'a'] += 1;
            }
            if (t.charAt(i) != ' ') {
                charTracker[Character.toLowerCase(t.charAt(i)) - 'a'] -= 1;
            }
        }

        for (int n : charTracker) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }
}
