package Bits;
// Leetcode 1310 
// Time: O(N)
// Space: O(N) 
public class XORQueriesOfASubarray {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] xorPrefix = new int[n];
        xorPrefix[0] = arr[0];

        for (int i = 1; i < n; i++) {
            xorPrefix[i] = arr[i] ^ xorPrefix[i - 1];
        }

        int[] res = new int[queries.length];
        int idx = 0;
        for (int[] q : queries) {
            int start = q[0], end = q[1];
            if (start == 0) {
                res[idx++] = xorPrefix[end];
                continue;
            }
            /*
            For example: if we have 1 ^ 3 ^ 4 = 6, to find 3 ^ 4, we can XOR the entire result with 1, because:
            (1 ^ 3 ^ 4) ^ 1 = (1 ^ 1) ^ (3 ^ 4) = 0 ^ (3 ^ 4) = 3 ^ 4
            */
            res[idx++] = xorPrefix[end] ^ xorPrefix[start - 1];
        }
        return res;
    }
}
