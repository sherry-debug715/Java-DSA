package TwoPointers;
// Lintcode problem 144: https://www.lintcode.com/problem/144/?fromId=161&_from=collection
// Time: O(N)
// Space: O(1)
public class InterleavingPosAndNegs {
    public void rerange(int[] a) {
        // edge case 
        if (a == null || a.length == 0) {
            return;
        }

        // move all negative number to the left and positive to the right 
        int left = 0, right = a.length - 1;
        // right should stop at the last negative number, while left stop at the 
        // begining of positive number
        while (left <= right) {
            while (left <= right && a[left] < 0) {
                left += 1;
            }
            while (left <= right && a[right] > 0) {
                right -= 1;
            }
            // if statement is necessarty, after the while loop, we could have the
            // following situation and we don't want to swap them. 
            //              l
            // [-33,-9,-19,26,30,21]
            //          r
            if (left <= right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
            }
        }

        int posCount = a.length - right - 1, negCount = right + 1;
        if (Math.abs(posCount - negCount) >= 2) {
            return;
        }

        // interleave pos and neg numbers 
        // if more positive number, array should start with pos number, hence l = 0 
        int l = posCount > negCount ? 0 : 1;
        // if more positive number, array should end with pos number, hence r = a.length - 2
        int r = posCount >= negCount ? a.length - 2 : a.length - 1;
        while (l <= r) {
            int temp = a[l];
            a[l] = a[r];
            a[r] = temp;
            l += 2;
            r -= 2;
        }  
    }
}
