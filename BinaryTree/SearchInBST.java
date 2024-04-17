package BinaryTree;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class SearchInBST {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        // find node 
        if (root.val == val) {
            return root;
        }

        // explore left subtree 
        if (root.val > val) {
            return searchBST(root.left, val);
        }
        // explore right subtree
        if (root.val < val) {
            return searchBST(root.right, val);
        }

        return null;
    }
}
