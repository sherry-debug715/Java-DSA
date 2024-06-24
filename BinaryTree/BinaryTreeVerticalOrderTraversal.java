package BinaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// Lintcode 651
public class BinaryTreeVerticalOrderTraversal {
     // Time: O(N)
     // Space: O(N)
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        if (root == null) {
            return output;
        }
        // queue is to hold node of each level 
        Queue<TreeNode> queue = new LinkedList<>();
        // queueIdx[i] is the column idx of queue[i], for example: if root colu
        mn 
        // is 0, its left child column == -1, right child column == 1;  
        Queue<Integer> queueIdx = new LinkedList<>();
        queue.offer(root);
        queueIdx.offer(0);
        Map<Integer, List<Integer>> levelMap = new HashMap<>();
        // bfs 
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int col = queueIdx.poll();

            levelMap.putIfAbsent(col, new ArrayList<Integer>());
            levelMap.get(col).add(curr.val);
            // explore children 
            if (curr.left != null) {
                queue.offer(curr.left);
                queueIdx.offer(col - 1);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                queueIdx.offer(col + 1);
            }
        }
        // end while 
        for (int i = Collections.min(levelMap.keySet()); i <= Collections.max(levelMap.keySet()); i++) {
            output.add(levelMap.get(i));
        }
        return output;
    }
}
