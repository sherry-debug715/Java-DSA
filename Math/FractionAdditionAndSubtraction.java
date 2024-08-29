package Math;
// leetcode 592
// Time: O(N);
// Space: O(1)
public class FractionAdditionAndSubtraction {
    public String fractionAddition(String expression) {
        int[] res = new int[2];
        res[1] = 1;
        char[] cArr = expression.toCharArray();
        int i = 0,  n = cArr.length;
        while (i < n) {
            int operator = 1;
            // update operator 
            if (cArr[i] == '+' || cArr[i] == '-') {
                operator = cArr[i] == '+' ? 1 : -1;
                i += 1;
            }
            int curNum = 0;
            while (i < n && Character.isDigit(cArr[i])) {
                curNum = curNum * 10 + (cArr[i] - '0');
                i += 1;
            }
            int numerator = curNum * operator;
            i += 1; // avoid '/'
            curNum = 0;
            while (i < n && Character.isDigit(cArr[i])) {
                curNum = curNum * 10 + (cArr[i] - '0');
                i += 1;
            }
            int denominator = curNum;
            // calculate res 
            // [numerator, denominator] and res[0], res[1]
            int lcm = calcuLcm(denominator, res[1]);
            res[0] = numerator * (lcm / denominator) + res[0] * (lcm / res[1]);
            res[1] = lcm;
            int gcd = getGcd(Math.abs(res[0]), Math.abs(res[1]));
                res[0] /= gcd;
                res[1] /= gcd;
        }
        // end while 
        if (res[1] == 0) {
            return "0/1";
        }
        return res[0] + "/" + res[1];
    }

    private int getGcd(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    private int calcuLcm(int a, int b) {
        return Math.abs(a * b) / getGcd(a, b);
    }
}
