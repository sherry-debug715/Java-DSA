package Math;
// lintcode 656 
// Time: O(n1 * n2)
// Space: O(n1 + n2)
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        int n2 = num2.length(), n1 = num1.length();
        int n3 = n1 + n2;
        char[] num2Arr = num2.toCharArray();
        char[] num1Arr = num1.toCharArray();
        int[] num3 = new int[n3];
        int i, j, carry, product;
        for (i = n1 - 1; i >= 0; i--) {
            carry = 0;
            for (j = n2 - 1; j >= 0; j--) {
                product = carry + num3[i + j + 1] + (num1Arr[i] - '0') * (num2Arr[j] - '0');
                num3[i + j + 1] = product % 10;
                carry = product / 10;
            }
            num3[i + j + 1] = carry;
        }
        
        // form output 
        i = 0;
        while (i < n3 - 1 && num3[i] == 0) {
            i += 1;
        } 
        StringBuilder output = new StringBuilder();
        while (i < n3) {
            output.append(num3[i++]);
        }
        return output.toString();
        
    }
}
