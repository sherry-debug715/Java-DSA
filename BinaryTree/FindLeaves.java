package BinaryTree;

import java.util.ArrayList;
import java.util.List;
// Lintcode 650: https://www.lintcode.com/problem/650/
// Time: O(N)
// Space: O(N)
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
        List<List<Integer>> output = new ArrayList<>();
        if (root == null) {
            return output;
        }

        exploreTree(root, output);
        return output;
    }

    private int exploreTree(TreeNode root, List<List<Integer>> output) {
        if (root == null) {
            return 0;
        }

        int left = exploreTree(root.left, output); 
        int right = exploreTree(root.right, output); 
        int curLevel = 1 + Math.max(left, right);
        insertLevel(curLevel, output, root);
        return curLevel;
    }

    private void insertLevel(int level, List<List<Integer>> output, TreeNode root) {
        if (output.size() < level) {
            output.add(new ArrayList<Integer>());
        }

        output.get(level - 1).add(root.val);
    }
}
