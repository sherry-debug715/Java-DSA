package PriorityQueue;
// Lintcode 104: https://www.lintcode.com/problem/104/?fromId=161&_from=collection
// Time: O(NlogN)
// Space: O(N)
public class MergeKSortedLists {
    private Comparator<ListNode> compareNode = new Comparator<>() {
        public int compare(ListNode node1, ListNode node2) {
            return node1.val - node2.val;
        }
    };

    public ListNode mergeKLists(List<ListNode> lists) {  
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy; 
        Queue<ListNode> minHeap = new PriorityQueue<>(compareNode); 

        // add the first col from list to minHeap 
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        while (!minHeap.isEmpty()) {
            ListNode curMin = minHeap.remove();
            cur.next = curMin;
            cur = cur.next; 
            if (curMin.next != null) {
                minHeap.add(curMin.next);
            }
        }
        return dummy.next;
    }
}
