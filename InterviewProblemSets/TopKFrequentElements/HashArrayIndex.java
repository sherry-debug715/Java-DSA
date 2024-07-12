package InterviewProblemSets.TopKFrequentElements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Time: O(N)
// Space: O(N)
public class HashArrayIndex {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: count frequency, O(N)
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        // System.out.println("Frequency Map: " + freq);
        // Step 2: using number frequency as index to store it's relevant numbers 
        // O(N)
        List<Set<Integer>> sortByFreq = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            sortByFreq.add(new HashSet<Integer>());
        }
        // Step 3: Populate sortByFreq with numbers based on their frequency
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int number = entry.getKey();
            int frequency = entry.getValue();
            sortByFreq.get(frequency).add(number);
        }

        // System.out.println(sortByFreq);
        // Step 4: form res, iterate over sortByFreq from end for k times 
        int[] res = new int[k];
        int idx = 0;
        for (int i = sortByFreq.size() - 1; i >= 0 && idx < k; i--) {
            if (sortByFreq.get(i).size() == 0) {
                continue;
            } 
            for (int n : sortByFreq.get(i)) {
                res[idx] = n;
                idx += 1;
                if (idx == k) {
                    break;
                }
            }
        }

        return res;
    } 
}
