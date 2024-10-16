package DP.Coordinates.CoinChange;
// Lintcode 740 
// Time: O(n * m), n: coins.length, m: amount - min(coins)
// Space: O(k), k: amount
public class CoinChangeTwo {
    public int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0) {
            return -1;
        }    
        int[] dp = new int[amount + 1];
        dp[0] = 1; // there is only one combinations to form 0 

        // for each c count from the value of c to amount, how many possible way 
        // to form dp[i] to avoid duplicate computation.
        for (int c : coins) {
            for (int i = c; i <= amount; i++) {
                dp[i] += dp[i - c];
            }
        }

        return dp[amount] == 0 ? -1 : dp[amount];
    }
}
