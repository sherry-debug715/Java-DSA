package LinkedList;
// leetcode 2
// Time: O(n)
// Space: O(n)
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int val = (val1 + val2 + carry) % 10;
            carry = (val1 + val2 + carry) / 10;
            curr.next = new ListNode(val);
            curr = curr.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            curr.next = new ListNode(carry);
        }
        return dummy.next;

    }
}
