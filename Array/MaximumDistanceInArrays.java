package Array;

import java.util.List;

// Leetcode 624
// Time: O(n)
// Space: O(1)
public class MaximumDistanceInArrays {
    public int maxDistance(List<List<Integer>> arrays) {
        int res = 0;
        // min is the min number from the i - 1 th subarray of arrays 
        // max is the max number from the i - 1 th subarray of arrays
        int min = arrays.get(0).get(0);
        int firstSubArrLen = arrays.get(0).size();
        int max = arrays.get(0).get(firstSubArrLen - 1);
        int n = arrays.size();

        for (int i = 1; i < n; i++) { // O(n)
            int curSubArrLen = arrays.get(i).size();
            int curMin = arrays.get(i).get(0);
            int curMax = arrays.get(i).get(curSubArrLen - 1);
            // compare the dist between largest num from i - 1 th subarray and current min num
            // and current max num to the min num from i - 1 th subarray
            res = Math.max(res, Math.max(Math.abs(curMin - max), Math.abs(curMax - min)));
            min = Math.min(min, curMin);
            max = Math.max(max, curMax);
        }
        return res;
    }
}
