package BinarySearch;
public class MedianOfTwoSortedArrays {
    // Time: O(log(sizeA + sizeB))
    // Space: O(1)
    public double findMedianSortedArrays(int[] shorter, int[] longer) {
        int aSize = shorter.length, bSize = longer.length;
        // ensures a is the shorter array.
        if (aSize > bSize) {
            return findMedianSortedArrays(longer, shorter);
        }

        int half = (aSize + bSize) / 2;
        boolean isEven = (aSize + bSize) % 2 == 0;
        int left = 0, right = shorter.length - 1; 
        while (true) {
            // p1 is pointer for longer, p2 is pointer for shorter
            int p2 = left + (right - left) / 2;
            int p1 = half - p2;

            // Edge cases: use MIN and MAX values when partition is out of bounds.
            int leftL = p1 > 0 ? longer[p1 - 1] : Integer.MIN_VALUE;
            int rightL = p1< longer.length ? longer[p1] : Integer.MAX_VALUE;
            int leftS = p2 > 0 ? shorter[p2 - 1] : Integer.MIN_VALUE;
            int rightS = p2 < shorter.length ? shorter[p2] : Integer.MAX_VALUE;

            if (leftL <= rightS && leftS <= rightL) {
                if (isEven) {
                    return (double) (Math.max(leftL, leftS) + Math.min(rightL, rightS)) / 2;
                }
                return (double) Math.min(rightL, rightS);
            } else if (leftL > rightS) {
                left = p2 + 1;
            } else {
                right = p2 - 1;
            }
        }
    }
}
