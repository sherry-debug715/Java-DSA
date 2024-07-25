package Sort;

import java.util.Arrays;
// Leetcode 973
// Time: O(nlogn)
// Space: O(k)
public class KClosestPointToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        int n = points.length;        
        quickSelect(0, n - 1, k, points);
        return Arrays.copyOf(points, k);
    }
    
    private void quickSelect(int start, int end, int k, int[][] points) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        int pivot = calcuDist(points[mid][0], points[mid][1]);
        int left = start, right = end;
        
        while (left <= right) {
            while (left <= right && calcuDist(points[left][0], points[left][1]) < pivot) {
                left += 1;
            }
            while (left <= right && calcuDist(points[right][0], points[right][1]) > pivot) {
                right -= 1;
            }
            if (left <= right) {
                int[] temp = points[left];
                points[left] = points[right];
                points[right] = temp;
                left += 1;
                right -= 1;
            }
        }
        
        if (k <= right) {
            quickSelect(start, right, k, points);
        }
        if (k >= left) {
            quickSelect(left, end, k, points);
        }
    }
    
    private int calcuDist(int x, int y) {
        return x * x + y * y;
    }
}
