package DivideAndConquer;
// Lintcode problem 900: https://www.lintcode.com/problem/900/?fromId=161&_from=collection
// Time: O(logN)
// Space: O(1)
public class ClosestBSTValue {
    public int closestValue(TreeNode root, double target) {
        // edge case 
        if (root == null) {
            return 0;
        }

        TreeNode upperBound = findUpperBound(root, target);
        TreeNode lowerBound = findLowerBound(root, target);
        // when target > maxNode, upperBound is null;
        if (upperBound == null) return lowerBound.val;
        if (lowerBound == null) return upperBound.val;
        // if none is null, calculate the diffs 
        double upperDiff = Math.abs(target - upperBound.val);
        double lowerDiff = Math.abs(target - lowerBound.val);
        if (upperDiff < lowerDiff) {
            return upperBound.val;
        }
        return lowerBound.val;
    }
// findUpperBound returns the min tree node with value >= target
    private TreeNode findUpperBound(TreeNode root, double target) {
        // when going beyond leaf nodes 
        if (root == null) {
            return null;
        }
        if (root.val < target) {
            return findUpperBound(root.right, target);
        } 
        // if root.val > target, then explore root.left to look for min 
        // root.val >= target;
        TreeNode upperBound = findUpperBound(root.left, target);
        if (upperBound != null) {
            return upperBound;
        }
        // upperBound is null when at leaf node 
        return root;
    }

    // findLowerBound returns the max tree node with value <= target 
    private TreeNode findLowerBound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        if (root.val > target) {
            return findLowerBound(root.left, target);
        }

        // if root.val < target, explore the right branch to look for node 
        // with value <= target 
        TreeNode lowerBound = findLowerBound(root.right, target);
        if (lowerBound != null) {
            return lowerBound;
        }
        return root;
    }
}
