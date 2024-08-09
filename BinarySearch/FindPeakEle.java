package BinarySearch;
// Lintcode 75
// Time: O(logN)
// Space: O(1)
public class FindPeakEle {
    public int findPeak(int[] a) {
        int left = 0, right = a.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (a[mid] > a[mid - 1] && a[mid] > a[mid + 1]) {
                return mid;
            }
            if (a[mid] > a[mid - 1] && a[mid] < a[mid + 1]) {
                // peak is on the right side 
                left = mid;
            } else {
                right = mid;
            }
        }
        return Math.max(left, right);
    }
}
