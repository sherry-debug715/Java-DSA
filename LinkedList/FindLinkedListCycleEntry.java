package LinkedList;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        next = null;
    }
}

public class FindLinkedListCycleEntry {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null && fast != slow) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // if no cycle, return null 
        if (fast == null || fast.next == null) {
            return null;
        }
        // search for entry 
        ListNode newSlow = head;
        while (slow.next != newSlow) {
            newSlow = newSlow.next;
            slow = slow.next;
        }
        return newSlow;
    }
}
