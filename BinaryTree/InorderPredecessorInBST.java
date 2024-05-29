package BinaryTree;

import BinaryTree.ConvertBTtoList.TreeNode;

// Lintcode 915: https://www.lintcode.com/problem/915/?fromId=161&_from=collection
// Time: O(logN)
// Space: O(1)
public class InorderPredecessorInBST {
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        TreeNode prev = null;

        while (root != null) {
            // when val == root.val, the predecessor can only be on the left branch 

            if (p.val <= root.val) {
                root = root.left;
            } else {
                if (prev == null || prev.val < root.val) {
                    prev = root;
                }
                root = root.right;
            }
        }
        return prev;
    }
}
