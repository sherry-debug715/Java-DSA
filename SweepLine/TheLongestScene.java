package SweepLine;
// Lintcode 1379 
public class TheLongestScene {
    // Time: O(2n) where n is the length of str 
     // Space: O(n) where n is the length of str 
     public int getLongestScene(String str) {
        if (str.length() <= 1) {
            return str.length(); // Handle cases where string length is 0 or 1
        }

        char[] charArr = str.toCharArray();
        int[] farthestIndex = new int[26]; // Stores the farthest occurrence of each character

        // Step 1: Fill the farthest index for each character
        for (int i = 0; i < charArr.length; i++) {
            farthestIndex[charArr[i] - 'a'] = i;
        }

        int maxSceneLength = 0; // Track the maximum scene length
        int currentStart = 0; // Start of the current scene
        int currentEnd = farthestIndex[charArr[0] - 'a']; // End of the current scene

        // Step 2: Traverse the string and find the longest scene
        for (int i = 0; i < charArr.length; i++) {
            // Extend the current scene's end if needed
            currentEnd = Math.max(currentEnd, farthestIndex[charArr[i] - 'a']);

            // If the current index reaches the currentEnd, we finish a scene
            if (i == currentEnd) {
                // Calculate the length of the current scene
                int sceneLength = currentEnd - currentStart + 1;
                maxSceneLength = Math.max(maxSceneLength, sceneLength); // Update max length
                currentStart = i + 1; // Start a new scene from the next character
            }
        }

        return maxSceneLength;
    }
}
