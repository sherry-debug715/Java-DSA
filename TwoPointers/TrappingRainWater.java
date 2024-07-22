package TwoPointers;
// lintcode 363 
// Time: O(N)
// Space: O(1)
public class TrappingRainWater {
    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int water = 0;
        int n = heights.length;
        int l = 0, r = n - 1;
        int leftMax = heights[l], rightMax = heights[r];
        while (l < r) {
            if (heights[l] < heights[r]) {
                leftMax = Math.max(leftMax, heights[l]);
                water += leftMax - heights[l];
                l += 1;
            } else {
                rightMax = Math.max(rightMax, heights[r]);
                water += rightMax - heights[r];
                r -= 1;
            }
        }
        return water;
    }
}
