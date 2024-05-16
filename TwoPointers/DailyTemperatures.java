package TwoPointers;

import java.util.Stack;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] output = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                output[stack.peek()] = i - stack.pop(); 
            }
            stack.push(i);
        }
        return output;
    }
}
