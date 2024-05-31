package PrefixSum;
// lintcode 389
// time: O(1);
// space: O(1);
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int[][] rowValid = new int[9][9];
        int[][] colValid = new int[9][9];
        int[][][] boxValid = new int[3][3][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int curIdx = board[i][j] - '0' - 1;
                rowValid[i][curIdx] += 1;
                colValid[j][curIdx] += 1;
                boxValid[i / 3][j / 3][curIdx] += 1;
                if (rowValid[i][curIdx] > 1 || colValid[j][curIdx] > 1 || boxValid[i / 3][j / 3][curIdx] > 1) {
                    return false;
                }
                
            }
        }
        return true;
    }
}
