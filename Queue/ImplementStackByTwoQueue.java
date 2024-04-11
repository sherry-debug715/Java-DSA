package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackByTwoQueue {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public ImplementStackByTwoQueue() {
        queue1 = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }
    public void push(int x) {
        queue2.offer(x);
        while (queue1.size() != 0) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    } 
    /*
     * @return: nothing
     */
    public void pop() {
        queue1.poll();
    }

    /*
     * @return: An integer
     */
    public int top() {
        return queue1.peek();
    }

    /*
     * @return: True if the stack is empty
     */
    public boolean isEmpty() {
        return queue1.isEmpty();
    }
}
