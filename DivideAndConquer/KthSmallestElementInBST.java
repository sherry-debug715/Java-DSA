package DivideAndConquer;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
// Time: O(logN)
// Space: O(left Tree Height), stack space 
public class KthSmallestElementInBST {
        /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    // basic logic in solving the problem:
    // 1. count the number of nodes in left tree and store it in variable r.
    // 2. if r + 1 == k, meaning root is the kth smallest element.
    // 3. if r + 1 > k, explore root.left.
    // 4. if r + 1 < k, explore root.right, k = k - (r + 1);
    public int kthSmallest(TreeNode root, int k) {
        // if tree is empty 
        if (root == null) {
            return 0;
        }

        int leftTreeNodes = countLeftTree(root.left) + 1;
        if (leftTreeNodes + 1 == k) {
            return root.val;
        }
        if (leftTreeNodes + 1 > k) {
            return kthSmallest(root.left, k);
        }
        return kthSmallest(root.right, k - (leftTreeNodes + 1));
    }

    private int countLeftTree(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int left = 1 + countLeftTree(root.left);
        int right = 1 + countLeftTree(root.right);
        return left + right;
    }
}
