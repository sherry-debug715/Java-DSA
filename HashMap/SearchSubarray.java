package HashMap;

import java.util.HashMap;
import java.util.Map;

// Lintcode problem 1457: https://www.lintcode.com/problem/1457/
// Time: O(N);
// Space: O(N);
public class SearchSubarray {
    public int searchSubarray(int[] arr, int k) {
        // edge case
        if (arr == null) {
            return -1;
        }

        // use hashmap to store each num from arr and the index next to it
        Map<Long, Integer> prefixMap = new HashMap<>();
        // subarray with length of one is also valid
        // arr=[1,2,3,4,5] ，k=5, arr[4] is also valid 
        prefixMap.put((long)0, 0);
        // tracking sum 
        long curSum = 0;
        for (int i = 0; i < arr.length; i++) {
            curSum = curSum + arr[i];
            // see if we can find a prefix sum in map 
            long prefixSum = curSum - k;
            if (prefixMap.containsKey(prefixSum)) {
                return i - prefixMap.get(curSum - k) + 1;
            }
            // it's important to include the following condition
            if (!prefixMap.containsKey(curSum)) {
//     i
// [1,-1,2,-2,3]. k = 3
// {0:0, 1:1, 0:2, 2: 3, 0: 4}
// because the question require to return the answer with minimum starting position
// if multiple subarray exists and they share the same ending position. 
// therefore, in the above situation, we do not
// want to overwrite 0 with 4 when i == 3.
                prefixMap.put(curSum, i + 1);
            }
        }

        return -1;
    }
}

// The following example demostrate how the hashmap is used:

// when i is pointing at 3, curSum = 0 + 3 == 3, we look for 12 - 3, which is 9 in the map, 
// 9 is not in the map, we add curSum and i + 1 in the map, because, if we ever
// find a situation that k - arr[i] == 3, then map.get(3) is pointing at the 
// starting position of the current subarray that satisfy the answer.  
//           i
// [3,5,7,10,2] k = 12


// prefixSum = 15 

// return i - map[prefixSum - k] + 1