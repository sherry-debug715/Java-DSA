package DP.Coordinates;

import java.util.Arrays;

// Lintcode 669
public class CoinChange {
    // logic:
    // to form amount with coins, assuming we need to form 11 dollars, and we have 
    // 1, 2, 5 coins.
    // the minimum coins we use is: min((11 - 1), (11 - 2), (11 - 5)) + 1
    // Time: O(amount * coins.length)
    // Space: O(amount)
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int max = amount + 1;
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) { // i is the amount 
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // if money can't be made up then return -1
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
