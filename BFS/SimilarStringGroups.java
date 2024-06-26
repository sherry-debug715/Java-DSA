package BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// lintcode 1430 
// Time: if word length ^ 2 > dict.length: O(L^3)
// else: O(N*L)
public class SimilarStringGroups {
    public int numSimilarGroups(String[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }

        Set<String> visited = new HashSet<>();
        Set<String> wordSet = new HashSet<>(Arrays.asList(a)); // convert a to a set for O(L) look up time 
        int pair = 0;
        for (String word : a) {
            if (visited.contains(word)) {
                continue;
            }
            pair += 1;
            visited.add(word);
            bfs(visited, word, wordSet);
        }
        return pair;
    }

    private void bfs(Set<String> visited, String word, Set<String> wordSet) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(word); 
        
        while (!queue.isEmpty()) {
            String curWord = queue.poll();
            for (String n : getNeighbors(curWord, wordSet)) {
                if (visited.contains(n)) {
                    continue;
                }
                visited.add(n);
                queue.offer(n);
            } 
        }
    }

    private List<String> getNeighbors(String curWord, Set<String> wordSet) {
        int len = curWord.length();
        if (len*len < wordSet.size()) {
            return getNeighborByCurword(curWord, wordSet);
        }
        return getNeighborByWordSet(curWord, wordSet);
    }

    private List<String> getNeighborByWordSet(String word, Set<String> wordSet) { // O(N*L) 
        // for each word in wordSet w, check the difference chars between word and  w 
        List<String> neighbors = new ArrayList<>();

        for (String w : wordSet) {
            if (isSimilar(w, word)) {
                neighbors.add(w);
            }
        }
        return neighbors;
    }

    private boolean isSimilar(String word1, String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff += 1;
            }
        }
        return diff == 2;
    }

    private List<String> getNeighborByCurword(String word, Set<String> wordSet) { // O(L^3)
        int n = word.length();
        char[] wordArr = word.toCharArray();
        List<String> neighbors = new ArrayList<>();

        for (int i = 0; i < n; i++) { // O(L^2 * 2L)
            for (int j = i + 1; j < n; j++) { 
                // swap i and j 
                char c = wordArr[i];
                wordArr[i] = wordArr[j];
                wordArr[j] = c;
                String swapped = String.valueOf(wordArr); // O(L) 
                if (wordSet.contains(swapped)) { // O(L) 
                    neighbors.add(swapped);
                }
                // change it back 
                c = wordArr[i];
                wordArr[i] = wordArr[j];
                wordArr[j] = c;
            }
        }

        return neighbors;
    }
}
