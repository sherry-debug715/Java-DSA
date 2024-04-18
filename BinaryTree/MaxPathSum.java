package BinaryTree;
// Lintcode problem 94: https://www.lintcode.com/problem/94/

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class MaxPathSum {
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        _maxPathSum(root);
        return maxSum;
    }

    private int _maxPathSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSum = _maxPathSum(node.left);
        int rightSum = _maxPathSum(node.right);
        maxSum = Math.max(maxSum, leftSum + rightSum + node.val);
        int curMax = Math.max(leftSum, rightSum) + node.val;
        return curMax > 0 ? curMax : 0;

    }
}
