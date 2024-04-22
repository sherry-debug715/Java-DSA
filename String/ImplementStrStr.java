package String;
// Time: O(N)
// Space: O(1)
public class ImplementStrStr {
    public int strStr(String source, String target) {
        // edge case
        if (source.length() == 0 && target.length() == 0) {
            return 0;
        }
        if (target.length() == 0) {
            return 0;
        }

        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            if (source.charAt(i) == target.charAt(0) && isSubstring(i, source, target)) {
                return i;
            } 
        }
        return -1;
    }

    private boolean isSubstring(int i, String source, String target) {
        int j = 0;
        for (j = 0; j < target.length(); j++) {
            if (target.charAt(j) != source.charAt(i + j)) {
                return false;
            }
        }
        if (j == target.length()) {
            return true;
        }
        return false;
    }
}
