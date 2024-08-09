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

        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();

        while(curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            if (k == 1) {
                return stack.pop().val;
            }
            curr = stack.pop();
            k -= 1;
            curr = curr.right;
        }
        return -1;
    }
}
