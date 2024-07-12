package Stack.MonotonicStack;

import java.util.Arrays;
import java.util.Stack;

// lintcode 285
// Time: O(n)
// Space: O(n)
public class TallBuilding {
    public int[] tallBuilding(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int n = arr.length;
        int[] output = new int[n];
        Arrays.fill(output, 1);

        // compute left 
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            output[i] += stack.size();
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
                stack.pop();
            }
            stack.add(i);
        }
        stack.clear();
        // compute right
        for (int i = n - 1; i >= 0; i--) {
            output[i] += stack.size();
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
                stack.pop();
            }
            stack.add(i);
        }

        return output;
    }
}
