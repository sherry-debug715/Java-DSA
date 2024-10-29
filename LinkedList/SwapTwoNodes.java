/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
// Time: O(n)
// Space: O(1)
 public class SwapTwoNodes {
    /**
     * @param head: a ListNode
     * @param v1: An integer
     * @param v2: An integer
     * @return: a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head; 

        // locate nodes with v1 and v2 
        ListNode p1 = null, p2 = null; // p1: prev pointer of left. p2: prev pointer of right 
        boolean findV1 = false, findV2 = false;
        ListNode curr = dummy;

        while (curr.next != null) { 
            if (curr.next.val == v1) {
                p1 = curr;
                findV1 = true;
            } else if (curr.next.val == v2) {
                p2 = curr;
                findV2 = true;
            }
            if (findV1 && findV2) {
                break;
            }
            curr = curr.next;
        }
        
        // v1 or v2 doesn't exist 
        if (!findV1 || !findV2) {
            return head;
        }

        if (p2.next == p1) {
            ListNode temp = p1;
            p1 = p2;
            p2 = temp;
        }

        ListNode node1 = p1.next, node2 = p2.next;
        ListNode node2Next = node2.next;

        // swap 
        if (p1.next == p2) { // node1 is p2
            p1.next = node2;
            node2.next = p2;
            p2.next = node2Next;
        } else {
            p1.next = node2;
            node2.next = node1.next;
            p2.next = node1;
            node1.next = node2Next;
        }

        return dummy.next;
    }
}

/*
      p1
         l   
  10->8->7->6->4->3->null
  p2
      r

temp1 = 8 = r
temp2 = 7 = l
                 p2
p2.next = r.next 10->7->6->4->3->null 
                      p1
p1.next = l.next  10->8->6->4->3->null

temp1.next = p1.next 
p1.next = temp1 
7
temp2.next = p2.next 
p2.next = temp2




*/