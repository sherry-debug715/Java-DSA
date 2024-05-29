package BinaryTree;

import BinaryTree.ConvertBTtoList.TreeNode;

// lintcode 448: https://www.lintcode.com/problem/448/?fromId=161&_from=collection
// O(logN) Time
// O(logH) Space
public class InorderSuccessorInBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        // if root is <= p.val, the next successor should be either on 
        // root.right branch or the parent node of root if root.right == null 
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }
        TreeNode left = inorderSuccessor(root.left, p);
        if (left == null) {
            return root;
        }
        return left;
    }
}

// method 2 
class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }

        TreeNode successor = null;
        while (root != null) {
            if (root.val <= p.val) {
                root = root.right;
            } else {
                if (successor == null || root.val > p.val) {
                    successor = root;
                }
                root = root.left;
            }
        }
        return successor;
    }
}
