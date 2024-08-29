package Tree;

import java.util.List;

import org.w3c.dom.Node;

// leetcode 590
// Time: O(n), n == number of tree nodes
// Space: O(H), height of the tree
public class NaryTreePostorderTraversal {
    List<Integer> res = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return res;
        }
        dfs(root);
        return res;
    }

    private void dfs(Node root) {
        if (root == null) {
            return;
        }

        if (root.children == null) {
            dfs(null);
        }

        for (Node node : root.children) {
            dfs(node);
        }
        res.add(root.val);
    }
}
