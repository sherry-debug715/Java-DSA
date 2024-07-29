package Tree;

import java.util.Stack;

// lintcode 902 
// Time: O(n)
// Space: O(n)
public class KthSmallestEleInBST {
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }

        TreeNode dummy = new TreeNode(-1);
        dummy.right = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(dummy);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            if (!stack.isEmpty()) {
                k -= 1;
                if (k == 0) {
                    return stack.peek().val;
                }
            }
        }
        return -1;
    }
}
