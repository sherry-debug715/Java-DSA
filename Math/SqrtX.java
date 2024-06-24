package Math;
// leetcode 69
// Time: O(logN)
// Space: O(1)
public class SqrtX {
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int left = 1, right = x / 2;
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid <= x / mid) {
                result = mid; // update the result to mid
                left = mid + 1; // move the left boundary up
            } else {
                right = mid - 1; // move the right boundary down
            }
        }

        return result;
    }
}
