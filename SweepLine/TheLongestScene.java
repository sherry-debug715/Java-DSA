package SweepLine;

import java.util.Arrays;

// Lintcode 1379 
public class TheLongestScene {
     // Time: O(26n) where n is the length of str 
     // Space: O(n) where n is the length of str 
    public int getLongestScene(String str) {
        if (str.length() <= 1) {
            return str.length(); // Handle cases where string length is 0 or 1
        }

        char[] charArr = str.toCharArray();
        int[][] charIntervals = new int[26][2]; // Stores the first and farthest occurrences of each character
        for (int i = 0; i < 26; i++) {
            charIntervals[i][0] = charArr.length;
            charIntervals[i][1] = -1;
        }

        // Step 1: Fill the farthest index for each character
        for (int i = 0; i < charArr.length; i++) {
            int idx = charArr[i] - 'a';
            charIntervals[idx][0] = Math.min(charIntervals[idx][0], i);
            charIntervals[idx][1] = Math.max(charIntervals[idx][1], i);
        }

        Arrays.sort(charIntervals, (a, b) -> a[0] - b[0]);

        int currentStart = charIntervals[0][0]; // Start of the current scene
        int currentEnd = charIntervals[0][1]; // End of the current scene
        int maxSceneLength = currentEnd - currentStart + 1; // Track the maximum scene length

        // Step 2: Traverse the 26 letters 
        for (int i = 1; i < 26; i++) {
            if (charIntervals[i][1] != -1 && charIntervals[i][0] < charArr.length) {
                if (charIntervals[i][0] <= currentEnd) {
                    currentEnd = Math.max(currentEnd, charIntervals[i][1]);
                } else {
                    currentStart = charIntervals[i][0];
                    currentEnd = charIntervals[i][1];
                }
                maxSceneLength = Math.max(maxSceneLength, currentEnd - currentStart + 1);
            }
        }

        return maxSceneLength;
    }
}
