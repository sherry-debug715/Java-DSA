package TwoPointers;

import java.util.Stack;
// lintcode 3547 
// Description
// Now there is an array of integers heights, given some rectangles placed horizontally adjacent from left to right at the height of heights[i]（0≤i<heights.length） and do not overlap each other, and below on the same horizontal line, you can completely cover the rectangle with a matrix of any size, but not cover the blank part, and do not allow to cover the same part repeatedly, find the minimum number of matrices needed, so as to completely cover the given rectangle.
// Example:
// Input:
// heights = [13, 16, 14, 13]
// Output:
// 3
// Time: O(N)
// Space: O(N)
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
