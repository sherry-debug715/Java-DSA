package LinkedList;
// Leetcode 725
// Time: O(N)
// Space: O(1)
public class SplitLinkedListInParts {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int totalNodes = countNodes(head);
        int part = totalNodes / k;
        int carry = totalNodes % k;
        if (part == 0) {
            part = 1;
            carry = 0;
        }

        ListNode[] res = new ListNode[k];
        int idx = 0;
        ListNode left = head, right = head;
        while (left != null && right != null) {
            for (int i = 0; i < part - 1; i++) { // 0 index
                right = right.next;
            }
            if (carry > 0) {
                right = right.next;
                carry -= 1;
            }
            res[idx++] = left;
            left = right.next;
            right.next = null;
            right = left;
        }
        for (int i = idx; i < k; i++) {
            res[idx++] = null;
        }
        return res;
    }

    private int countNodes(ListNode head) {
        int count = 0;
        while (head != null) {
            count += 1;
            head = head.next;
        }
        return count;
    }
}
