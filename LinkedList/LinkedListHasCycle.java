package LinkedList;
// Lintcode 102 
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
}

public class LinkedListHasCycle {
    /**
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    // Time: O(N) where N is the number of nodes in the linked list.
    // Space: O(1)
    public boolean hasCycle(ListNode head) {
        // empty list or a list with only one node doesn't have cycle 
        if (head == null || head.next == null) {
            return false;
        }
        // Floyd's cycle detection: slow moves 1 step, fast moves 2 steps 
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null && slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // if fast and slow meet, a cycle is proved -  Floyd's cycle detection algorithm
        return fast == slow;
    }
}
