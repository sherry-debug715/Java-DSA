package BinaryTree.InvertBT;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class InvertBTdfs {
    public void invertBinaryTree(TreeNode root) {
        dfs(root);
    }

    private TreeNode dfs(TreeNode node) {
        if (node == null) return null;

        TreeNode left = dfs(node.left);
        TreeNode right = dfs(node.right);

        node.left = right;
        node.right = left;
        return node;
    }
}
