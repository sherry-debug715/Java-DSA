package HashMap.KDifference;

import java.util.HashSet;
import java.util.Set;
// Time: O(N)
// Space: O(N)
public class HashMap {
    public int kDifference(int[] nums, int target) {
        // hash set to store each n from nums array. 
        Set<Integer> sum = new HashSet<>();
        int counter = 0;
        for (int n : nums) {
            if (sum.contains(n + target)) {
                counter += 1;
            }
            if (sum.contains(n - target)) {
                counter += 1;
            }
            sum.add(n);
        }
        return counter;
    }
}
