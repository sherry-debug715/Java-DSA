package BinaryTree.BalancedTree;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class MethodOne {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        if (!isBalanced(root.left)) {
            return false;
        }
        if (!isBalanced(root.right)) {
            return false;
        }

        int left = traverse(root.left);
        int right = traverse(root.right);

        return Math.abs(left - right) <= 1;
    }

    private int traverse(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = traverse(node.left);
        int right = traverse(node.right);

        return 1 + Math.max(left, right);
    }
}
