package DivideAndConquer;
// Lintcode problem 88: https://www.lintcode.com/problem/88/?fromId=161&_from=collection
public class LowestCommonAncestorOfBT {
    // 3 conditions 
     // 1. A and B both on left tree, return the one who is closer to root.
     // 2. A and B both on right tree, return the one who is closer to root.
     // 3. A and B on separate tree, return root.  
     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) {
            return null;
        }

        if (root == A || root == B) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);

        if (left != null && right != null) {
            return root;
        }
        if (right != null) {
            return right;
        }
        if (left != null) {
            return left;
        }
        return null;
    } 
}
