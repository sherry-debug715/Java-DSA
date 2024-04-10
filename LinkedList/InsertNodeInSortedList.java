package LinkedList;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        next = null;
    }
}

public class InsertNodeInSortedList {
    public ListNode insertNode(ListNode head, int val) {
        ListNode newNode = new ListNode(val);
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        // find the last element < val
        while (cur.next != null && cur.next.val < val) {
            cur = cur.next;
        }

        ListNode curNext = cur.next;
        cur.next = newNode;
        newNode.next = curNext;

        return dummy.next;

    }
}
