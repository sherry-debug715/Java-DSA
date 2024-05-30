package PrefixSum;
// Lintcode 149: https://www.lintcode.com/problem/149/?fromId=178&_from=collection
// Time: O(N)
// Space: O(N)
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[] buy = new int[n + 1];
        Arrays.fill(buy, Integer.MAX_VALUE / 2);
        for (int i = 1; i < n + 1; i++) {
            buy[i] = Math.min(buy[i - 1], prices[i - 1]);
        }

        int[] sell = new int[n + 1];
        Arrays.fill(sell, Integer.MIN_VALUE / 2);
        for (int i = n - 1; i >= 0; i--) {
            sell[i] = Math.max(sell[i + 1], prices[i]);
        }
        int profit = 0;
        for (int i = 0; i < n + 1; i++) {
            int buyPrice = buy[i];
            int sellPrice = sell[i];
            profit = Math.max(sellPrice - buyPrice, profit);
        }
        return profit;
    }
}
