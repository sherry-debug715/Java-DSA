package DFS.WordSearch;
// Leetcode 79
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
