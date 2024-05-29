package BinaryTree.LevelTraversal;
// Lintcode 70: https://www.lintcode.com/problem/70/
public class LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Deque<List<Integer>> output = new ArrayDeque<>();
        if (root == null) {
            return new ArrayList<>(output);
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            output.addFirst(level);
        }
        return new ArrayList<>(output);
    }
}
