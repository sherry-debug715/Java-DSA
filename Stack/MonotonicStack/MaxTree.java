package Stack.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;

import javax.swing.tree.TreeNode;

// lintcode 126 
// Time: O(N)
// Space: O(N)
public class MaxTree {
    public TreeNode maxTree(int[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        int n = a.length;
        TreeNode[] nodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new TreeNode(a[i]);
        }

        Deque<Integer> deque = new ArrayDeque<>(); // stores indexes, decrease order
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && a[i] > a[deque.peekLast()]) {
                int leftChildIdx = deque.removeLast();
                nodes[i].left = nodes[leftChildIdx]; 
            }
            if (!deque.isEmpty()) {
                nodes[deque.peekLast()].right = nodes[i];
            }
            deque.addLast(i);
        } 

        return nodes[deque.peekFirst()];
    }
}
