package DFS.WordSearch;
// Leetcode 79
// TwoSolution below 
class OptimizedSolution1 {
    // O(m * n)
    // Space: O(word.length + 255) 
    public boolean exist(char[][] board, String word) {
        char[] wordArr = word.toCharArray();
        int m = board.length, n = board[0].length;
        // count if board has enough chars to cover all chars from word
        int[] charCounter = new int[255]; 
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                charCounter[board[r][c]] += 1;
            }
        }
        for (char c : wordArr) {
            charCounter[c] -= 1;
            if (charCounter[c] < 0) {
                return false;
            }
        }
        // if wordArr[0] has more chars than wordArr[-1], reverse the string to increase speed of traversing board 
        if (charCounter[wordArr[0]] > charCounter[wordArr[wordArr.length - 1]]) {
            wordArr = new StringBuilder(word).reverse().toString().toCharArray();
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (dfs(r, c, 0, board, wordArr)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int row, int col, int idx, char[][] board, char[] wordArr) {
        if (idx == wordArr.length) { // it's important to check if idx is at the end of wordArr before the second condition 
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        if (board[row][col] != wordArr[idx]) {
            return false;
        }
        board[row][col] = '*';
        boolean up = dfs(row - 1, col, idx + 1, board, wordArr);
        boolean down = dfs(row + 1, col, idx + 1, board, wordArr);
        boolean left = dfs(row, col - 1, idx + 1, board, wordArr);
        boolean right = dfs(row, col + 1, idx + 1, board, wordArr);
        if (up || down || left || right) {
            return true;
        }
        board[row][col] = wordArr[idx];
        return false;
    }
}



public class VersionOne {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == word.charAt(0)) {
                    visited[r][c] = true;
                    if (dfs(r, c, board, word, 0, visited)) {
                        return true;
                    }
                    // backtracking
                    visited[r][c] = false;
                }
            }
        }
        return false;
    }

    private boolean dfs(int row, int col, char[][] board, String word, int curIdx, boolean[][] visited) {
        // exit
        if (curIdx == word.length() - 1) {
            return true;
        }

        int[] POS = new int[] {-1, 0, 1, 0, -1};
        for (int i = 0; i < POS.length - 1; i++) {
            int nextR = row + POS[i];
            int nextC = col + POS[i + 1];
            if (nextR >= 0 && nextR < board.length && nextC >= 0 && nextC < board[0].length && board[nextR][nextC] == word.charAt(curIdx + 1) && !visited[nextR][nextC]) {
                visited[nextR][nextC] = true;
                boolean temp = dfs(nextR, nextC, board, word, curIdx + 1, visited);
                // backtracking 
                visited[nextR][nextC] = false;
                if (temp) {
                    return true;
                }
                
            }
        }
        return false;
    }
}
