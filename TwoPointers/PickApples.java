package TwoPointers;
// lintcode 1850 
// Description
// Alice and Bob work in a beautiful orchard. There are N apple trees in the orchard. The apple trees are arranged in a row and they are numbered from 1 to N.
// Alice is planning to collect all the apples from K consecutive trees and Bob is planning to collect all the apples from L consecutive trees.
// They want to choose to disjoint segements (one consisting of K trees of Alice and the other consisting of L trees for Bob) so as not to disturb each other. you should return the maximum number of apples that they can collect.

// input:
// A = [6, 1, 4, 6, 3, 2, 7, 4]
// K = 3
// L = 2
// Output: 
// 24

// Time: O(N * 4N)
// Space: O(1)
public class PickApples {
    public int pickApples(int[] a, int k, int l) {
        int n = a.length;
        if (n < k + l) {
            return -1;
        }

        int maxPick = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // check k -> l pattern 
            int leftMaxK = findMax(a, k, 0, i); // [0, i)
            int rightMaxL = findMax(a, l, i, n); // [i, n)
            // check l -> k pattern 
            int leftMaxL = findMax(a, l, 0, i);
            int rightMaxK = findMax(a, k, i, n);
            if (leftMaxK != -1 && rightMaxL != -1) {
                maxPick = Math.max(maxPick, leftMaxK + rightMaxL);
            }
            if (leftMaxL != -1 && rightMaxK != -1) {
                maxPick = Math.max(maxPick, leftMaxL + rightMaxK);
            }
        }
        // end for 
        if (maxPick == Integer.MIN_VALUE) {
            return -1;
        }
        return maxPick;
    }

    private int findMax(int[] a, int range, int start, int end) {
        if (end - start < range) {
            return -1;
        }
        int maxApples = 0;
        int sum = 0;
        for (int i = start; i < start + range; i++) {
            sum += a[i];
        }
        maxApples = Math.max(maxApples, sum);
        int left = start, right = start + range;
        while (right < end) {
            sum += a[right];
            sum -= a[left];
            maxApples = Math.max(maxApples, sum);
            right += 1;
            left += 1;
        }
        return maxApples;
    }
}
