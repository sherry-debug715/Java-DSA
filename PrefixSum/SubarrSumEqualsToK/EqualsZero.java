package PrefixSum.SubarrSumEqualsToK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// Time: O(N)
// Space: O(N)
public class EqualsZero {
    public List<Integer> subarraySum(int[] nums) {
        List<Integer> output = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return output;
        }
        // prefixMap key is the prefix sum of current index, value is the closest 
        // index with the prefix sum  
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 0);
        int preifxSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preifxSum += nums[i];
            // System.out.println(curPrefix);
            // System.out.println(prefixMap);
            // check if 0 is found, before updating prefixMap 
            if (prefixMap.containsKey(preifxSum)) {
                output.add(prefixMap.get(preifxSum));
                output.add(i);
                break;
            }
            // update prefixMap 
            prefixMap.put(preifxSum, i + 1);
        }
        return output;
    }
}
