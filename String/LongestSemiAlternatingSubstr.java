package String;

public class LongestSemiAlternatingSubstr {
    public int longestSemiAlternatingSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int counter = 1;
        int maxSubStr = 0;

        while (left < s.length() && right < s.length()) {
            if (right > 0 && s.charAt(right) == s.charAt(right - 1)) {
                counter += 1;
                if (counter == 3) {
                    left = right - 1;
                    counter = 2;
                } 
            } else {
                counter = 1;
            }

            // update maxSubStr as long as the substring is valid
            maxSubStr = Math.max(maxSubStr, right - left + 1);
            right += 1;
        }

        return maxSubStr;
    }
}
