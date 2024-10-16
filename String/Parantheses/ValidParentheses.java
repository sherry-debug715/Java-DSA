package String.Parantheses;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
        public boolean isValidParentheses(String s) {
        Map<Character, Character> bracketMap = new HashMap<>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (!stack.isEmpty() && curChar == bracketMap.get(stack.peek())) {
                stack.pop();
            } else {
                if (bracketMap.containsKey(curChar)) {
                    stack.push(curChar);
                }
                else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
