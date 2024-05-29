package BinaryTree;

import BinaryTree.ConvertBTtoList.TreeNode;

// lintcode 578: https://www.lintcode.com/problem/578/?fromId=161&_from=collection
// Time: O(N)
// Space: O(1)
public class LowestCommonAncestor3 {
    class ResultType {
        boolean leftFound;
        boolean rightFound;
        TreeNode node;
        public ResultType(boolean _left, boolean _right, TreeNode _node) {
            leftFound = _left;
            rightFound = _right;
            node = _node;
        }
    }
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        ResultType res = exploreTree(root, A, B);
        if (res.leftFound && res.rightFound) {
            return res.node;
        }
        return null;
    }

    private ResultType exploreTree(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) {
            return new ResultType(false, false, null);
        }

        ResultType left = exploreTree(root.left, A, B);
        ResultType right = exploreTree(root.right, A, B);

        boolean leftFound = left.leftFound || right.leftFound || root == A;
        boolean rightFound = left.rightFound || right.rightFound || root == B;
        if (root == A || root == B) {
            return new ResultType(leftFound, rightFound, root);
        }
        if (left.node != null && right.node == null) {
            return new ResultType(leftFound, rightFound, left.node);
        } 
        if (right.node != null && left.node == null) {
            return new ResultType(leftFound, rightFound, right.node);
        } 
        if (right.node != null && left.node != null) {
            return new ResultType(leftFound, rightFound, root);
        } 
        return new ResultType(leftFound, rightFound, null);
    }
}
