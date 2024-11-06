package TwoPointers;

import java.util.HashSet;
import java.util.Set;

// Lintcode 939
// Time: O(n ^ 2)
// Space: O(n)
public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {
        Set<Integer> allPoints = new HashSet<>();
        for (int[] p : points) {
            allPoints.add(p[0] * 40001 + p[1]);
        }
        int minArea = Integer.MAX_VALUE;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                // two interval shouldn't be on the same line horizontally or vertically 
                if (points[i][0] == points[j][0] || points[i][1] == points[j][1]) {
                    continue;
                }
                // locate the other two points if the current two points can form a rectangle 
                int point1 = points[i][0] * 40001 + points[j][1];
                int point2 = points[j][0] * 40001 + points[i][1];
                if (!allPoints.contains(point1) || !allPoints.contains(point2)) {
                    continue;
                }
                // calculate area 
                int width = Math.abs(points[j][0] - points[i][0]);
                int height = Math.abs(points[j][1] - points[i][1]);
                minArea = Math.min(minArea, width * height);
            }
        }

        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
}
