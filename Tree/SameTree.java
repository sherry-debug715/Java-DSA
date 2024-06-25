package Tree;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int _val) {
        val = _val;
        left = right = null;
    }
}

public class SameTree {
    public boolean isIdentical(TreeNode a, TreeNode b) {
        // if both a and b are null, they equal.
        // note, the following check has to occur before the or statement.
        if (a == null && b == null) {
            return true;
        }
        // if one is null and the other is not, they differ 
        if (a == null || b == null) {
            return false;
        }
        // check if the values are equal 
        if (a.val != b.val) {
            return false;
        }

        // so far, they are the same, divide: check left branch, then right 
        return isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
    }
}
