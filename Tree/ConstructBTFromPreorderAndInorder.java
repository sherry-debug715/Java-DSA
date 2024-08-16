package Tree;
// Leetcode 105
public class ConstructBTFromPreorderAndInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) {
            return null;
        }
        int n = preorder.length;
        return exploreTree(0, n - 1, preorder, 0, n - 1, inorder);
    }

    private TreeNode exploreTree(int preStart, int preEnd, int[] preorder,  int inStart, int inEnd, int[] inorder) {
        if (inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        // find root position in inorder 
        int rootIdx = findIndex(root.val, inorder, inStart, inEnd);
        // left side of rootIdx from inorder is the left branch of root, right side is right branch 
        int leftSize = rootIdx - inStart;

        root.left = exploreTree(preStart + 1, preStart + leftSize, preorder, inStart, rootIdx - 1, inorder);
        root.right = exploreTree(preStart + leftSize + 1, preEnd, preorder, rootIdx + 1, inEnd, inorder);
        return root;
    }

    private int findIndex(int val, int[] inorder, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }
}
