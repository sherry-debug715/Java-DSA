package BinaryTree.ValidateBST;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class ValidateBSTOne {
    public boolean isValidBST(TreeNode root) {
        return _isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    private boolean _isValidBST(TreeNode root, long maxVal, long minVal) {
        // empty tree and tree with one node are valid bst.
        if (root == null) return true;

        if (root.val >= maxVal || root.val <= minVal) {
            return false;
        }

        return _isValidBST(root.left, root.val, minVal) && _isValidBST(root.right, maxVal, root.val);
    }
}
