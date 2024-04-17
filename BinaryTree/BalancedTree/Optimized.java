package BinaryTree.BalancedTree;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class ResultType {
    boolean type;
    int curHeight;
    public ResultType(boolean _type, int _curHeight) {
        type = _type;
        curHeight = _curHeight;
    }
}

public class Optimized {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        ResultType result = dfs(root);
        return result.type;
    }

    private ResultType dfs(TreeNode root) {
        if (root == null) {
            return new ResultType(true, 0);
        }

        ResultType leftResult = dfs(root.left);
        ResultType rightResult = dfs(root.right);
        int curHeight = Math.max(leftResult.curHeight, rightResult.curHeight) + 1;
        // check if height difference is greater than 1
        if (Math.abs(leftResult.curHeight - rightResult.curHeight) > 1) {
            return new ResultType(false, curHeight);
        }
        // if either side is unbalanced, return false 
        if (!leftResult.type || !rightResult.type) {
            return new ResultType(false, curHeight);
        }

        return new ResultType(true, curHeight);
    }
}
