// Leetcode problem 19

package LinkedList;

public class RemoveNthNodeFromBack {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        // create a gap of n between dummy and fast
        while (fast != null && n >= 0) {
            fast = fast.next;
            n -= 1;
        }

        // move the gap to the end of the list 
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}
