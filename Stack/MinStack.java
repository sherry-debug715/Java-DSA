package Stack;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        stack.push(number);
        if (minStack.isEmpty()) {
            minStack.push(number);
        } else {
            if (number <= minStack.peek()) {
                minStack.push(number);
            }
        }
    }

    /*
     * @return: An integer
     */
    public int pop() {
        int popVal = stack.pop();
        if (popVal == minStack.peek()) {
            minStack.pop();
        }
        return popVal;
    }

    /*
     * @return: An integer
     */
    public int min() {
        return minStack.peek();
    }
}
