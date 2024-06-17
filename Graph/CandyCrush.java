package Graph;
// leetcode 723
// Time: O(M^2 * N^2)
// Space: O(M*N)
public class CandyCrush {
    public int[][] candyCrush(int[][] board) { 
        int rowSize = board.length, colSize = board[0].length;
        boolean isStable = checkStable(board, rowSize, colSize);
        while (!isStable) { // O(M * N)
            for (int c = 0; c < colSize; c++) {
                drop(c, board, rowSize);
            }
            isStable = checkStable(board, rowSize, colSize);              
        }
        return board;
    }
    
    private void drop(int col, int[][] board, int rowSize) {
        // drop all 0s from current col down 
        int right = rowSize - 1;
        for (int left = rowSize - 1; left >= 0; left--) {
            if (board[left][col] != 0) {
                board[right][col] = board[left][col];
                right -= 1;
            }
        }
        for (int i = right; i >= 0; i--) {
            board[i][col] = 0;
        }
    }
    
    private boolean checkStable(int[][] board, int rowSize, int colSize) { // Time O(M * N)
        boolean[][] boardCheck = new boolean[rowSize][colSize];
        boolean isStable = true;
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                int candy = board[r][c];
                if (candy == 0) {
                    continue;
                }
                // check horizontally 
                if (c + 2 < colSize && board[r][c + 2] == candy && board[r][c + 1] == candy) {
                    boardCheck[r][c + 1] = boardCheck[r][c + 2] = boardCheck[r][c] = true;
                    isStable = false;
                }
                
                // check vertically 
                if (r + 2 < rowSize && board[r + 1][c] == candy && board[r + 2][c] == candy) {
                    boardCheck[r + 1][c] = boardCheck[r][c] = boardCheck[r + 2][c] = true;
                    isStable = false;
                }
            }
        }
        
        // end for 
        // crush 
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (boardCheck[r][c]) {
                    board[r][c] = 0;
                }
            }
        }
        
        // end for 
        return isStable;
    }
}
