package String;
// Lintcode problem 1790: https://www.lintcode.com/problem/1790/?fromId=161&_from=collection
// Time: O(n), n is the length of result string.
// Space: O(n), n is the length of result string.
public class RotateString2 {
    public String rotateString2(String str, int left, int right) {
        // edge case 
        if (str == null || str.length() == 0) {
            return str;
        }

        // if left < right, rotate from left to right, else right to left 
        int offSet = (left - right) % str.length();
        int flag = offSet >= 0 ? 1 : -1;
        offSet = Math.abs(offSet) % str.length();
        String prefix;
        String suffix;

        if (flag >= 0) {
            // right to left 
            suffix = str.substring(0, offSet); 
            prefix = str.substring(offSet, str.length());
        } else {
            // left to right
            suffix = str.substring(0, str.length() - offSet);
            prefix = str.substring(str.length() - offSet, str.length());
        }

        return prefix + suffix;
    }
}
