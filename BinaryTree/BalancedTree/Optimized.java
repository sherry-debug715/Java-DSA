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
// method 2
class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    class Result {
        boolean isBalanced;
        int curH;
        public Result(boolean _isBalanced, int _curH) {
            isBalanced = _isBalanced;
            curH = _curH;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        Result res = exploreTree(root);
        return res.isBalanced;
    }
    private Result exploreTree(TreeNode root) {
        if (root == null) {
            return new Result(true, 0);
        }

        Result left = exploreTree(root.left);
        Result right = exploreTree(root.right);
        int curHeight = Math.max(left.curH, right.curH) + 1;
        boolean isBalanced = Math.abs(right.curH - left.curH) <= 1;
        return new Result(isBalanced && left.isBalanced && right.isBalanced, curHeight);
    }
}
