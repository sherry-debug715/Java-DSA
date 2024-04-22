package String;
// Lintcode problem 415: https://www.lintcode.com/problem/415/?fromId=161&_from=collection
// Time: O(N)
// Space: O(1)
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        // edge case 
        if (s == null) {
            return false;
        }
        if (s == "") {
            return true;
        }
        // two pointer traverse toward middle 
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // if it's not number or valid alphabetic letter then continue
            while (left < right && !isValid(left, s)) {
                left += 1;
            }
            while (left < right && !isValid(right, s)) {
                right -= 1;
            }
            char leftChar = Character.toLowerCase(s.charAt(left));
            char rightChar = Character.toLowerCase(s.charAt(right));
            if (leftChar != rightChar) {
                return false;
            }
            left += 1;
            right -= 1;
        }
        return true;
    }

    private boolean isValid(int idx, String s) {
        char c = s.charAt(idx);
        return Character.isDigit(c) || Character.isLetter(c);
    }
}
