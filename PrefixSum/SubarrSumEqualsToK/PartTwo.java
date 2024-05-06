package PrefixSum.SubarrSumEqualsToK;

import java.util.HashMap;
// Time: O(N)
// Space: O(N)
public class PartTwo {
    public int subarraySumEqualsKII(int[] nums, int k) {
        // edge case 
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int minSubarrLen = Integer.MAX_VALUE;
        // prefix Sum key is the prefix sum of nums[i], value is the closest index 
        // with a prefix sum of the key.
        HashMap<Integer, Integer> prefixMap = new HashMap<>();
        // for the first index of nums, it's prefix sum is 0 and it's index is 0; 
        prefixMap.put(0, 0);
        int curPrefixSum = 0;
        for (int i = 1; i < nums.length + 1; i++) {
            curPrefixSum += nums[i - 1];
            // if curPrefixSum - k is in prefixMap 
            if (prefixMap.containsKey(curPrefixSum - k)) {
                // get the index of the starting position 
                int startIdx = prefixMap.get(curPrefixSum - k);
                minSubarrLen = Math.min(minSubarrLen, i - startIdx);
            }
            // update prefixMap;
            prefixMap.put(curPrefixSum, i);
        }
        if (minSubarrLen == Integer.MAX_VALUE) {
            return -1;
        }
        return minSubarrLen;
    }
