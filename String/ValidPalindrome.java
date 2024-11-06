package String;
// Lintcode problem 415: https://www.lintcode.com/problem/415/?fromId=161&_from=collection
// Time: O(N)
// Space: O(1)
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && convert(s.charAt(left)) == null) {
                left += 1;
            }
            while (right > left && convert(s.charAt(right)) == null) {
                right -= 1;
            }
            if (left < right && !convert(s.charAt(left)).equals(convert(s.charAt(right))) ) {
                return false;
            }
            left += 1;
            right -= 1;
        }
        return true;
    }

    private Character convert(char c) {
        if (c >= 'a' && c <= 'z') {
            return c;
        } 
        if (c >= 'A' && c <= 'Z') {
            return (char) (c + ('a' - 'A'));
        }
        if (c >= '0' && c <= '9') {
            return c;
        }
        return null;
    }
}
