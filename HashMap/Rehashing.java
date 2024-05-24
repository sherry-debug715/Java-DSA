package HashMap;
// lintcode 129: https://www.lintcode.com/problem/129/?fromId=161&_from=collection
// Time: O(N)
// Space: O(N)
public class Rehashing {
    public ListNode[] rehashing(ListNode[] hashTable) {
        int oldCapacity = hashTable.length;
        int newCapacity = oldCapacity * 2;
        ListNode[] newHashTable = new ListNode[newCapacity];
        for (ListNode node : hashTable) {
            ListNode cur = node;
            while (cur != null) {
                int key = hashcode(cur.val, newCapacity);
                // empty list 
                if (newHashTable[key] == null) {
                    newHashTable[key] = new ListNode(cur.val);
                } else {
                    // add to tail of the list 
                    addToTail(newHashTable[key], cur);
                }
                cur = cur.next;
            }
        } 
        return newHashTable;
    }

    private void addToTail(ListNode head, ListNode node) {
        while (head.next != null) {
            head = head.next;
        }
        head.next = new ListNode(node.val);
    }

    private int hashcode(int key, int capacity) {
        return (key % capacity + capacity) % capacity;
    }
}
