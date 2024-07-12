package DP.Sequntial;
// Time: O(n*k)
// Space: O(n*k)
public class BestTimeToBuyAndSellStock5 {
    // 两种情况：
    // 1： k >= 股票总天数 / 2， 则可以理解为每天都可以操作也不会超过k transactions 
    // 2: k < 股票总天数， 就需要dp操作
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        // 第一种情况
        if (k > n / 2) {
            return tradeEveryday(prices); // greedy 
        }
        // 第二种情况
        int phases = 2 * k + 1;
        int[][] dp = new int[n + 1][phases + 1];
        for (int c = 2; c < phases + 1; c++) {
            dp[0][c] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < n + 1; i++) {
            // not holding stocks 
            for (int j = 1; j < phases + 1; j += 2) {
                // didn't buy the previous day, not buying today 
                dp[i][j] = dp[i - 1][j];
                // have stocks the day before, sell it now 
                if (i > 1 && j > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }

            // hold stocks 
            for (int j = 2; j < phases + 1; j += 2) {
                // didn't hold any shares the day before, buy now 
                dp[i][j] = dp[i - 1][j - 1];
                // have shares the day before, continue holding it today while counting profit 
                if (i > 1 && dp[i - 1][j] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }
            }
        }
        // for (int[] row : dp) {
        //     System.out.println(Arrays.toString(row));
        // }
        int profit = 0;
        for (int c = 1; c < phases + 1; c += 2) {
            profit = Math.max(profit, dp[n][c]);
        }
        return profit;
    }

    private int tradeEveryday(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(prices[i] - prices[i - 1], 0);
        }

        return profit;
    }
}
