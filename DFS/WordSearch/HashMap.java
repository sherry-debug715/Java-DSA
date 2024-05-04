package DFS.WordSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap

public class HashMap {
    public static int[] dx = {0, 1, -1, 0};
    public static int[] dy = {1, 0, 0, -1};

    public List<String> wordSearchII(char[][] board, List<String> words) {
        ArrayList<String> output = new ArrayList<>();

        if (words == null || words.size() == 0) {
            return output;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        // prefixMap is a hashmap with key of preifxes of each word and the word itself
        // from words list. All the prefixes have false value only word has true value.
        HashMap<String, Boolean> prefixMap = constructPrefixMap(words);
        // System.out.println("prefixMap" + " " + prefixMap);

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                // System.out.println("prefixMap.containsKey(board[r][c])" + " " + prefixMap.containsKey(board[r][c]));

                if (!prefixMap.containsKey(String.valueOf(board[r][c]))) {
                    continue;
                }
                // System.out.println("String.valueOf(board[r][c])" + " " + String.valueOf(board[r][c]));
                dfs(r, c, board, visited, prefixMap, output, String.valueOf(board[r][c]));
            }
        }
        return output;
    }

    private void dfs(int row,
                int col,
                char[][] board,
                boolean[][] visited,
                HashMap<String, Boolean> prefixMap,
                ArrayList<String> output,
                String curStr) {
        if (!prefixMap.containsKey(curStr)) return; 
        // find a word 
        if (prefixMap.get(curStr) == true) {
            output.add(curStr);
            prefixMap.put(curStr, false);
        }
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            if(isValid(newRow, newCol, board, visited)) {
                dfs(newRow, newCol, board, visited, prefixMap, output, curStr + board[newRow][newCol]);
            }
        }
        visited[row][col] = false;
    }

    private boolean isValid(int row,
                            int col,
                            char[][] board,
                            boolean[][] visited) {
        if (row < 0 || row == board.length) return false;
        if (col < 0 || col == board[0].length) return false;
        if (visited[row][col]) return false;
        return true;
    }


    private java.util.HashMap<String, Boolean> constructPrefixMap(List<String> words) {
        HashMap<String, Boolean> prefixMap = new HashMap<>();
        // get access to each char word from words 
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                prefixMap.putIfAbsent(word.substring(0, i + 1), false);
            }
            prefixMap.put(word, true);
        }
        return prefixMap;
    }
}
