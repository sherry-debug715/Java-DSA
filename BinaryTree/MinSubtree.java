package BinaryTree;

import BinaryTree.ConvertBTtoList.TreeNode;

public class MinSubtree {
    class Record {
        int curSum;
        int minSum;
        TreeNode minNode;
        public Record(int _curSum, int _minSum, TreeNode _minNode) {
            curSum = _curSum;
            minSum = _minSum;
            minNode = _minNode;
        }
    }

    public TreeNode findSubtree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Record result = exploreTree(root);
        return result.minNode;
    }

    private Record exploreTree(TreeNode root) {
        if (root == null) {
            return new Record(0, Integer.MAX_VALUE, null);
        }

        Record left = exploreTree(root.left);
        Record right = exploreTree(root.right);

        int curSum = root.val + left.curSum + right.curSum;
        int minSum = Math.min(curSum, Math.min(left.minSum, right.minSum));
        if (curSum == minSum) {
            return new Record(curSum, curSum, root);
        }
        if (left.minSum == minSum) {
            return new Record(curSum, minSum, left.minNode);
        }
        return new Record(curSum, minSum, right.minNode);
    }
}
