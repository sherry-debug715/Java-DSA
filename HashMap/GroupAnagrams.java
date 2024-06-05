package HashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// lintcode 772
// Time: O(N log L);
// Space: O(N)
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> output = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return output;
        }

        Map<String, List<String>> groupMap = new HashMap<>();
        for (String word : strs) {
            char[] wordArr = word.toCharArray();
            Arrays.sort(wordArr);
            String key = new String(wordArr);
            groupMap.putIfAbsent(key, new ArrayList<String>());
            groupMap.get(key).add(word);
        }

        for (String key : groupMap.keySet()) {
            output.add(groupMap.get(key));
        }
        return output;
    }
}
