package LinkedList.CopyListWithRandomPointer;

import java.util.HashMap;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class HashMapVersion {
    public Node copyRandomList(Node head) {
    if (head == null) {
        return null;
    }

    HashMapVersion<Node, Node> oldToNew = new HashMapVersion<>();
    Node cur = head;

    // iterate over linked list to build new node refs
    while (cur != null) {
        Node newNode = new Node(cur.val);
        oldToNew.put(cur, newNode);
        cur = cur.next;
    }

    // iterate over linked list again to nect next and random pointers
    cur = head;
    while (cur != null) {
        Node newNode = oldToNew.get(cur);
        newNode.next = oldToNew.get(cur.next);
        // The HashMap.get() method in Java 
        // returns null if the map contains no mapping for the key.
        newNode.random = oldToNew.get(cur.random);
        cur = cur.next;
    }

    return oldToNew.get(head);
}
}
