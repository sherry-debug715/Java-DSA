package LinkedList.CopyListWithRandomPointer;
class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
};

public class ConstantSpace {
    public RandomListNode copyRandomList(RandomListNode head) {
        // O(1) space 3 steps 
        // 1. 1 -> 2 -> 3 -> null into 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> null
        // 2, connect the random pointer to new references 
        // 3. connect next pointer 

        // edge case 
        if (head == null) {
            return null;
        }
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode newNode = new RandomListNode(cur.label);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }

        connectRandom(head);
        RandomListNode dummy1 = new RandomListNode(-1), cur1 = dummy1;
        RandomListNode dummy2 = new RandomListNode(-1), cur2 = dummy2;
        dummy1.next = head;
        dummy2.next = head.next;
        cur1 = dummy1.next;
        cur2 = dummy2.next;
        connectNext(cur1, cur2);
        return dummy2.next;
    }

    private void connectRandom(RandomListNode node) {
        while (node != null) {
            node.next.random = node.random != null ? node.random.next : null;
            node = node.next.next;
        }
    }

    private void connectNext(RandomListNode head1, RandomListNode head2) {
        while (head1.next != null && head2.next != null) {
            head1.next = head1.next.next;
            head2.next = head2.next.next;
            head1 = head1.next;
            head2 = head2.next;
        }
        // break out of while loop, 1 -> 1' -> null 
        head1.next = head2.next; 
    }
}
