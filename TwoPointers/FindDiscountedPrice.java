package TwoPointers;

public class FindDiscountedPrice {
    public int[] finalDiscountedPrice(int[] prices) {
        if (prices == null || prices.length == 0) {
            return prices;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[i] <= prices[stack.peekLast()]) {
                prices[stack.pollLast()] -= prices[i];
            }
            stack.addLast(i);
        }
        return prices;
    }
}
