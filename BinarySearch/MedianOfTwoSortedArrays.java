package BinarySearch;
// time: O(logN)
// Space: O(1)
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length == 0 && b.length == 0) {
            return 0.0;
        }

        int total = a.length + b.length; 
        boolean isEven = total % 2 == 0; 
        int p1 = 0, p2 = 0, L = 0, R =0;
        int half = total / 2;

        // a, b are both sorted, only need to find the half th number 
        for (int i = 0; i < half + 1; i++) {
            // always move left pointer before right pointer is moved 
            L = R;
            if (p2 == b.length || p1 < a.length && a[p1] <= b[p2]) {
                R = a[p1];
                p1 += 1;
            } else {
                R = b[p2];
                p2 += 1;
            }
        }

        if (isEven) {
            return (L + R) / 2.0;
        }
        return R;
    }
}
