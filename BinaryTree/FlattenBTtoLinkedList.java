package BinaryTree;

import BinaryTree.ConvertBTtoList.TreeNode;

// Lintcode 453: https://www.lintcode.com/problem/453/?fromId=161&_from=collection
public class FlattenBTtoLinkedList {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = dfs(root.left);
        TreeNode right = dfs(root.right);

        if (left != null) {
            left.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        // make sure left or right is always referencing the last node of the fake linked list 
        // right should always take priority because right branch is placed below left branch
        if (right != null) {
            return right;
        }
        if (left != null) {
            return left;
        }
        return root;
    }
}
