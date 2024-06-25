package Tree;

import java.util.ArrayList;
import java.util.List;

// lintcode 262
public class HeirTree {
    int val;
    List<MyTreeNode> children;
    boolean removed;

    MyTreeNode(int _val) {
        val = _val;
        children = new ArrayList<MyTreeNode>();
        removed = false;
    }
    
    /**
     * @param root: the root treenode
     * @return: get the traverse of the treenode
     */
    public ArrayList<Integer> traverse(MyTreeNode root) { // O(N), N = the number of nodes in the tree 
        ArrayList<Integer> heirTree = new ArrayList<>();
        dfs(root, heirTree);
        return heirTree;
    }
    
    private void dfs(MyTreeNode root,  List<Integer> heirTree) {
        if (!root.removed) {
            heirTree.add(root.val);
        }
        for (MyTreeNode c : root.children) {
            dfs(c, heirTree);  
        }
    }

    /**
     * @param root: the node where added
     * @param value: the added node's value
     * @return: add a node, return the node
     */
    public MyTreeNode addNode(MyTreeNode root, int value) { // O(1)
        if (root == null) {
            return null;
        }
        MyTreeNode newNode = new MyTreeNode(value);
        root.children.add(newNode);
        return newNode;
    }

    /**
     * @param root: the deleted node
     * @return: nothing
     */
    public void deleteNode(MyTreeNode root) { // O(1)
        root.removed = true;
    }
}
