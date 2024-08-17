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

// Recursion 
// Time: O(n)
// Space: O(stack)
class Solution {
    int res = 0;
    int counter = 0;
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return res;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        dfs(root.left, k);
        counter += 1;
        if (counter == k) {
            res = root.val;
            return;
        }
        dfs(root.right, k);
    }
}
