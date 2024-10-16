package String;
// Leetcode 44 
// Time: O(N) where n is the length of s string
// Space: O(1)
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;

        while (sIdx < sLen) {
            // Case 1: Characters match, or pattern has '?' which matches any character
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                sIdx++;
                pIdx++;
            }
            // Case 2: Pattern contains '*', which can match any sequence (including empty)
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                starIdx = pIdx; // Mark the position of '*'
                sTmpIdx = sIdx; // Mark the position of the match in the input string
                pIdx++; // Move the pattern pointer to the next character
            }
            // Case 3: There's a mismatch, but there was a '*' previously
            else if (starIdx != -1) {
                pIdx = starIdx + 1; // Go back to the position after the last '*'
                sTmpIdx++;          // Increment the match position in the input string
                sIdx = sTmpIdx;     // Update the input pointer
            }
            // Case 4: No match, and no previous '*', so return false
            else {
                return false;
            }
        }

        // Check if remaining characters in pattern are all '*', which can match an empty sequence
        while (pIdx < pLen && p.charAt(pIdx) == '*') {
            pIdx++;
        }

        // If we've exhausted both the input string and the pattern, return true
        return pIdx == pLen;
    }
}
