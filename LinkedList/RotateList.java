package LinkedList;

public class RotateList {
    private int getLength(ListNode head) {
        int counter = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            counter += 1;
        }
        return counter;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        int listLength = getLength(head);
        k %= listLength;
        // create k - 1 gap between dummy and fast
        head = dummy; 
        while (k > 0 && head != null) {
            head = head.next;
            k -= 1;
        }
        // move slow pointer to the last k - 1 th node
        ListNode slow = dummy;
        while (head != null && head.next != null) {
            slow = slow.next;
            head = head.next;
        }

        head.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null;
        return dummy.next;
    }
}
