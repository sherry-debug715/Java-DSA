package PriorityQueue;

import java.util.List;
import java.util.Queue;

// lintcode 1274 
// Time: O(klogk)
// Space: O(k)
public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<int[]> minHeap = new PriorityQueue<>(k, (a, b) -> {
            int sum1 = nums1[a[0]] + nums2[a[1]];
            int sum2 = nums1[b[0]] + nums2[b[1]];
            //if the sum of the pairs is the same, the pairs should be ordered by the first number in the pair. 
            if (sum1 == sum2) {
                return Integer.compare(nums1[a[0]], nums1[b[0]]);
            }
            return Integer.compare(sum1, sum2);
            }
        );
        int n = nums1.length, m = nums2.length;
        // Only need to consider at most the first k elements from nums1 since we want the k smallest pairs
        for (int i = 0; i < Math.min(k, n); i++) {
            minHeap.offer(new int[]{i, 0});
        }

        while (k-- >0 && !minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            List<Integer> pair = new ArrayList<>();
            pair.add(nums1[curr[0]]);
            pair.add(nums2[curr[1]]);
            res.add(pair);
            // If there's another element in nums2 for the current nums1 element
            if (curr[1] + 1 < m) {
                minHeap.offer(new int[]{curr[0], curr[1] + 1});
            }
        }
        return res;

    }
}
