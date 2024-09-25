package DFS.Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// Lintcode 17 
public class TakeEveryOne {
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     *          we will sort your return value in output
     */
    // Time: O(2 ^ n) where n is the length nums 
    // Space: O(2^n) where 2 ^ n is the storage for storing the result list 
    List<List<Integer>> res = new ArrayList<>(); 
    public List<List<Integer>> subsets(int[] nums) {
        // edge case 
        if (nums == null || nums.length == 0) {
            res.add(new ArrayList<Integer>()); // [[]]
            return res;
        }

        // sort the array to ensure duplicate numbers are handled 
        Arrays.sort(nums);
        // dfs approach to generate all suvbsets
        dfs(nums, 0, new ArrayList<Integer>());
        return res;
    }

    private void dfs(int[] nums, int startIndex, List<Integer> subset) {
        res.add(new ArrayList<>(subset));

        for (int i = startIndex; i < nums.length; i++) {
            // skip duplicates 
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            subset.add(nums[i]);
            dfs(nums, i + 1, subset);
            subset.remove(subset.size() - 1);
        }
    }
}
