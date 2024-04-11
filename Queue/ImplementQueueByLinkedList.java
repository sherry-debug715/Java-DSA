package Queue;

class Node {
    public int val;
    public Node next, prev;
    public Node(int val) {
        this.val = val;
        prev = next = null;
    }
}

public class ImplementQueueByLinkedList {
    public Node head, tail;

    public ImplementQueueByLinkedList() {
        head = null;
        tail = null;
    }

    /*
     * @param item: An integer
     * @return: nothing
     */
    public void push_front(int item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    /*
     * @param item: An integer
     * @return: nothing
     */
    public void push_back(int item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    /*
     * @return: An integer
     */
    public int pop_front() {
        if (head == null) {
            return -11;
        }

        Node oldHead = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            head = tail = null;
        }
        return oldHead.val;
    }

    /*
     * @return: An integer
     */
    public int pop_back() {
        if (tail == null) {
            return -11;
        }

        Node oldTail = tail;
        tail = tail.prev;
        // if only one item in the queue, tail.prev is null
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }

        return oldTail.val;
    }
}
