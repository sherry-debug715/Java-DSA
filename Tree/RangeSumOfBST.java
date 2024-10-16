package Tree;
// Lintcode 1704
// Time: O(n) where n is the total nodes in tree 
// Space: O(h) where h is the total height of the tree 
public class RangeSumOfBST {
    int sum = 0;
    public int rangeSumBST(TreeNode root, int l, int r) {
        if (root == null) {
            return 0;
        }
        dfs(root, l, r);
        return sum;
    }

    private void dfs(TreeNode root, int l, int r) {
        if (root == null) {
            return;
        }
        if (root.val < l) {
            dfs(root.right, l, r);
            return;
        }
        if (root.val > r) {
            dfs(root.left, l, r);
            return;
        }
        sum += root.val;
        dfs(root.left, l, r);
        dfs(root.right, l, r);
    }
}

class IterativeSolution {
    /**
     * @param root: the root node
     * @param l: an integer
     * @param r: an integer
     * @return: the sum
     */
    public int rangeSumBST(TreeNode root, int l, int r) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                if (curr.val < l) {
                    curr = curr.right;
                } else if (curr.val > r) {
                    curr = curr.left;
                } else {
                    stack.push(curr);
                    curr = curr.left;
                }
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
                sum += curr.val;
                curr = curr.right;
            }
        }
        return sum;
    }
}
