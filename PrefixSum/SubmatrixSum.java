package PrefixSum;

import java.util.Map;

// Lintcode 405 
// Time: O(m ^ 3)
// Space: O(n) for storing data in hashMap
public class SubmatrixSum {
        public int[][] submatrixSum(int[][] matrix) {
        // edge case 
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][0];
        }

        int[][] res = new int[2][2];
        int m = matrix.length, n = matrix[0].length;
        for (int top = 0; top < m; top ++) {
            int[] coloumnPrefixSum = new int[n]; // default to 0
            for (int down = top; down < m; down ++) {
                Map<Integer, Integer> prefixHash = new HashMap<>(); // key: prefixSum. val: column number 
                prefixHash.put(0, -1); // prefixSum of 0 of an empty matrix doesn't exist, hence -1 
                int curPrefixSum = 0;
                for (int col = 0; col < n; col++) {
                    coloumnPrefixSum[col] += matrix[down][col];
                    curPrefixSum += coloumnPrefixSum[col];
                    if (prefixHash.containsKey(curPrefixSum)) {
                        res[0] = new int[]{top, prefixHash.get(curPrefixSum) + 1}; 
                        res[1] = new int[]{down, col};
                        return res; 
                    }
                    prefixHash.put(curPrefixSum, col);
                }
            } 
        }
        return res;
    }
}
