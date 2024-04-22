package Sort.SortList;

class ListNode {
    int val;
    ListNode next;
    ListNode(int _val) {
        val = _val;
        next = null;
    }
}

public class MergeSort {
    public ListNode sortList(ListNode head) {
        // edge case and exit of recursion 
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMid(head);
        // divide 
        // right is the sorted right head 
        ListNode right = sortList(mid.next);
        // break the linked list into two lists 
        mid.next = null; 
        // left is the sorted left head 
        ListNode left = sortList(head);
        // conquer 
        ListNode newHead = merge(left, right);
        return newHead;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head, fast = head.next; 
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode leftHead, ListNode rightHead) {
        ListNode dummy = new ListNode(0), tail = dummy;

        while (leftHead != null && rightHead != null) {
            if (leftHead.val < rightHead.val) {
                tail.next = leftHead;
                leftHead = leftHead.next;
            } else {
                tail.next = rightHead;
                rightHead = rightHead.next;
            }
            tail = tail.next;
        }

        if (leftHead != null) {
            tail.next = leftHead;
        }

        if (rightHead != null) {
            tail.next = rightHead;
        }

        return dummy.next;
    }
}
