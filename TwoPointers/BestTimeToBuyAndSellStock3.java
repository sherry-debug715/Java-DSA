package TwoPointers;
// lintcode 151 
// Time: O(n^2)
// Space: O(1)
public class BestTimeToBuyAndSellStock3 {
    // 隔板法 
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length; 
        int output = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // ranges are [0, i), [i, n)
            int leftProfit = getProfit(0, i, prices);
            int rightProfit = getProfit(i, n, prices);
            output = Math.max(output, leftProfit + rightProfit);
        }
        return output;
    }

    private int getProfit(int left, int right, int[] prices) {
        int maxProfit = 0;
        int minP = prices[left];
        for (int i = left; i < right; i++) {
            minP = Math.min(minP, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minP);
        }
        return maxProfit;
    }
}
