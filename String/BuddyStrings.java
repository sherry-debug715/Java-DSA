package String;

public class BuddyStrings {
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        if (s.equals(goal)) {
            // if there is no repeating char in the string, swapping would product different string
            int[] charMap = new int[26];
            for (int i = 0; i < s.length(); i++) {
                charMap[s.charAt(i) - 'a'] += 1;
                if (charMap[s.charAt(i) - 'a'] > 1) {
                    return true;
                }
            }
            return false;
        } else {
            // find two char with different index;
            int left = -1, right = -1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != goal.charAt(i)) {
                    if (left == -1) {
                        left = i;
                    } else if (right == -1) {
                        right = i;
                    } else {
                        // find the third char that differs 
                        return false;
                    }
                }
            }
            return right != -1 && s.charAt(left) == goal.charAt(right) && s.charAt(right) == goal.charAt(left);
        }
    }
}
