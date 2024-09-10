package LinkedList;
// Leetcode 2807
// Time: O(Nlog small)
// Space: O(log small) for the stack space
public class InsertGreatestCommonDivisorsInLinkedList {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode curr = head;
        ListNode next = head.next;
        while (next != null) { // O(N log(small))
            int small = Math.min(curr.val, next.val);
            int large = Math.max(curr.val, next.val);
            int gcd = getGcd(small, large);
            ListNode newNode = new ListNode(gcd);
            curr.next = newNode;
            newNode.next = next;
            curr = next;
            next = curr.next;
        }

        return head;
    }

    private int getGcd(int small, int large) { // O(log(small))
        if (small == 0) {
            return large;
        }

        return getGcd(large % small, small);
    }
}
