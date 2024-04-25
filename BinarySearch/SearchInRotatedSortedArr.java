package BinarySearch;

public class SearchInRotatedSortedArr {
// O(logN)
// O(1)
public int search(int[] a, int target) {
    if (a == null || a.length == 0) {
        return -1;
    }

    int left = 0, right = a.length - 1;
    
    while (left + 1 < right) {
        int mid = left + (right - left) / 2;
        if (a[mid] == target) {
            return mid;
        }

        // mid on the right portion 
        if (a[mid] < a[right]) {
            if (a[mid] <= target && target <= a[right]) {
                left = mid;
            } else {
                right = mid;
            }
        } else {
            // mid on the left portion 
            if (target < a[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
    }

    if (a[left] == target) {
        return left;
    }
    if (a[right] == target) {
        return right;
    }
    return -1;
}
}
