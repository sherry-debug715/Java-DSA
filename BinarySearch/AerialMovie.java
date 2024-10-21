package BinarySearch;
// Lintcode 1636 

// Time: O(nlogn)
// Space: O(1)
public class AerialMovie {
    public int[] aerialMovie(int t, int[] dur) {
        Arrays.sort(dur);
        int[] res = new int[2];
        int curDistance = 0;
        for (int i = 0; i < dur.length - 1; i++) {
            int left = i;
            int right = findRightBound(i + 1, dur.length - 1, t - 30 - dur[left], dur);
            if (right == -1) {
                continue;
            }
            int curSum = dur[left] + dur[right];
            if (curSum > curDistance) {
                curDistance = curSum;
                res[0] = dur[left];
                res[1] = dur[right];
            } else if (curSum == curDistance && res[1] < dur[right]) {
                res[1] = dur[right];
            }

        }
        return res;
    }
    // find the closest number to target 
    private int findRightBound(int left, int right, int target, int[] dur) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (dur[mid] == target) {
                return mid;
            }
            if (dur[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (dur[right] <= target) {
            return right;
        }
        if (dur[left] <= target) {
            return left;
        }
        return -1;
    }
}
