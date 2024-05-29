package BinaryTree.ConvertBTtoList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
// Lintcode 242: https://www.lintcode.com/problem/242
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int _val) {
        val = _val;
        this.left = this.right = null;
    }
}

class ListNode {
    public int val;
    ListNode next;
    ListNode(int _val) {
        val = _val;
        this.next = null;
    }
}

public class ConvertBTtoListByDepth {
    public List<ListNode> binaryTreeToLists(TreeNode root) {
        List<ListNode> res = new ArrayList<>();
        // edge case
        if (root == null) {
            return res;
        }
        // use queue to realize bfs 
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // building list of Linked list 
        while (!queue.isEmpty()) {
            ListNode newHead = buildList(queue);
            res.add(newHead);
        }

        return res;
    }

    private ListNode buildList(Queue<TreeNode> queue) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int queueSize = queue.size();

        for (int i = 0; i < queueSize; i++) {
            TreeNode curNode = queue.poll();
            cur.next = new ListNode(curNode.val);
            cur = cur.next;
            // add to queue 
            if (curNode.left != null) {
                queue.offer(curNode.left);
            }
            if (curNode.right != null) {
                queue.offer(curNode.right);
            }
        }
        return dummy.next;
    }
}
