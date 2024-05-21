package BFS;

public class BTKthFloorNode {
    public int kthfloorNode(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int output = 0;
        while (k > 0 && !queue.isEmpty()) {
            int size = queue.size();
            output = size;
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            k -= 1;
        }
        return output;
    }
}
