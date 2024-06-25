package Tree;

import Tree.ConvertBTtoList.TreeNode;

public class SerializeAnDeserializeBT {
    List<String> tree = new ArrayList<>();
    public String serialize(TreeNode root) { // O(N) Time, O(N) Space
        if (root == null) {
            return String.join("",tree);
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                tree.add("#");
            } else{
                tree.add(Integer.toString(cur.val));
            } 
            if (cur != null) {
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return String.join("", tree);
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) { // Time: O(N) Space: O(N)
        if (tree.size() == 0) {
            return null;
        }
        List<TreeNode> treeList = new ArrayList<>();
        for (String s : tree) {
            if (s != "#") {
                treeList.add(new TreeNode(Integer.parseInt(s)));
            } else {
                treeList.add(null);
            }
        }

        // build tree 
        TreeNode root = treeList.get(0);
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        int slow = 0, fast = 1; 
        while (slow < queue.size()) {
            TreeNode curNode = queue.get(slow);
            slow += 1;
            curNode.left = treeList.get(fast);
            curNode.right = treeList.get(fast + 1);
            fast += 2;
            if (curNode.left != null) {
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
            }
        }
        return root;
    }
}
