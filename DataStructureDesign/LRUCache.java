package DataStructureDesign;

import java.util.Map;
import java.util.HashMap;


public class LRUCache {
    class ListNode {
        int key;
        int val;
        ListNode prev;
        ListNode next;
        public ListNode(int _key, int _val) {
            key = _key;
            val = _val;
        }
    }

    int cap;
    ListNode head;
    ListNode tail;
    Map<Integer, ListNode> map;
    /*
    * @param capacity: An integer
    */public LRUCache(int capacity) {
        cap = capacity;
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<Integer, ListNode>();
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // if key not in map, return -1
        // else - locate the node, delete it, then set it to tail prev 
        if (!map.containsKey(key)) {
            return -1;
        } else {
            // locate the node 
            ListNode node = map.get(key);
            // delete it 
            deleteNode(node);
            // move to tail prev
            moveToTailPrev(node);
            return node.val;
        }
    }
    
    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // if key in map, remove the old node from linked list, create a new ListNode
        // and set it to tail prev, then set the new node to the key. 
        ListNode newNode = new ListNode(key, value);
        if (map.containsKey(key)) {
            ListNode oldNode = map.get(key);
            deleteNode(oldNode);

        } else {
            if (map.size() == cap) {
                map.remove(head.next.key);
                deleteNode(head.next);
            }
        }

        map.put(key, newNode);
        moveToTailPrev(newNode);
    }

    private void deleteNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToTailPrev(ListNode node) {
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node; 
    }
}
