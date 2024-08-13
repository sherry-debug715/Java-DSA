package Stack.BasicCalculator.java;
// Lintcode 980
public class VersionTwo {
    public int calculate(String s) {
        int num = 0;
        char sign = '+'; // sign is the operator before the current number
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '-') {
                    stack.push(-1 * num);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                } 
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = c;
                num = 0;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
