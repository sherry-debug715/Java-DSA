package Queue;
class Node {
    public int val;
    public Node next;

    public Node(int val) {
        this.val = val;
        next = null;
    }
}

public class LinkedListQueue {
    private Node head;
    private Node tail;
    
    public LinkedListQueue() {
        head = null;
        tail = null;
    }
    /*
     * @param item: An integer
     * @return: nothing
     */
    public void enqueue(int item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    /*
     * @return: An integer
     */
    public int dequeue() {
        if (head == null) {
            return -1;
        }
        Node oldHead = head;
        head = head.next;
        return oldHead.val;
    }
}
