package LinkedList;
// leetcode 25
// Time: O(n)
// Space: O(1)
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy; 
        while (head.next != null) {
            head = reverseK(head, k);
        }
        return dummy.next;
    }

    private ListNode reverseK(ListNode head, int k) {
        // 1) check if there are k nodes left in the linked list 
        ListNode curr = head;
        for (int i = 0; i < k; i++) {
            if (curr.next == null) {
                return curr; // when curr is returned, back at reverseKGroup funciton, head.next == null
            }
            curr = curr.next;
        }

        // 2) now reverse k nodes
        curr = head.next;
        ListNode prev = head, newTail = head.next; 
        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // break out of for loop, curr should be referencing the first node after the current reversed k nodes
        newTail.next = curr;
        head.next = prev;
        return newTail;
    }
}
