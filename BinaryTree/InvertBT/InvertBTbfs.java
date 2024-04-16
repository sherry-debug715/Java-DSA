package BinaryTree.InvertBT;

import java.util.LinkedList;
import java.util.Queue;
// Time: O(N)
// Space: O(max nodes on a level)
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class InvertBTbfs {
    public void invertBinaryTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            TreeNode left = null;
            TreeNode right = null;
            if (curNode.left != null) {
                left = curNode.left;
                queue.offer(left);
            }
            if (curNode.right != null) {
                right = curNode.right;
                queue.offer(right);
            }
            // swap 
            curNode.left = right;
            curNode.right = left;
        }
    }
}
