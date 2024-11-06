package String.Calculator;

import java.util.Stack;

// Leetcode 224
public class BasicCalculatorHard {
    public int calculate(String s) {
        int res = 0;
        Stack<Integer> globalStack = new Stack<>();
        int current = 1;
        int global = 1;
        int curNum = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                curNum = (curNum * 10 + (c - '0'));
                continue;
            }
            int prefix = current * global;
            res += (curNum * prefix);
            curNum = 0;
            if (c == '(') {
                globalStack.push(current);
                global *= current;
                current = 1;
            } else if (c == ')') {
                int removed = globalStack.pop();
                global /= removed;
                current = 1;
            } else if (c == '+') {
                current = 1;
            } else if (c == '-') {
                current = -1;
            }
        }
        if (curNum != 0) {
            res += curNum * current;
        }
        return res;
    }
}
