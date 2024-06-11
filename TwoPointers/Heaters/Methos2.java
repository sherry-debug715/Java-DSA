package TwoPointers.Heaters;

import java.util.Arrays;

public class Methos2 {
    // Time: O(NlogN);
    // Space: O(1)
    // approach: use two pointer method to locate the left max radius of the current house 
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int finalRadius = 0;
        int i = 0, j = 0;
        while (i < houses.length && j < heaters.length) {
            int leftRadius = Math.abs(heaters[j] - houses[i]);
            int rightRadius = Integer.MAX_VALUE;
            if (j + 1 < heaters.length) {
                rightRadius = Math.abs(heaters[j + 1] - houses[i]);
            }
            if (leftRadius < rightRadius) {
                finalRadius = Math.max(finalRadius, leftRadius);
                i += 1;
            } else {
                j += 1;
            }

        }
        return finalRadius;
    }
}
