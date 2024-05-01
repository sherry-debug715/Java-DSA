package DivideAndConquer;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
// Time: O(logN)
// Space: O(N)
public class SearchRangeInBST {
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> output = new ArrayList<>();
        exploreTree(root, k1, k2, output);
        return output;
    }

    private void exploreTree(TreeNode root, int k1, int k2, List<Integer> output) {
        if (root == null) {
            return;
        }
        if (root.val < k1) {
            exploreTree(root.right, k1, k2, output);
        } else if (root.val > k2) {
            exploreTree(root.left, k1, k2, output);
        } else {
            exploreTree(root.left, k1, k2, output);
            output.add(root.val);
            exploreTree(root.right, k1, k2, output);

        }

    }
}
