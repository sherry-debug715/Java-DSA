package Sort;
// Lintcode 189
// Time: O(n)
// Space: O(1)
public class FirstMissingPostive {
    public int firstMissingPositive(int[] A) {
        // write your code here
        if (A == null) {
            return 1;
        }
        int n = A.length;

        // the result can only be in the range of [1: n + 1]
        for (int i = 0; i < n; i++) {
            // use A to store the sorted order, negative num, duplicate num and num >= n to 
            // take spot for missing positve numbers.
            while (A[i] > 0 && A[i] <= n && A[i] != (i + 1)) {
                // put A[i] in the correct index 
                int tmp = A[A[i] - 1];
                if (tmp == A[i]) {
                    break;
                }
                A[A[i] - 1] = A[i];
                A[i] = tmp;
            } 
        }

        // the first index that's not i + 1 is the missing num 
        for (int i = 0; i < n; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
