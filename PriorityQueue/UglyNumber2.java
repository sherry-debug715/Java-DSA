package PriorityQueue;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
// Time: O(nlogn), removing a number from minHeap is logH time where H is the height of the minHeap 
// Space: O(N) 
public class UglyNumber2 {
    public int nthUglyNumber(int n) {
        int[] prime = {2, 3, 5};
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        minHeap.add(1L);
        long curUgly = 1;

        for (int i = 0; i < n; i++) {
            // remove the smallest ugly number from heap 
            curUgly = minHeap.remove();
            for (int num : prime) {
                // generate new ugly number and add to heap 
                long newUgly = num * curUgly;
                if (!visited.contains(newUgly)) {
                    visited.add(newUgly);
                    minHeap.add(newUgly);
                }
            }
        }

        return (int)curUgly;
    }
}
