package String;
// Leetcode 1071: https://leetcode.com/problems/greatest-common-divisor-of-strings/
// Time: O(N) 1ms 
// Space: O(1) 41.77mb
public class GreatestCommonDivisorOfStr {
    public String gcdOfStrings(String str1, String str2) {
        String shorter = str1, longer = str2;
        if (str1.length() > str2.length()) {
            shorter = str2;
            longer = str1;
        }
        int str1Size = str1.length();
        int str2Size = str2.length();
        
        for (int i = shorter.length() - 1; i >= 0; i--) {
            if (str1Size % (i + 1) == 0 && str2Size % (i + 1) == 0) {
                if (validDivisor(i, shorter, longer)) {
                    return shorter.substring(0, i + 1);
                }
            }    
        }
        return "";
    }
    
    private boolean validDivisor(int idx, String shorter, String longer) {
        String targetStr = shorter.substring(0, idx + 1);
        // check if idx is a valid divisor for shorter itself
        int p1 = 0;
        while (p1 < shorter.length()) {
            if (!targetStr.equals(shorter.substring(p1, p1 + idx + 1))) {
                return false;
            }
            p1 += idx + 1;
        }

        // check if targetStr is a valid divisor for longer 
        p1 = 0;
        while (p1 < longer.length()) {
            if (!targetStr.equals(longer.substring(p1, p1 + idx + 1))) {
                return false;
            }
            p1 += idx + 1;
        }
        return true;
    }
}
