package TwoPointers;
// lintcode 1849
// Time: O(n)
// Space: O(1)
public class GrumpyBookstoreOwner {
    public int maxSatisfied(int[] customers, int[] grumpy, int x) {
        int sum = 0;
        int n = grumpy.length;
        // the first x numbers from customers will be considered as good temper.
        // when i == x, if temper is bad, + 0, only add good temper days customer
        // to sum.
        for (int i = 0; i < n; i++) {
            if (i < x) {
                sum += customers[i];
            } else {
                // if bad temper 0, else 1 * customers[i]
                sum += (1 - grumpy[i]) * customers[i];
            }
        } 
        // end for 
        int left = 0, right = x;
        int maxPositive = 0;
        // maintain a x sized window 
        while (right < n) {
            if (grumpy[right] == 1) {
                sum += customers[right];
            }
            if (grumpy[left] == 1) {
                sum -= customers[left];
            }
            maxPositive = Math.max(maxPositive, sum);
            right += 1;
            left += 1;
        }

        return maxPositive;
    }
}
