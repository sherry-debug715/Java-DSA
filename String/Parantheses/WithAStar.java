package String.Parantheses;

import java.util.Stack;

// Lintcode 1089
// Two solutions below 
class GreedySolution {
    /**
     * @param s: the given string
     * @return: whether this string is valid
     */
    // Time: O(n)
    // Space: O(1)
    public boolean checkValidString(String s) {
        char[] sArr = s.toCharArray();
        // minLeft keep track of number of '(', maxLeft count all '*' as '('
        // when ')', deduct 1 from both minLeft and maxLeft 
        int minLeft = 0, maxLeft = 0;
        for (int i = 0; i < sArr.length; i++) {
            char c = sArr[i];
            if (c == '(') {
                minLeft += 1;
                maxLeft += 1;
            } else if (c == '*') { // minLeft count '*' as ')', maxLeft count '*' as '('
                minLeft = Math.max(0, minLeft - 1);
                maxLeft += 1;
            } else {
                minLeft = Math.max(0, minLeft - 1);
                maxLeft -= 1;
                if (maxLeft < 0) {
                    return false;
                }
            }
        }

        return minLeft == 0;
    }
}

public class WithAStar {
    // Time: O(n) where n is the length of s 
    // Space: O(n) where n is the stack spaces 
    public boolean checkValidString(String s) {
        // edge case 
        char[] sArr = s.toCharArray();
        Stack<Integer> left = new Stack<>(); // left stores indexes of '('
        Stack<Integer> star = new Stack<>(); // star stores indexes of '*'

        for (int i = 0; i < sArr.length; i++) {
            char c = sArr[i];
            if(c == '*') {
                star.push(i);
            } else if (c == '(') {
                left.push(i);
            } else {
                if (!left.isEmpty()) {
                    left.pop();
                    continue;
                } 
                if (star.isEmpty()) {
                    // System.out.println("here left: " + left);
                    return false;
                }
                star.pop();
            }
        }
        // match the rest of unmatched '(' with '*'
        while (!left.isEmpty() && !star.isEmpty()) {
            int leftIdx = left.peek();
            int rightIdx = star.peek();
            if (leftIdx > rightIdx) {
                return false;
            }
            left.pop();
            star.pop();
        }
        return left.isEmpty();
    }
}
