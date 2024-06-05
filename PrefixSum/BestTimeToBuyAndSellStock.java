package PrefixSum;
// Lintcode 149: https://www.lintcode.com/problem/149/?fromId=178&_from=collection
// Time: O(N)
// Space: O(1)
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int profit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i ++) {
            minPrice = Math.min(minPrice, prices[i]);
            profit = Math.max(profit, prices[i] - minPrice);
        }
        return profit;
    }
}
