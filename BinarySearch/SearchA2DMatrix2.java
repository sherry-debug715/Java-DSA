package BinarySearch;

public class SearchA2DMatrix2 {
    public int searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int output = 0;
        int n = matrix.length, m = matrix[0].length;
        int row = n - 1, col = 0;
        while (row >= 0 && col < m && col >= 0) {
            int curNum = matrix[row][col];
            if (curNum > target) {
                row -= 1;
            } else if (curNum < target) {
                col += 1;
            } else {
                 output += 1;
                row -= 1;
                col += 1;
            }
        }
        return output;
    }
}
