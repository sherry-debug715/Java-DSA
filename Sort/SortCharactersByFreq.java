package Sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SortCharactersByFreq {
    public String frequencySort(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (char c : s.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        // sort counter by value 
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(counter.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        // create a linkedhashmap to preserve the order 
        LinkedHashMap<Character, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        StringBuilder res = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : sortedMap.entrySet()) {
            int freq = entry.getValue();
            for (int i = 0; i < freq; i++) {
                res.append(Character.toString(entry.getKey()));
            }
        }

        return res.toString();
    }
}
