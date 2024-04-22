package String;
// Time: O(N^2)
// Space: O(1)
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        // edge case 
        if (s == null || s.length() == 0) {
            return s;
        }

        int maxLen = 0;
        String output = "";

        for (int i = 0; i < s.length(); i++) {
            // odd length 
            String palindrome = calcuPalindrome(s, i, i);
            if (palindrome.length() > maxLen) {
                maxLen = palindrome.length();
                output = palindrome;
            }

            // even length 
            palindrome = calcuPalindrome(s, i, i + 1);
            if (palindrome.length() > maxLen) {
                maxLen = palindrome.length();
                output = palindrome;
            }
        }

        return output; 
    }

    private String calcuPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left -= 1;
            right += 1;
        }

        return s.substring(left + 1, right);
    }
}
