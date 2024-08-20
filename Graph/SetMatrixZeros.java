package Graph;
// Leetcode 73
public class SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] rowZero = new boolean[m + 1];
        boolean[] colZero = new boolean[n + 1];
        findZeros(matrix, rowZero, colZero);

        for (int i = 0; i < m + 1; i++) {
            if (rowZero[i]) {
                populateRow(matrix, i);
            }
        }

        for (int i = 0; i < n + 1; i++) {
            if (colZero[i]) {
                populateCol(matrix, i);
            }
        }

    }

    private void findZeros(int[][] matrix, boolean[] rowZero,  boolean[] colZero) {
        int m = matrix.length, n = matrix[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 0) {
                    rowZero[r] = true;
                    colZero[c] = true;
                }
            }
        }
    }

    private void populateRow(int[][] matrix, int row) {
        int m = matrix.length, n = matrix[0].length;
        for (int c = 0; c < n; c++) {
            matrix[row][c] = 0;
        }
    }

    private void populateCol(int[][] matrix, int col) {
        int m = matrix.length, n = matrix[0].length;
        for (int r = 0; r < m; r++) {
            matrix[r][col] = 0;
        }
    }
}
