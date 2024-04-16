package BinaryTree;

import java.util.ArrayList;
import java.util.Collections;
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

public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean leftToRight = true;

        if (root == null) {
            return res;
        }
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> curPath = new ArrayList<>();
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                // access first node from queue and remove it then add to curPath
                TreeNode curNode = queue.poll();
                curPath.add(curNode.val);
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            
            // check if reverse is needed
            if (!leftToRight) {
                Collections.reverse(curPath);
            }

            res.add(curPath);
            leftToRight = !leftToRight;
        }
        return res;
    }
}
