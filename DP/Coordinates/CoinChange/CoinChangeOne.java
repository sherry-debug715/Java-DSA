package DP.Coordinates.CoinChange;

import java.util.Arrays;

// Lintcode 669
public class CoinChangeOne {
    // logic:
    // to form amount with coins, assuming we need to form 11 dollars, and we have 
    // 1, 2, 5 coins.
    // the minimum coins we use is: min((11 - 1), (11 - 2), (11 - 5)) + 1
    // Time: O(amount * coins.length)
    // Space: O(amount)
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }

        // dp[i] represents the amount of coins takes to form i 
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                if (i < c) {
                    break;
                }
                // dp[i - c] represents the amount of coins take to form i - c, 
                // + 1 is to add the current coin 
                dp[i] = Math.min(dp[i], dp[i - c] + 1);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
