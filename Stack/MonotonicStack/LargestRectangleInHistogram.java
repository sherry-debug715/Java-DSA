package Stack.MonotonicStack;

import java.util.Stack;

// Lintcode problem 122: https://www.lintcode.com/course/101/learn/122/description?chapterId=535&sectionId=3986&ac=true
// Time: O(N). Each index is pushed to the stack once and popped from the stack once. Thus, each element undergoes a constant amount of work aside from the loop iterations. The reason for processing each element twice (once when pushed and once when popped) without leading to a quadratic time complexity is that each index is only handled a couple of times (pushed and popped), and each operation (push or pop) is O(1).

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int maxArea = -1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length + 1; i++) {
            int curHeight = i == heights.length ? -1 : heights[i];
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            // when found a right end 
            while (!stack.isEmpty() && curHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                // now stack.peek() is the index of first height <= h 
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w); 
            }
            stack.push(i);
        }
        return maxArea;
    }
}
