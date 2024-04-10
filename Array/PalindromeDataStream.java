// Lintcode problem 958
package Array;

public class PalindromeDataStream {
    public int[] getStream(String s) {
        int[] answer = new int[s.length()];
        int[] alphabet = new int[26];
        int oddCounter = 0;

        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a'] += 1;
            // if odd number
            if (alphabet[s.charAt(i) - 'a'] % 2 != 0) oddCounter += 1;
            else oddCounter -= 1;
            
            // fill answer array
            if (oddCounter > 1) answer[i] = 0;
            else answer[i] = 1;
        }

        return answer;
    }
}
