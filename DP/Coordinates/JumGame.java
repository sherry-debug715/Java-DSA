package DP.Coordinates;
// lintcode 116
// time: O(N^2)
// Space: O(n)
public class JumGame {
    public boolean canJump(int[] a) {
        int n = a.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) { // last jump is from j to i 
                if (dp[j] && j + a[j] >= i) { //j + a[j] is the farthest index frog can reach from index j.
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n - 1];
    }
}

class GreedySolution {
    /**
     * @param a: A list of integers
     * @return: A boolean
     */
    // Time: O(N)
    // Space: O(1)
    public boolean canJump(int[] a) {
        int n = a.length;
        int farthest = 0;
        for (int i = 0; i < n; i++) {
            if (i > farthest) {
                return false;
            }
            farthest = Math.max(farthest, i + a[i]);
        }
        return true;
    }
}
