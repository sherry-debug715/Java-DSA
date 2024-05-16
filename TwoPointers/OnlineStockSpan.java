package TwoPointers;
import java.util.Stack;

public class OnlineStockSpan {
    Stack<Integer> prices;
    Stack<Integer> spans;
    public OnlineStockSpan() {
        prices = new Stack<>();
        spans = new Stack<>();
    }
    /**
     * @param price: 
     * @return: int
     */
    public int next(int price) {
        int span = 1;
        while (!prices.isEmpty() && price >= prices.peek()) {
            prices.pop();
            span += spans.pop();
        }
        prices.push(price);
        spans.push(span);
        return span;
    }
}
