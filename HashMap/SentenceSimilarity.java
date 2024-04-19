package HashMap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class SentenceSimilarity {
    public boolean isSentenceSimilarity(String[] words1, String[] words2, List<List<String>> pairs) {
        // edge case 
        if (words1 == null && words2 == null) {
            return true;
        }
        if (words1 == null || words2 == null) {
            return false;
        }
        // if words1 and words2 have different length, return false 
        if (words1.length != words2.length) {
            return false;
        }

        // use a hashmap to store similar pairs, one word could have multiple similar words.
        Map<String, HashSet<String>> similarPairs = new HashMap<>();
        // iterate over pairs to build simialarPairs 
        buildSimilarPairs(similarPairs, pairs);
        // System.out.println("similarPairs" + similarPairs);
        // iterate over similarPairs, checking for similarity.
        for (int i = 0; i < words1.length; i++) {
            String w1 = words1[i];
            String w2 = words2[i];
            // if w1 == w2 they are similar 
            if (w1.equals(w2)) {
                continue;
            }
            // first check if w1 is in hashMap, if it is, then check if w2 is in it's hashSet. 
            // words1 -> words2
            boolean flag1 = similarPairs.get(w1) == null || !similarPairs.get(w1).contains(w2);
            // words2 -> words1 
            boolean flag2 = similarPairs.get(w2) == null || !similarPairs.get(w2).contains(w1);
            // if either flags is raised, return false 
            if (flag1 && flag2) {
                return false;
            }
        }

        return true;
    }

    private void buildSimilarPairs(Map<String, HashSet<String>> similarPairs,
                                   List<List<String>> pairs) {
        for (List<String> pair : pairs) {
            String word1 = pair.get(0);
            String word2 = pair.get(1);
            // if hashSet doesn't exist then create one 
            similarPairs.putIfAbsent(word1, new HashSet<String>());
            similarPairs.get(word1).add(word2);
        }
    }
}
