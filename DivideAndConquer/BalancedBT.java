package DivideAndConquer;
// Lintcode 93: https://www.lintcode.com/problem/93/?fromId=161&_from=collection
public class BalancedBT {
    public class Solution {
        /**
         * @param root: The root of binary tree.
         * @return: True if this Binary tree is Balanced, or false.
         */
        boolean output = true;
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
    
            exploreTree(root);
            return output;
        }
    
        private int exploreTree(TreeNode root) {
            if (root == null) {
                return 0;
            }
    
            int left = exploreTree(root.left);
            int right = exploreTree(root.right);
    
            int curLevel = Math.max(left, right) + 1;
            if (Math.abs(right - left) > 1) {
                output = false;
                return curLevel;
            }
            return curLevel;
        }
}
