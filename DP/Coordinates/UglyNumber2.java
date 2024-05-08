package DP.Coordinates;
// Time: O(N)
// Space: O(N)
public class UglyNumber2 {
    public int nthUglyNumber(int n) {
        // dp[i] is the next smallest ugly number of dp[i - 1]
        long[] dp = new long[n];
        dp[0] = 1L;
        // each pointers is representing the factors of each prime number 
        int l2 = 0, l3 = 0, l5 = 0;

        for (int i = 1; i < n; i++) {
            long two = dp[l2] * 2;
            long three = dp[l3] * 3;
            long five = dp[l5] * 5;
            // conpute the smallest ugly 
            long min = Math.min(two, Math.min(three, five));
            dp[i] = min;
            if (min == two) {
                l2 += 1;
            }
            if (min == three) {
                l3 += 1;
            }
            if (min == five) {
                l5 += 1;
            }
        } 
        return (int) dp[dp.length - 1];
    }
}
