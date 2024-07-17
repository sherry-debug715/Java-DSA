package Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Leetcode 1110
// Time: O(N)
// Space: O(H)
public class DeleteNodesAndReturnForest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> output = new ArrayList<>();
        if (root == null) {
            return output;
        }

        Set<Integer> toDelete = new HashSet<>();
        for (int n : to_delete) {
            toDelete.add(n);
        }

        // Handle the root separately
        if (!toDelete.contains(root.val)) {
            output.add(root);
        }

        trimWoods(toDelete, root, null, false, output);
        return output;
    }

    private void trimWoods(Set<Integer> toDelete, 
                          TreeNode node, 
                          TreeNode prevNode,
                          boolean isLeftNode,
                          List<TreeNode> output) {
        if (node == null) {
            return;
        }

        boolean isDeleted = toDelete.contains(node.val);
        if (isDeleted) {
            if (node.left != null && !toDelete.contains(node.left.val)) {
                output.add(node.left);
            }
            if (node.right != null && !toDelete.contains(node.right.val)) {
                output.add(node.right);
            }
        }

        trimWoods(toDelete, node.left, node, true, output);
        trimWoods(toDelete, node.right, node, false, output);

        if (isDeleted) {
            if (prevNode != null) {
                if (isLeftNode) {
                    prevNode.left = null;
                } else {
                    prevNode.right = null;
                }
            }
        }
    }
}
