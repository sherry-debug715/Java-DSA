package Stack;

import java.util.Stack;

public class ImplementQueueByTwoStacks {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public ImplementQueueByTwoStacks() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        stack1.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        if (stack2.isEmpty()) {
            pushUp();
        }

        return stack2.pop();
    }

    /*
     * @return: An integer
     */
    public int top() {
        if (stack2.isEmpty()) {
            pushUp();
        }

        return stack2.peek();
    }

    public void pushUp() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
}
