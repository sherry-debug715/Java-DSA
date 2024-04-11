import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(10);
        queue.offer(9);
        queue.offer(8);
        queue.offer(7);
        queue.offer(6);
        queue.offer(5);

        while (!queue.isEmpty()) {
            System.out.print(queue.poll());
        }

        
    }
}


