package String;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// lintcode 774
// Time: O(N)
// Space: O(N)
public class RepeatedDNA {
        public List<String> findRepeatedDna(String s) {
        Set<String> output = new HashSet<>();
        if (s == null || s.length() == 0) {
            return new ArrayList<>(output);
        }

        Map<String, Integer> counterMap = new HashMap<>();
        int left = 0;
        for (int right = 9; right < s.length(); right ++) {
            String curSub = s.substring(left, right + 1);
            counterMap.put(curSub, counterMap.getOrDefault(curSub, 0) + 1);
            if (counterMap.get(curSub) > 1) {
                output.add(curSub);
            }
            left += 1;
        }
        return new ArrayList<>(output);
    }
}
