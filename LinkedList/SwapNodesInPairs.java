package LinkedList;
// Leetcode 24
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        head = dummy;

        while (head.next != null && head.next.next != null) {
            ListNode n1 = head.next, n2 = head.next.next;
            head.next = n2;
            n1.next = n2.next;
            n2.next = n1;

            head = n1;
        }
        return dummy.next;
    }
}
