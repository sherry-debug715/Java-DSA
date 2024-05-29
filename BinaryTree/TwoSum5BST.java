package BinaryTree;

import BinaryTree.ConvertBTtoList.TreeNode;

// Time: O(NlogN);
// Space: O(1);
public class TwoSum5BST {
    int[] output = {-1, -1};
    public int[] twoSum(TreeNode root, int n) {
        if (root == null) {
            return null;
        }
        exploreTree(root, root, n);
        return output;
    }
    private void exploreTree(TreeNode root, TreeNode curNode, int n) {
        if (root == null) {
            return;
        }
        int diff = n - curNode.val;
        if (findDiff(diff, root)) {
            output[0] = curNode.val;
            output[1] = diff;
            return;
        } else {
            if (curNode.left != null) {
                exploreTree(root, curNode.left, n);
            }
            if (curNode.right != null) {
                exploreTree(root, curNode.right, n);
            }
        }
    }

    private boolean findDiff(int diff, TreeNode root) {
        if (root == null) {
            return false;
        }
        if (root.val == diff) {
            return true;
        }
        if (root.val > diff) {
            if (findDiff(diff, root.left)) {
                return true;
            }
        } else {
            if (findDiff(diff, root.right)) {
                return true;
            }
        }
        return false;
    }
}
