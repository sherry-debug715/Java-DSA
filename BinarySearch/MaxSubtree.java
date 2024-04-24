package BinarySearch;
class TreeNode {
    int val;
    TreeNode left, right;
    public TreeNode(int _val) {
        val = _val;
        this.left = this.right = null;
    }
}

class MaxTracker {
    int curSum;
    int curMax;
    TreeNode maxNode;
    public MaxTracker(int _curSum, int _curMax, TreeNode _maxNode) {
        curSum = _curSum;
        curMax = _curMax;
        maxNode = _maxNode;
    }
}
// time: O(N)
// Space: O(Height)
public class MaxSubtree {
    public TreeNode findSubtree(TreeNode root) {
        if (root == null) {
            return null;
        }

        MaxTracker maxSub = _findSubtree(root);
        return maxSub.maxNode;
    }

    private MaxTracker _findSubtree(TreeNode root) {
        if (root == null) {
            return new MaxTracker(0, Integer.MIN_VALUE, null);
        }

        MaxTracker leftSubs = _findSubtree(root.left);
        MaxTracker rightSubs = _findSubtree(root.right);
        int curSum = root.val + leftSubs.curSum + rightSubs.curSum;
        int curMax = Math.max(curSum, Math.max(leftSubs.curMax, rightSubs.curMax));
        if (curMax == curSum) {
            return new MaxTracker(curSum, curSum, root);
        }
        if (curMax == leftSubs.curMax) {
            return new MaxTracker(curSum, leftSubs.curMax, leftSubs.maxNode);
        }
        return new MaxTracker(curSum, rightSubs.curMax, rightSubs.maxNode);
    }
}
