package Array;

import java.util.List;
import java.util.Map;

// Leetcode 249
// Time: O(n * m + k)
// Space: O(k)
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> patterns = new HashMap<>();

        for (String str : strings) { // O(n) n: strings.length()
            String pattern = getShiftPattern(str);// O(m) m: str.length();
            patterns.putIfAbsent(pattern, new ArrayList<String>());
            patterns.get(pattern).add(str);
        }

        List<List<String>> res = new ArrayList<>();
        for (String pattern : patterns.keySet()) { // O(k) k: patterns.length()
            res.add(patterns.get(pattern));
        }
        return res;
    }

    private String getShiftPattern(String str) {
        StringBuilder pattern = new StringBuilder();

        for (int i = 1; i < str.length(); i++) {
            char c1 = str.charAt(i), c2 = str.charAt(i - 1);
            int dist = (c2 - c1 + 26) % 26;
            pattern.append(Integer.toString(dist));
            pattern.append(",");
        }

        return pattern.toString();
    } 
}
