package Tree.ConvertIntoBST;
// Lintcode problem 177: https://www.lintcode.com/problem/177/

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class ConvertSortedArrIntoBST {
    public TreeNode sortedArrayToBST(int[] a) {
        return formBST(a, 0, a.length - 1);
    }

    private TreeNode formBST(int[] a, int start, int end) {
        if (start > end) {
            return null; 
        }

        if (start == end) {
            return new TreeNode(a[start]);
        }

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(a[mid]);
        root.left = formBST(a, start, mid - 1);
        root.right = formBST(a, mid + 1, end);
        return root;
    }
}
