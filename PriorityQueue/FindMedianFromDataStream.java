package PriorityQueue;

import java.util.Collections;
import java.util.Queue;

// lintcode 81 
public class FindMedianFromDataStream {
    Queue<Integer> maxHeap; // mid and mid left 
    Queue<Integer> minHeap; // mid right 
    public Solution() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void add(int val) { // O(logN)
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
            // check for inbalance 
            if (minHeap.size() + 1 < maxHeap.size()) {
                minHeap.offer(maxHeap.poll());
            }
        } else {
            minHeap.offer(val);
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
    }

    /**
     * @return: return the median of the all numbers
     */
    public int getMedian() { // O(1)
        // write your code here
        return maxHeap.peek();
    }
}
