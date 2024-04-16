package BinaryTree;

import java.util.ArrayList;
import java.util.List;
 // Definition of TreeNode:
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
 
public class PathSum {
        public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // initialize path array 
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        dfs(root, target, res, path, root.val);
        return res;
    }

    private void dfs(TreeNode root, 
                     int target, 
                     List<List<Integer>> res,
                     List<Integer> path,
                     int curSum) {
        if (root == null) return;
        // if reach leaf node, check path sum 
        if (root.left == null && root.right == null && curSum == target) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        if (root.left != null) {
            path.add(root.left.val);
            curSum += root.left.val;
            dfs(root.left, target, res, path, curSum);
            path.remove(path.size() - 1);
            curSum -= root.left.val;
        }

        if (root.right != null) {
            path.add(root.right.val);
            curSum += root.right.val;
            dfs(root.right, target, res, path, curSum);
            path.remove(path.size() - 1);
            curSum -= root.right.val;
        }

    } 
}
