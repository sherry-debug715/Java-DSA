package Tree;
// leetcode 543
public class DiameterOfBT {
    int res = 1;
    public int diameterOfBinaryTree(TreeNode root) {
        exploreTree(root);
        return res - 1;
    }

    private int exploreTree(TreeNode root) {
        if (root == null) {
            return 0; // number of node
        }

        int left = exploreTree(root.left);
        int right = exploreTree(root.right);
        res = Math.max(res, left + right + 1);
        return 1 + Math.max(left, right);
    }
}
