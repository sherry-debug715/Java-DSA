package Greedy;
// Lintcode 397
public class LongestContinuousIncresingSubsequence {
    // Time: O(n)
    // Space: O(1)
    public int longestIncreasingContinuousSubsequence(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }

        int longest = 1, incre = 1, decre = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1]) {
                decre = 1;
                incre += 1;
            } else if (a[i] < a[i - 1]) {
                incre = 1;
                decre += 1;
            } else {
                incre += 1;
                decre += 1;
            }

            longest = Math.max(longest, Math.max(incre, decre));
        }
        return longest;
    }
}
