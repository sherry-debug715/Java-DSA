package Stack.MonotonicStack;

import java.util.Stack;

// lintcode 510
// Time: O(m * n)
// Space: O(m * n)
public class MaximalRectangle {
    // Approach: treat each row as a bottom  
    public int maximalRectangle(boolean[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length, m = matrix[0].length;
        int[] heights = new int[m];
        int maxArea = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (matrix[r][c]) {
                    heights[c] += 1;
                } else {
                    heights[c] = 0;
                }
            }
            int curArea = computeArea(heights);
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    private int computeArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= heights.length; i++) {
            int curHeight = i == heights.length ? Integer.MIN_VALUE : heights[i];
            while (!stack.isEmpty() && curHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
