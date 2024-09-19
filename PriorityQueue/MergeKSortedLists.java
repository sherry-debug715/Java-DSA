package PriorityQueue;

import Sort.SortTheJumbledNumbers.java.PriorityQueue;

// Lintcode 104: https://www.lintcode.com/problem/104/?fromId=161&_from=collection

// Definition for ListNode.
class ListNode {
      int val;
      ListNode next;
      ListNode(int val) {
          this.next = null;
      }
  }
 
public class MergeKSortedLists {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    // Time: O(nlog(k)) where n is the total number of linked list nodes, and 
    // k is the size of lists, at any given time, there are at most k nodes in minHeap, add and removing a node from 
    // minHeap is log(k) time.
    // Space: O(k) where k is the size of lists.
    public ListNode mergeKLists(List<ListNode> lists) {  
        // use a minHeap to store nodes from the list of linked list, sort them by their value 
        Queue<ListNode> minHeap = new PriorityQueue()<>((a, b) -> {
            return a.val - b.val;
        });

        // Add the first node of each list to the heap (if it exists).
        for (ListNode node : lists) {
            if (node == null) {
                continue;
            }
            minHeap.offer(node);
        }

        // dummy node is the node that references the new linked list head prev 
        ListNode dummy = new ListNode(-1);
        // curr node is the tail pointer of the new linked list.
        ListNode curr = dummy;
        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            // add minNode as new tail 
            curr.next = minNode;
            // move tail to minNode 
            curr = curr.next;
            // if minNode is not tail node, add the next node to minHeap 
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }

        return dummy.next;
    }
}
