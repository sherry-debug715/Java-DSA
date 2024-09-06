package LinkedList;
// Leetcode 3217
// Time O(N)
// Space O(N)
/*
 Explain the use of boolean array instead of HashSet: boolean array provides a more efficient lookup mechanism compared to a HashSet, reducing the overall runtime due to better cache usage, fewer memory allocations, and quicker lookups. Although the lookup time for checking whether a value exists in the set is O(1) on average, but the actual cost involves managing the underlying hash table, handling hash collisions, and dealing with memory overhead.
 */
public class DeleteNodesFromLinkedListPresentInArray {
    public ListNode modifiedList(int[] nums, ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        // find the max value from nums array and use it as array size
        int max = 0;
        for (int n : nums) {
            max = Math.max(max, n);
        }
        boolean[] deleteNums = new boolean[max + 1];
        for (int n : nums) {
            deleteNums[n] = true;
        }

        while (head != null) {
            while (head != null && head.val < deleteNums.length && deleteNums[head.val]) {
                prev.next = head.next;
                head = head.next;
            }
            prev = prev.next;
            if (head != null) {
                head = head.next;
            }
        }

        return dummy.next;
    }
}
