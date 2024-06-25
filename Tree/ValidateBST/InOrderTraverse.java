package Tree.ValidateBST;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class InOrderTraverse {
    int lastNum = -1;
    boolean isValid = true;

    public boolean isValidBST(TreeNode root) {
        inOrderTraverse(root);
        return isValid;
    }

    private void inOrderTraverse(TreeNode node) {
        if (node == null) return; 
        // reach the left most leaf
        inOrderTraverse(node.left);
        // if lastNum is not null, and the value >= node.val, it's not valid 
        if (lastNum != -1 && lastNum >= node.val) {
            isValid = false;
            return;
        }
        // start from the node with min value 
        lastNum = node.val;
        inOrderTraverse(node.right);
    }
}
