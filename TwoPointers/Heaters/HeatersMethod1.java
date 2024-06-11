package TwoPointers.Heaters;
import java.util.stream.Collectors;

// lintcode 1219
// Description
// Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

// Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

// So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.
// Example:
// Input: [1,2,3,4],[1,4]
// Output: 1
// Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.

import java.util.Arrays;

public class HeatersMethod1 {
    // approach: for every house, find the closest heater from heateres and return the distance 
    // the biggest distances of house to heater would be the answer 
    // Time: O(NlogN)
    // Space: O(1)
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters); // O(NlogN)
        int minRadius = 0;

        for (int house : houses) { // O(NlogN)
            int distance = findMinRadius(house, heaters);
            minRadius = Math.max(minRadius, distance);
        }
        return minRadius;
    }

    private int findMinRadius(int house, int[] heaters) {
        int start = 0, end = heaters.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (heaters[mid] > house) {
                end = mid;
            } else {
                start = mid;
            }
        }
        // end while 
        int left = Math.abs(house - heaters[start]);
        int right = Math.abs(house - heaters[end]);
        return Math.min(left, right); 
    }
}

// use built in method 

class Solution {
    /**
     * @param houses: positions of houses
     * @param heaters: positions of heaters
     * @return: the minimum radius standard of heaters
     */
    // approach: for every house, find the closest heater from heateres and return the distance 
    // the biggest distances of house to heater would be the answer 
    // Time: O(NlogN)
    // Space: O(1)
    public int findRadius(int[] houses, int[] heaters) {
        int minRadius = 0;
        // create a treeSet, and pass in heaters list with Integer objects
        List<Integer> heaterList = new ArrayList<>();
        Arrays.stream(heaters).forEach(h -> heaterList.add(h));
        TreeSet<Integer> heaterSet = new TreeSet<>(heaterList);
        // TreeSet<Integer> heaterSet = new TreeSet<>(Arrays.stream(heaters).boxed().collect(Collectors.toList()));

        for (int house : houses) { // O(NlogN)
            Integer leftMax = heaterSet.floor(house);
            Integer rightMin = heaterSet.higher(house); 
            minRadius = Math.max(minRadius, 
            Math.min(leftMax == null ? Integer.MAX_VALUE : Math.abs(house - leftMax),
                     rightMin == null ? Integer.MAX_VALUE : Math.abs(house - rightMin)));
        }
        return minRadius;
    }
}
