package Stack.MonotonicStack;

import java.util.Arrays;
import java.util.Stack;
// lintcode 1852 
// Time: every price from prices is iterated over at most once and added / removed at most once, therefore O(2n) -> O(N)
// Space: O(n)
public class FinalDiscountedPrice {
    public int[] finalDiscountedPrice(int[] prices) {
        if (prices.length == 1) {
            return prices;
        }
        int n = prices.length;
        Stack<Integer> stack = new Stack<>(); // increasing, stores indexes 
        stack.add(0);
        int[] output = Arrays.copyOf(prices, prices.length);
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && prices[i] <= prices[stack.peek()]) {
                int idx = stack.pop();
                output[idx] -= prices[i];
            }
            stack.add(i);
        }

        return output;
    }
}
