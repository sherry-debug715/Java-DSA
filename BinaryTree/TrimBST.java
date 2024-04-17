package BinaryTree;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class TrimBST {
    public TreeNode trimBST(TreeNode root, int minimum, int maximum) {
        if (root == null) {
            return null;
        }

        int curVal = root.val; 
        // qualified node 
        if (curVal >= minimum && curVal <= maximum) {
            root.left = trimBST(root.left, minimum, maximum);
            root.right = trimBST(root.right, minimum, maximum);
            return root;
        }
        // if value too small, explore it's right subbranch
        if (curVal < minimum) {
            return trimBST(root.right, minimum, maximum);
        }
        // if value too large, explore it's left subtree 
        if (curVal > maximum) {
            return trimBST(root.left, minimum, maximum);
        }

        return root;
    }
}
