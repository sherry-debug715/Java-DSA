package Stack.MonotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class FinalDiscountedPrice {
    public int[] finalDiscountedPrice(int[] prices) {
        int[] output = Arrays.copyOf(prices, prices.length);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < prices.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            int curPrice = prices[i];
            while (!stack.isEmpty() && prices[stack.peek()] >= curPrice) {
                output[stack.pop()] -= curPrice;
            }
            stack.push(i);
        }
        return output;
    }
}
