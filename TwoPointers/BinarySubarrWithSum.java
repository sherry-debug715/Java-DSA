package TwoPointers;
// lintcode 1712
// Time: O(N)
// Space: O(1)
public class BinarySubarrWithSum {
    public int numSubarraysWithSum(int[] a, int s) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int output = 0;
        int n = a.length;
        int l1 = 0, l2 = 0;
        int sum1 = 0, sum2 = 0;
        // a[l1:l2) when pairs with a[l2 + 1: r] == s
        for (int r = 0; r < n; r ++) {
            sum1 += a[r]; 
            // a[l1:r] maintains <= s 
            while (l1 <= r && sum1 > s) {
                sum1 -= a[l1];
                l1 += 1;
            }

            sum2 += a[r]; 
            while (l2 <= r && sum2 >= s) {
                sum2 -= a[l2];
                l2 += 1;
            }

            output += l2 - l1;
        }

        return output;
    }
}
