package String;
// Leetcode 1071: https://leetcode.com/problems/greatest-common-divisor-of-strings/
// Time: O(N)
// Space: O(1)
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
        // check if valid middle of shorter
        int p1 = 0;
        for (int i = 0; i < shorter.length() / (idx + 1); i++) {
            if (!shorter.substring(0, idx + 1).equals(shorter.substring(p1, p1 + idx + 1))) {
                return false;
            }
            p1 += idx + 1;
        }
        int p2 = 0;
        // check if valid middle of longer 
        for (int i = 0; i < longer.length() / (idx + 1); i++) {
            if (!shorter.substring(0, idx + 1).equals(longer.substring(p2, p2 + idx + 1))) {
                return false;
            }
            p2 += idx + 1;
        }
        return true;
    }
}
