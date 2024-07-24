package Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Leetcode 163 
// Time: O(N)
// Space: O(N * 2)
public class MissingRanges {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            List<Integer> gap = new ArrayList<>();
            Collections.addAll(gap, lower, upper);
            res.add(gap);
            return res;
        }
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            int curNum = i == n ? upper + 1 : nums[i];
            int prevNum = i == 0 ? lower - 1 : nums[i - 1];
            if (curNum == prevNum + 1) {
                continue;
            }
            List<Integer> gap = new ArrayList<>();
            Collections.addAll(gap, prevNum + 1, curNum - 1);
            res.add(gap);
        }
        
        return res;

    }
}
