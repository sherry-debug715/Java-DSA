package LinkedList;
// Leetcode 1367
// Time: O(n)
// Space: O(1)
public class LinkedListInBT {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }

        if (dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right)) {
            return true;
        }
        return false;
    }

    private boolean dfs(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root.val != head.val) {
            return false;
        }
        boolean left = dfs(head.next, root.left);
        boolean right = dfs(head.next, root.right);
        return left || right;
    }
}
