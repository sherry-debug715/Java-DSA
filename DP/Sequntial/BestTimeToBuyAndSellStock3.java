package DP.Sequntial;
// lintcode 151
// Time: O(n)
// Space: O(n)
public class BestTimeToBuyAndSellStock3 {
    public int maxProfit(int[] prices) {
        // write your code here
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int [][]dp = new int[n + 1][5 + 1];
        
        for (int k = 1; k <= 5; k++) {
            dp[0][k] = Integer.MIN_VALUE;
        }
        dp[0][1] = 0;
        // 阶段1，3，5为手中无股票状态： dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - 1] + price(n - 1) - price(n - 2)),
        // 其中，dp[i - 1][j]表示昨天没有持有股票， dp[i - 1][j - 1] + price(n - 1) - price(n - 2)表示昨天持有股票，今天卖出清仓。
        // 阶段2，4为手中有股票状态：dp[i][j] = max(dp[i - 1][j] + price(n - 1) - price(n - 2), dp[i - 1][j - 1])
        // 其中，前者表示昨天就持有股票，继续持有并获利，后者为昨天没有持有股票，今天买入（这里不用记录买入价格，第二天会计算）。
        // dp[i][j]表示前i天（第i - 1天）结束后，在阶段j的最大获利
        for (int i = 1; i <= n; i++) {
            // 两种情况：
            // 1. 第n - 2天就在阶段5,已经卖掉: dp[n - 1][5]
            // 2. 第n - 2天还在阶段4（第二次持有股票），第n - 1天卖掉： dp[n - 1][4] + price(n - 1) - price(n - 2)
            // 手中未持有股票, 阶段1，3，5
            for (int j = 1; j <= 5; j += 2) {
                // 前一天也未持有, 先记录利润
                dp[i][j] = dp[i - 1][j]; 
                // j需要 > 1因为卖出是第二阶段过渡到第三阶段才发生的
                if (j > 1 && i > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) { // dp[i - 1][j - 1] == Integer.MIN_VALUE代表前一天也未持有
                    //前一天持有，今天卖了获利。
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }
            // 手中持有股票
            for (int j = 2; j <= 5; j += 2) {
                //前一天未持有，今天买进
                dp[i][j] = dp[i - 1][j - 1];
                if (i > 1 && dp[i - 1][j] != Integer.MIN_VALUE) {
                    //前一天持有了，计算今天的利润
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }
            }
        }
        
        int res = 0;
        // 最大获利为在清仓状态下，即阶段1，3，5的最大获利
        for (int j = 1; j <= 5; j += 2) {
            res = Math.max(res, dp[n][j]);
        }
        return res;
    }
}
