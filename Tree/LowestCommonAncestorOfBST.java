package Tree;

import BinaryTree.ConvertBTtoList.TreeNode;

// Lintcode 1311: https://www.lintcode.com/problem/1311/?fromId=161&_from=collection
// Time: O(logN)
// Space: O(1)
public class LowestCommonAncestorOfBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        while (true) {
            if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else {
                break;
            }
        }
        return root;
    }
}

// dfs 
class Solution {
    /**
     * @param root: root of the tree
     * @param p: the node p
     * @param q: the node q
     * @return: find the LCA of p and q
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (root == p || root == q) {
            return root;
        }
        if (left == null && right == null) {
            return null;
        }
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }
}
