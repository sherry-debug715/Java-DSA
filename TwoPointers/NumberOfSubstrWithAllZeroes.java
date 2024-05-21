package TwoPointers;

public class NumberOfSubstrWithAllZeroes {
    public int stringCount(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int output = 0;
        int right = 0;
        int n = str.length();
        for (int left = 0; left < n; left ++) {
            if (str.charAt(left) != '0') {
                continue;
            }
            right = Math.max(right, left + 1);
            while (right < n && str.charAt(right) == '0') {
                right += 1;
            }
            output += right - left;

        }
        return output;
    }
}
