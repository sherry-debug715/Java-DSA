package BinaryTree.MustRemember;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;

// Lintcode 67
public class InorderIterative {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        if (root == null) {
            return output;
        }

        Stack<TreeNode> stack = new Stack<>();
        // populate stack with left most branch nodes 
        TreeNode curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        while(!stack.isEmpty()) {
            TreeNode node = stack.peek();
            output.add(node.val);
            if (node.right == null) {
            // if node has no right child, check if node is the right child of 
            // parent node, remove parent node, as it's value is already added to 
            // output 
                node = stack.pop();
                while (!stack.isEmpty() && stack.peek().right == node) {
                    node = stack.pop();
                }
            } else {
            // if node has right children, add right child and all left children of
            // this right child to stack.
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        return output;
    }
}
