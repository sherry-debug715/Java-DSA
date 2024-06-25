package DFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Lintcode 634
public class WordSquares {
    public List<List<String>> wordSquares(String[] words) {
        Map<String, List<String>> prefixToWord = explorePrefixToWord(words);
        List<List<String>> matrix = new ArrayList<>();

        for (String word : words) {
            List<String> curMatrix = new ArrayList<>();
            curMatrix.add(word);
            search(prefixToWord, curMatrix, matrix, word);
        }
        return matrix;
    }

    private void search(Map<String, List<String>> prefixToWord,
                        List<String> curMatrix,
                        List<List<String>> matrix,
                        String curWord) {
        int n = curWord.length();
        if (curMatrix.size() == n) {
            matrix.add(new ArrayList<String>(curMatrix));
            return;
        }

        int curIdx = curMatrix.size(); // curIdx is the idx of the current prefix index 
        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < curIdx; i++) {
            prefix.append(curMatrix.get(i).charAt(curIdx));
        }

        for (String word : prefixToWord.getOrDefault(prefix.toString(), new ArrayList<String>())) {
            curMatrix.add(word);
            search(prefixToWord, curMatrix, matrix, curWord);
            curMatrix.remove(curMatrix.size() - 1);
        }
    }

    private Map<String, List<String>> explorePrefixToWord(String[] words) {
        Map<String, List<String>> prefixToWord = new HashMap<>();
        for (String word : words) {
            for (int i = 1; i <= word.length(); i++) {
                String prefix = word.substring(0, i);
                prefixToWord.putIfAbsent(prefix, new ArrayList<String>());
                prefixToWord.get(prefix).add(word);
            }
        }

        return prefixToWord;
    }
}
