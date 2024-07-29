package Tree;

import java.util.Stack;

// Lintcode 1534
// Time: O(N)
// Space: O(1)
public class ConvertBSTintoSortedDoublyLinkedList {
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode dummy = new TreeNode(-1);
        TreeNode prev = dummy;
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            // Link the previous node (prev) with the current node (curr)
            prev.right = curr;
            curr.left = prev;
            // Move the previous pointer to the current node
            prev = curr;
            // Move to the right subtree
            curr = curr.right;
        }
        TreeNode head = dummy.right;
        TreeNode tail = prev;
        head.left = tail;
        tail.right = head;
        return head;
    }
}
