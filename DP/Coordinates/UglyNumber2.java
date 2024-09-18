package DP.Coordinates;

public class UglyNumber2 {
     // Time: O(n) because we process each number once 
     // Space: O(n) to store the array of ugly numbers 
     public int nthUglyNumber(int n) {
        long[] dp = new long[n];
         // The first ugly number is defined as 1L
        dp[0] = 1L;

        // Pointers representing the current positions in the dp array for multiples of 2, 3, and 5.
        int l2 = 0, l3 = 0, l5 = 0; 

        // Generate ugly numbers up to the nth position 
        for (int i = 1; i < n; i++) {
            long num2 = dp[l2] * 2;
            long num3 = dp[l3] * 3;
            long num5 = dp[l5] * 5;

            // Find the smallest next ugly number among the factors of 2, 3, and 5.
            long nextUgly = Math.min(num2, Math.min(num3, num5));

            // update dp with the smallest next ugly number.
            dp[i] = nextUgly;
            
            // Move pointers forward to avoid duplicates.
            // If the next ugly number is a multiples of 2, 3, or 5, move the respective pointer.
            if (nextUgly == num2) {
                l2 += 1;
            }
            if (nextUgly == num3) {
                l3 += 1;
            }
            if (nextUgly == num5) {
                l5 += 1;
            }
        }

        // return the nth ugly number (0-indexed)
        return (int) dp[n - 1];
    }
}
