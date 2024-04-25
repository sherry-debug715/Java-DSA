package BinarySearch;
// Lintcode problem 183: https://www.lintcode.com/problem/183/?fromId=161&_from=collection
public class WoodCut {
    public int woodCut(int[] l, int k) {
        // the minimum length of wood we can cut is 1 
        int left = 1;
        int right = Integer.MIN_VALUE;
        for (int n : l) {
            right = Math.max(right, n);
        }
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(mid, l) < k) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (check(right, l) >= k) {
            return right;
        }
        if (check(left, l) >= k) {
            return left;
        }
        return 0;
    }

    private int check(int num, int[] numArr) {
        int sum = 0;
        for (int n : numArr) {
            sum += n / num;
        }
        return sum;
    }
}
