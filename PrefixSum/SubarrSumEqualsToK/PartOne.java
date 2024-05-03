package PrefixSum.SubarrSumEqualsToK;

import java.util.HashMap;

public class PartOne {
    public int subarraySumEqualsK(int[] nums, int k) {
        // edge case 
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // prefixMap key is the prefix sum of nums[i], value is the total count of 
        // subarrays that sum up to prefix sum.
        HashMap<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1);
        
        int counter = 0;
        int curPrefixSum = 0;
        // iterate nums, nums.length + 1 because the entire array nums could sum up 
        // to k 
        for (int i = 1; i < nums.length + 1; i++) {
            curPrefixSum += nums[i - 1];
            // check if subarray == k exist 
            // curPrefixSum - k是指如果当前数前面有一个数去掉的话能组成k 
            // 比如说，nums = [2,1,-1,1,2] and k = 3
            // 当i==1时， curPrefixSum == 2, 此时2 - 3是指i前面是否有一个-1的前缀和，如果去掉的话，就能组成3.
            if (prefixMap.containsKey(curPrefixSum - k)) {
                counter += prefixMap.get(curPrefixSum - k);
            }
            prefixMap.put(curPrefixSum, prefixMap.getOrDefault(curPrefixSum, 0) + 1);
        }
        return counter;
    }
}
