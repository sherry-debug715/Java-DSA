package Tree;
// Lintcode problem 94: https://www.lintcode.com/problem/94/

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class MaxPathSum {
    /**
     * @param root: The root of binary tree.
     * @return: An integer
     */
    // Time: O(N) where n is the total number of nodes in the tree 
    // Space: O(H) where h is the height of tree for stack space 

    // maxPath is the path with max sum of weights of nodes 
    int maxPath = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        calculateMaxPathSum(root);
        return maxPath;
    }

    // return the max branch sum of root while updating maxPath 
    private int calculateMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Recursively get the max path sum for left and right branches
        int leftBranch = Math.max(calculateMaxPathSum(root.left), 0); // ignores negative paths 
        int rightBranch =  Math.max(calculateMaxPathSum(root.right), 0); // ignores negative paths
        // update global maxPath 
        maxPath = Math.max(maxPath, leftBranch + rightBranch + root.val);

        // return the maximum branch sum including the current node value 
        return root.val + Math.max(leftBranch, rightBranch);
    }
}
