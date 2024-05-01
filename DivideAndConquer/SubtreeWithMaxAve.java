package DivideAndConquer;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class ReturnType {
    int childCount;
    int curSum;
    float maxAve;
    TreeNode maxNode;
    public ReturnType(int _childCount, int _curSum, float _maxAve, TreeNode _maxNode) {
        childCount = _childCount;
        curSum = _curSum;
        maxAve = _maxAve;
        maxNode = _maxNode;
    }
}
// Time: O(N)
// Space: O(1)
public class SubtreeWithMaxAve {
    public TreeNode findSubtree2(TreeNode root) {
        ReturnType maxAveTree = exploreTree(root);
        return maxAveTree.maxNode;
    }

    private ReturnType exploreTree(TreeNode root) {
        if (root == null) {
            return new ReturnType(0, 0, Float.NEGATIVE_INFINITY, null);
        }

        ReturnType left = exploreTree(root.left);
        ReturnType right = exploreTree(root.right);

        // count the total number of nodes of the current subtree 
        int nodeCounts = left.childCount + right.childCount + 1;
        int totalSum = left.curSum + right.curSum + root.val;
        float curAve = (float) totalSum / nodeCounts;
        // get the current max average 
        float curMaxAve = Math.max(curAve, Math.max(left.maxAve, right.maxAve));
        if (curMaxAve == curAve) {
            return new ReturnType(nodeCounts, totalSum, curAve, root);
        }
        if (left.maxAve == curMaxAve) {
            return new ReturnType(nodeCounts, totalSum, left.maxAve, left.maxNode);
        }
        return new ReturnType(nodeCounts, totalSum, right.maxAve, right.maxNode);
    }
}
