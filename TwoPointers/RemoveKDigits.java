package TwoPointers;
// lintcode 1255
// Time: O(N)
// Space: O(1)
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        if (k >= num.length()) {
            return "0";
        }
        
        char[] numArr = num.toCharArray();
        char[] list = new char[num.length()];
        int lastIdx = 0;
        int len = num.length() - k;
        for (int i = 0; i < numArr.length; i++) {
            while (lastIdx > 0 && k > 0 && list[lastIdx - 1] > numArr[i]) {
                lastIdx -= 1;
                k -= 1; // need to remove a digit 
            }
            list[lastIdx++] = numArr[i];
        }
        
        // find leading 0s 
        int start = 0;
        while (start < len && list[start] == '0') {
            start += 1;
        }

        return start == len ? "0" : new String(list, start, len - start);
    }
}
