package DP.IntervalDP.StoneGame;
// Leetcode 877
// Time: O(N)
// Space: O(N)
public class VersionOne {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n / 2 + 1][2];
        int sum = 0;
        for (int p : piles) { // O(N)
            sum += p;
        }

        int leftIdx = 0, rightIdx = n - 1;
        for (int i = 1; i < n / 2 + 1; i++) { // O(N / 2)
            dp[i][0] = dp[i - 1][0] + piles[leftIdx];
            dp[i][1] = dp[i - 1][1] + piles[rightIdx];
            leftIdx += 2;
            rightIdx -= 2;
        }
        
        int largestPile = Math.max(dp[n / 2][0], dp[n / 2][1]);
        if (largestPile > sum / 2) {
            return true;
        }
        return false;
    }
}
