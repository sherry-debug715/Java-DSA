package Bits;
// Leetcode 2220
// Time: O(1)
// Space: O(1)
public class MinimumBitFlipsToConvertNumber {
    public int minBitFlips(int start, int goal) {
        int xor = start ^ goal;

        return Integer.bitCount(xor);
    }
}

/*
 * start = 10, goal = 7
 * start = 1010
   goal  = 0111
   XOR   = 1101
   int xor = 3
 */
