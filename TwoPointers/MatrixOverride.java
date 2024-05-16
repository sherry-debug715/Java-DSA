package TwoPointers;

import java.util.Stack;

public class MatrixOverride {
        // Stack is strictly increasing 
        public int minCount(int[] heights) {
            if (heights == null || heights.length == 0) {
                return 0;
            }
    
            int output = 0;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < heights.length + 1; i++) {
                int curH = i == heights.length ? -1 : heights[i];
                while (!stack.isEmpty() && curH < stack.peek()) {
                    stack.pop();
                    output += 1;
                }
                if (stack.isEmpty() || curH > stack.peek()) {
                    stack.push(curH);
                }
            }
            return output;
        }
}
