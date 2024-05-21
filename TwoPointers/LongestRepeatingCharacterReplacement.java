package TwoPointers;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLen = 0;
        int maxChar = 0;
        int left = 0;
        Map<Character, Integer> charCounter = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) { // i is right pointer 
            char curChar = s.charAt(i);
            charCounter.put(curChar, charCounter.getOrDefault(curChar, 0) + 1);
            // update maxChar 
            maxChar = Math.max(maxChar, charCounter.get(curChar));
            while (left < s.length() && i - left + 1 - maxChar > k) {
                charCounter.put(s.charAt(left), charCounter.get(s.charAt(left)) - 1);
                maxChar = Math.max(maxChar, charCounter.get(s.charAt(left)));
                left += 1;
            }
            maxLen = Math.max(maxLen, i - left + 1);
        }
        return maxLen;
    }
}
