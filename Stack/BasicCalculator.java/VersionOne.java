package Stack.BasicCalculator.java;
// Lintcode 978
// Time: O(n)
// Space: O(n)
public class VersionOne {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int sign = 1; // 1: +, 2: - 
        int res = 0;
        int curSum = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                curSum = curSum * 10 + (c - '0');
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                // reset 
                sign = 1;
                res = 0;
                curSum = 0;
            } else if (c == '+') {
                // compute sum before +
                res += sign * curSum;
                sign = 1;
                curSum = 0;
            } else if (c == '-') {
                res += sign * curSum;
                sign = -1;
                curSum = 0;
            } else if (c == ')'){ 
                res += sign * curSum;
                res *= stack.pop(); // add the operator + or - 
                res += stack.pop(); // previous sum 
                sign = 1;
                curSum = 0;
            }
        }
        if (curSum != 0) {
            res += sign * curSum;
        }
        return res;
    }
}
