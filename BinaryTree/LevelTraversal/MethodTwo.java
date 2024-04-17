package BinaryTree.LevelTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class MethodTwo {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // edge case 
        if (root == null) {
            return res;
        }
        // bfs travsersal 
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);

        List<Integer> curLevel = new ArrayList<>();
        // iterate over queue 
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            // if curNode is null, finished one level 
            if (curNode == null) {
                if (!queue.isEmpty()){
                    queue.offer(null);
                }
                res.add(curLevel);
                curLevel = new ArrayList<>();
                continue;
            }

            curLevel.add(curNode.val);
            if (curNode.left != null) {
                queue.offer(curNode.left);
            } 
            if (curNode.right != null) {
                queue.offer(curNode.right);
            }    
        }

        return res;
    }
}
