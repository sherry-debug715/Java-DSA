package DFS.WordSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {
    class TriNode {
        HashMap<Character, TriNode> children;
        String word;

        public TriNode() {
            children = new HashMap<>();
            word = null;
        }

        // insert takes in a string, and constract a prefix tree 
        public void insert(String word) {
            // cur start from the instance of TriNode, root   
            TriNode cur = this;
            for (char c : word.toCharArray()) {
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new TriNode());
                }
                // updating the children number of cur 
                cur = cur.children.get(c);
            }
            cur.word = word;
        }

    }

    public List<String> wordSearchII(char[][] board, List<String> words) {
        // build the root of the tree 
        TriNode root = new TriNode();
        ArrayList<String> output = new ArrayList<>();
        if (words == null || words.size() == 0) {
            return output;
        }
        // build prefix tree 
        for (String word : words) {
            root.insert(word);
        }
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(r, c, board, root, output, visited);       
            }
        }

        return output;
    }

    private void dfs(int row,
                     int col,
                     char[][] board, 
                     TriNode root, 
                     ArrayList<String> output,
                     boolean[][] visited) {
        if (row < 0 || row >= board.length) {
            return;
        }
        if (col < 0 || col >= board[0].length) {
            return;
        }
        if (visited[row][col]) {
            return;
        }
        char curChar = board[row][col];
        if (!root.children.containsKey(curChar)) {
            return;
        }
        // at the end of forming a string 
        TriNode childNode = root.children.get(curChar);
        if (childNode.word != null) {
            if (!output.contains(childNode.word)) {
                output.add(childNode.word);
                // can't return here, "dad", "daded" belong to the same branch.
            }
        }
        visited[row][col] = true;
        dfs(row - 1, col, board, childNode, output, visited);
        dfs(row + 1, col, board, childNode, output, visited);
        dfs(row, col - 1, board, childNode, output, visited);
        dfs(row, col + 1, board, childNode, output, visited);
        // backtracking 
        visited[row][col] = false;
    }
}
