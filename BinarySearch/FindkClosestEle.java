package BinarySearch;
// Lintcode 460 
// Time: O(logN + k)
// Space: O(k)
public class FindkClosestEle {
    public int[] kClosestNumbers(int[] a, int target, int k) {
        // edge case 
        // if (a == null || a.length = 0) {
        //     return a;
        // }
        int[] output = new int[k];
        // locate the right pointer, it should stop at the position where 
        // a[right] <= target.
        int right = locateRightPointer(a, target);
        int left = right - 1;
        // iterate for k times 
        for (int i = 0; i < k; i++) {
            if (isLeftSmaller(left, right, a, target)) {
                output[i] = a[left];
                left -= 1;
            } else {
                output[i] = a[right];
                right += 1;
            }
        }
        return output;
    }

    private boolean isLeftSmaller(int left, int right, int[] a, int target) {
        if (right >= a.length) {
            return true;
        }
        if (left < 0) {
            return false;
        }
        return target - a[left] <= a[right] - target;
    }

    private int locateRightPointer(int[] a, int target) {
        int left = 0, right = a.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (a[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (a[right] >= target) {
            return right;
        }
        if (a[left] >= target) {
            return left;
        }
        // return the length as the farthest 'right' possible.
        // if the input list is [1, 2, 3, 4, 5] and target = 10
        return a.length;
    }
}
