package Tree;

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
// Time: O(N)
// Space: O(H)
public class TreePaths {
        public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        List<String> curPath = new ArrayList<>();
        curPath.add(Integer.toString(root.val));
        dfs(root, res, curPath);
        return res; 
    }

    private void dfs(TreeNode node,
                     List<String> res,
                     List<String> curPath) {
        
        if (node == null) return;
        // add curPath to res when reaching leaf node 
        if (node.left == null && node.right == null) {
            res.add(String.join("->", curPath));
            return;
        }

        if (node.left != null) {
            curPath.add(Integer.toString(node.left.val));
            dfs(node.left, res, curPath);
            curPath.remove(curPath.size() - 1);
        }

        if (node.right != null) {
            curPath.add(Integer.toString(node.right.val));
            dfs(node.right, res, curPath);
            curPath.remove(curPath.size() - 1);
        }
    
    
    }
}
