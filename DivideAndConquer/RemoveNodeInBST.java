package DivideAndConquer;

public class RemoveNodeInBST {
    public TreeNode removeNode(TreeNode root, int value) {
        // edge case 
        if (root == null) {
            return null;
        }
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        // locate the parent of the node with value 
        TreeNode parent = locateParent(root, dummy, value);
        TreeNode nodeToRemove;
        if (parent.left != null && parent.left.val == value) {
            nodeToRemove = parent.left;
        } else if (parent.right != null && parent.right.val == value) {
            nodeToRemove = parent.right;
        } else {
            // value doesn't exist in tree;
            return dummy.left;
        }

        deleteNode(nodeToRemove, parent);
        return dummy.left;
    }

    private void deleteNode(TreeNode nodeToRemove, TreeNode parent) {
        // since the replacement node is the next node of nodeToRemove in an 
        // inorder traverse, if nodeToRemove doesn't have right children, the replacement node or null 
        // is nodeToRemove.left or right 
        if (nodeToRemove.right == null) {
            if (parent.left == nodeToRemove) {
                parent.left = nodeToRemove.left;
            } else {
                parent.right = nodeToRemove.left;
            }
        } else {
            // find the left most node on the right branch 
            TreeNode temp = nodeToRemove.right;
            TreeNode prev = nodeToRemove; 
            while (temp.left != null) {
                prev = temp;
                temp = temp.left;
            }
            // break the relationship between prev and temp 
            if (prev.right == temp) {
                // temp.left == null, so can only set it to temp.right 
                prev.right = temp.right;
            } else if (prev.left == temp) {
                prev.left = temp.right;
            }

            // ready to use temp to replace nodeToRemove 
            // check which branch is nodeToRemove on 
            if (parent.left == nodeToRemove) {
                parent.left = temp;
            } else if (parent.right == nodeToRemove) {
                parent.right = temp;
            }
            // connect temp with the rest of the tree 
            temp.left = nodeToRemove.left;
            temp.right = nodeToRemove.right;
        }

    }

    private TreeNode locateParent(TreeNode root, TreeNode prev, int value) {
        if (root == null) {
            return prev;
        }

        if (root.val == value) {
            return prev;
        }
        if (root.val < value) {
            return locateParent(root.right, root, value);
        }
        return locateParent(root.left, root, value);
    }
}
