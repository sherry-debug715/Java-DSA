package DivideAndConquer;
// Lintcode problem 596: https://www.lintcode.com/problem/596/?fromId=161&_from=collection
// Time: O(N)
// Space: O(1)
public class MinSubtree {
    private int minSum;
    private TreeNode minRoot;

    public TreeNode findSubtree(TreeNode root) {
        minSum = Integer.MAX_VALUE;
        minRoot = null;
        exploreTree(root);
        return minRoot;
    }

    private int exploreTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = exploreTree(root.left);
        int right = exploreTree(root.right);

        // calcute current sum 
        int curSum = left + right + root.val;

        if (minSum > curSum) {
            minSum = curSum;
            minRoot = root;
        }
        return curSum;
    }
}
