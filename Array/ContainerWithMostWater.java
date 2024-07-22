package Array;
// lintcode 383 
// Time: O(n)
// Space: O(1)
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1;
        int maxArea = 0;
        while (l < r) {
            int curHeight = Math.min(height[l], height[r]);
            maxArea = Math.max(maxArea, curHeight * (r - l));
            while (l < r && curHeight >= height[l]) {
                l += 1;
            } 
            while (l < r && curHeight >= height[r]) {
                r -= 1;
            }
        }
        return maxArea;
    }
}
