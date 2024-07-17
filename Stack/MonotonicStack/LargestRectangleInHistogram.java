package Stack.MonotonicStack;

import java.util.Stack;

// Lintcode problem 122: https://www.lintcode.com/course/101/learn/122/description?chapterId=535&sectionId=3986&ac=true
// Time: O(N). Each index is pushed to the stack once and popped from the stack once. Thus, each element undergoes a constant amount of work aside from the loop iterations. The reason for processing each element twice (once when pushed and once when popped) without leading to a quadratic time complexity is that each index is only handled a couple of times (pushed and popped), and each operation (push or pop) is O(1).

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int maxArea = 0;
        int n = heights.length;
        Stack<Integer> stack = new Stack<>(); // stack of indexes 
        for (int i = 0; i <= n; i++) {
            int curHeight = i == n ? Integer.MIN_VALUE : heights[i];
            while (!stack.isEmpty() && curHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }
}
