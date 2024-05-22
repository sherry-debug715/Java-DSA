package BinaryTree;
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
        if (right != null) {
            return right;
        }
        if (left != null) {
            return left;
        }
        return root;
    }
}
