package BinaryTree;

import BinaryTree.ConvertBTtoList.TreeNode;

// Leetcode problem 404: https://leetcode.com/problems/sum-of-left-leaves/
public class SumOfLeftLeaves {
    int leafSum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root);
        return leafSum;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            // check if the left child is a leaf node 
            if (root.left.left == null && root.left.right == null) {
                leafSum += root.left.val;
            }
            dfs(root.left);
        } 
        dfs(root.right);
    }
}
