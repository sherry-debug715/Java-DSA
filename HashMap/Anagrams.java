package HashMap;

import java.util.List;

public class Anagrams {
    public List<String> anagrams(String[] strs) {
        List<String> output = new ArrayList<>();
        Map<String, List<String>> charMap = new HashMap<>();
        populateAsciiMap(strs, charMap);

        // any list value from asciiMap that's longer than length of 1 is the output 
        for (List<String> anagram : charMap.values()) {
            if (anagram.size() > 1) {
                for (String word : anagram) {
                    output.add(word);
                }
            }
        }
        return output;
    }

    private void populateAsciiMap(String[] strs, Map<String, List<String>> charMap) {
        for (String str : strs) {
            char[] strArr = str.toCharArray();
            Arrays.sort(strArr);
            String newStr = new String(strArr);
            // check if newStr is in map 
            if (!charMap.containsKey(newStr)) {
                charMap.put(newStr, new ArrayList<>());
            }
            charMap.get(newStr).add(str);
        }
    }
}
