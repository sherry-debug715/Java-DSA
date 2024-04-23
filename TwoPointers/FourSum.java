package TwoPointers;

import java.util.Map;
// Time: O(N^2)
// Space: O(N)
public class FourSum {
    public int fourSumCount(int[] a, int[] b, int[] c, int[] d) {
        // all arrays have the same length.
        if (a.length == 0) {
            return 0;
        }
        int output = 0;
        Map<Integer, Integer> diff = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                int twoSum = a[i] + b[j];
                diff.put(-twoSum, diff.getOrDefault(-twoSum, 0) + 1);
            }
        }
        // itearate c and d, see if any of two sums exist in diff 
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < d.length; j++) {
                int twoSum = c[i] + d[j];
                output += diff.getOrDefault(twoSum, 0);
            }
        }

        return output;
    }
}
