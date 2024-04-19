package Sort;
// Lintcode problem 173: https://www.lintcode.com/problem/173/

//  InsertionSort: 0 based

//  i = 1
//  while i < arr.length:
//     j = i 
//     while j > 0 and arr[j] > arr[j - 1]:
//         swap arr[j] and arr[j-1];
//         j = j - 1;
//     i += 1 

// return arr 

// basically, whenever j is swapped with j - 1, j will need to go backward until arr[j - 1] < arr[j]

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
 }
// Time: O(N^2) on average, best case is when linked list is already sorted, then O(N) time 
// Space: O(1)
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        // dummy will point at head.prev 
        ListNode dummy = new ListNode(-100);

        while (head != null) {
            // curNode will always start at dummy on every iteration.
            ListNode curNode = dummy;
            // looking for a position where curNode.val < head.val < curNode.next.val 
            while (curNode.next != null && curNode.next.val < head.val) {
                curNode = curNode.next; 
            }
            // reference head.next before moving next pointer of head 
            ListNode temp = head.next;
            head.next = curNode.next;
            curNode.next = head;
            head = temp;
        }

        return dummy.next;
    }
}
