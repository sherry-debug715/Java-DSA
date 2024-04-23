package DataStructureDesign;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Time: O(n)
// Space: O(n)
public class TwoSum3 {
    List<Integer> nums; 
    public TwoSum3() {
        nums = new ArrayList<>();
    }

    public void add(int number) {
        nums.add(number);
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        Set<Integer> diff = new HashSet<>();
        if (nums.isEmpty()) {
            return false;
        }
        for (int n : nums) {
            if (diff.contains(n)) {
                return true;
            }
            diff.add(value - n);
        }
        return false;
    }
}
