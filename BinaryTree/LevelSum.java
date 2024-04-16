package BinaryTree;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class LevelSum {
    private int sum = 0;
    public int levelSum(TreeNode root, int level) {
        if (root == null) return 0;
        dfs(root, level, 1);
        return sum;
    }

    private void dfs(TreeNode node, 
                    int level, 
                    int curLevel) {
        if (node == null) return;

        if (level == curLevel) {
            sum += node.val;
            return;
        }

        dfs(node.left, level, curLevel + 1);
        dfs(node.right, level, curLevel + 1);
        
    }
}
