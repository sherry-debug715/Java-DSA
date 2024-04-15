package Stack;

import java.util.Stack;

// Lintcode problem 575: https://www.lintcode.com/problem/575/
public class DecodeString {
    public String expressionExpand(String s) {
        if (s == null || s.length() == 0) return "";

        Stack<Object> stack = new Stack<>();
        int number = 0;

        for (char c : s.toCharArray()) {
            // check if c is a digit 
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '[') {
                // finished documenting number 
                // change number into Object type 
                stack.push(Integer.valueOf(number));
                number = 0;
            } else if (c == ']') {
                // 2[ad] get "ad" back 
                String newStr = popStack(stack);
                // pop number for the newStr 
                Integer counter = (Integer) stack.pop();
                for (int i = 0; i < counter; i++) {
                    stack.push(newStr);
                }
            } else {
                stack.push(String.valueOf(c));
            }
        }

        return popStack(stack);
    }

    private String popStack(Stack stack) {
        Stack<String> buffer = new Stack<>();
        while (!stack.isEmpty() && (stack.peek() instanceof String)) {
            buffer.push((String) stack.pop());
        }

        StringBuilder newStr = new StringBuilder();
        while (!buffer.isEmpty()) {
            newStr.append(buffer.pop());
        }
        return newStr.toString();
    }
}
