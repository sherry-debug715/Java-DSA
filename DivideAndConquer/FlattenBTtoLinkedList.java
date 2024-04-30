package DivideAndConquer;
// Lintcode problem 453: https://www.lintcode.com/problem/453/description?fromId=161&_from=collection
// Time: O(N)
// Space: O(1)
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class FlattenBTtoLinkedList {
    public void flatten(TreeNode root) {
        _flatten(root);
    }

    private TreeNode _flatten(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode lastLeft = _flatten(node.left);
        TreeNode lastRight = _flatten(node.right);

        if (lastLeft != null) {
            lastLeft.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        if (lastRight != null) {
            return lastRight;
        }
        if (lastLeft != null) {
            return lastLeft;
        }
        return node;
    }
}
