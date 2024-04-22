package Sort.SortList;

class ListNode {
    int val;
    ListNode next;
    ListNode(int _val) {
        val = _val;
        next = null;
    }
}

public class QuickSort {
    public ListNode sortList(ListNode head) {
        // edge case and exit case for recursion.
        if (head == null || head.next == null) {
            return head;
        }
        // create left, mid and right pointers 
        ListNode leftDummy = new ListNode(0), leftTail = leftDummy;
        ListNode midDummy = new ListNode(0), midTail = midDummy;
        ListNode rightDummy = new ListNode(0), rightTail = rightDummy;
        // find middle node 
        ListNode midNode = findMid(head);
        // traverse through linked list to divide the list into left, mid, and right
        // portions, left is a list with node value < than mid node, mid is a list 
        // with the same value as mid nodes, right is a list with node value > mid node.
        while (head != null) {
            if (head.val < midNode.val) {
                leftTail.next = head;
                leftTail = leftTail.next;
            } else if (head.val > midNode.val) {
                rightTail.next = head;
                rightTail = rightTail.next;
            } else {
                midTail.next = head;
                midTail = midTail.next;
            }
            head = head.next;
        }
        // break the old references of the next pointers of all tails. 
        leftTail.next = null;
        rightTail.next = null;
        midTail.next = null;
        // divide  
        ListNode left = sortList(leftDummy.next);
        ListNode right = sortList(rightDummy.next);
        // conquer 
        ListNode newHead = merge(left, midDummy.next, right);
        return newHead;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head, fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode left, ListNode mid, ListNode right) {
        ListNode dummy = new ListNode(0), tail = dummy;
        tail.next = left; tail = findTail(tail);
        tail.next = mid; tail = findTail(tail);
        tail.next = right; tail = findTail(tail);
        return dummy.next;
    }

    private ListNode findTail(ListNode tail) {
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
        return tail;
    }
}
