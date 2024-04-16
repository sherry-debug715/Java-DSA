package BinaryTree;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class FindLeaves {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // edge case
        if (root == null) {
            return res;
        }
        dfs(root, res);
        return res;
    }

    private int dfs(TreeNode node, List<List<Integer>> res) {
        // exist
        if (node == null) {
            // level below leaf nodes is -1
            return -1;
        }

        int left = dfs(node.left, res);
        int right = dfs(node.right, res);
        int curLevel = 1 + Math.max(left, right);
        // add leaf nodes to res 
        addToRes(res, curLevel, node.val);
        return curLevel;
    }

    private void addToRes(List<List<Integer>> res,
                     int curLevel,
                     int val) {
        if (curLevel == res.size()) {
            res.add(new ArrayList<Integer>());
        }
        res.get(curLevel).add(val);
    } 
}
