package BinaryTree;
// Lintcode 482: https://www.lintcode.com/problem/482/
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

// BFS
class Solution {
    /**
     * @param root: the root of the binary tree
     * @param level: the depth of the target level
     * @return: An integer
     */
    public int levelSum(TreeNode root, int level) {
        if (root == null) {
            return 0;
        }
        int curLevel = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int curSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                curSum += curNode.val;
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            if (curLevel == level) {
                return curSum;
            }
            curLevel += 1;
        }
        return 0;
    }
}
