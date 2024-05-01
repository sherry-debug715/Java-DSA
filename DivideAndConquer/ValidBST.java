package DivideAndConquer;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
// Time: O(N)
// Space: O(1)
public class ValidBST {
    TreeNode lastNode = null;
    boolean isValid = true;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        exploreTree(root);
        return isValid;
    }

    private void exploreTree(TreeNode root) {
        if (root == null) {
            return;
        }
        exploreTree(root.left);
        if (lastNode != null && lastNode.val >= root.val) {
            isValid = false;
            return;
        }
        lastNode = root;
        exploreTree(root.right);
    }
}
