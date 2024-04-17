package BinaryTree;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Subtree {
    public boolean isSubtree(TreeNode t1, TreeNode t2) {
        // empty tree is subtree of any tree 
        if (t2 == null) {
            return true;
        }
        // a tree is not a subtree of an empty tree 
        if (t1 == null) {
            return false;
        }

        // check if they are same tree 
        if (checkTree(t1, t2)) {
            return true;
        }
        // t2 is either a left subtree of a right subtree of t1 
        return isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
    }

    private boolean checkTree(TreeNode t1, TreeNode t2) {
        // if both exceed leave at the same time
        if (t1 == null && t2 == null) {
            return true;
        }
        // if one is null and the other is not
        if (t1 == null || t2 == null) {
            return false;
        }
        // if they have different value 
        if (t1.val != t2.val) {
            return false;
        }

        return checkTree(t1.left, t2.left) && checkTree(t1.right, t2.right);

        
    }
}
