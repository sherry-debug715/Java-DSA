package LinkedList;
// leetcode 160
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // find tail of two linked list 
        ListNode curr = headA;
        while (curr != null && curr.next != null) {
            curr = curr.next;
        }
        curr.next = headB;

        ListNode res = linkedListCycle(headA);
        curr.next = null;
        return res;
    }

    private ListNode linkedListCycle(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        
        slow = head;
        fast = fast.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
