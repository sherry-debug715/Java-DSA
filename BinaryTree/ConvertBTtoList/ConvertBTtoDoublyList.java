package BinaryTree.ConvertBTtoList;
// Lintcode 378: https://www.lintcode.com/problem/378/
// Time: O(N)
// Space: O(N)

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int _val) {
        val = _val;
        this.left = this.right = null;
    }
}

class DoublyListNode {
    public int val;
    DoublyListNode next, prev;
    DoublyListNode(int _val) {
        val = _val;
        this.next = null;
        this.prev = null;
    }
}
public class ConvertBTtoDoublyList {
    DoublyListNode dummy = new DoublyListNode(-1);
    DoublyListNode curNode = dummy;
    public DoublyListNode bstToDoublyList(TreeNode root) {
        exploreTree(root);
        return dummy.next;
    }

    private void exploreTree(TreeNode root) {
        if (root == null) {
            return;
        }
        exploreTree(root.left);
        DoublyListNode newNode = new DoublyListNode(root.val);
        curNode.next = newNode;
        newNode.prev = curNode;
        curNode = curNode.next;
        exploreTree(root.right);
    }
}
