package Tree.ConvertIntoBST;
// lintcode 106: https://www.lintcode.com/problem/106
// Time: O(N)
// Space: O(N)
public class ConvertSortedListIntoBST {
    class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int _val) {
            val = _val;
            this.left = this.right = null;
        }
    }
    
    class ListNode {
        public int val;
        ListNode next;
        ListNode(int _val) {
            val = _val;
            this.next = null;
        }
    }
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        return formTree(head, null);
    }

    private TreeNode formTree(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }
        if (head == tail) {
            return null;
        }

        ListNode mid = findMid(head, tail);
        TreeNode root = new TreeNode(mid.val);
        root.left = formTree(head, mid);
        root.right = formTree(mid.next, tail);
        return root;
    }

    private ListNode findMid(ListNode head, ListNode tail) {
        ListNode slow = head, fast = head.next;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
