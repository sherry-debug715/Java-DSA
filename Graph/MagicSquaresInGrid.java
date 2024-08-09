package Graph;

import java.util.HashSet;
import java.util.Set;

// Leetcode 840 
// Time: O(rowSize * colSize) 2ms 
// Space: O(rowSize * colSize) 41.7mb
public class MagicSquaresInGrid {
    public int numMagicSquaresInside(int[][] grid) {
        if (grid == null || grid.length < 3 || grid[0].length < 3) {
            return 0;
        }
        int counter = 0;
        int rowSize = grid.length, colSize = grid[0].length;
        // Step 1: create prefixSum arrays for rows and cols 
        int[][] rowPrefixSum = drawRowPrefixSum(grid);
        int[][] colPrefixSum = drawColPrefixSum(grid); 
        // Step 2: using sliding window technique to check wach 3 * 3 grid 
        int left = 0, right = 2, top = 0, bottom = 2;
        while (bottom < rowSize) {
            boolean uniqueValid = checkUnique(left, top, grid);
            boolean rowSumValid = checkRowSum(rowPrefixSum, left, top, grid);
            boolean colSumValid = checkColSum(colPrefixSum, left, top, grid);
            boolean diagonalValid = checkDiagonal(left, right, top, grid);
            if (uniqueValid && rowSumValid && colSumValid && diagonalValid) {
                counter += 1;
            }
            // move pointers 
            left += 1;
            right += 1;
            if (right == colSize) {
                top += 1;
                bottom += 1;
                left = 0;
                right = 2;
            }
        }

        return counter;
    }

    private boolean checkUnique(int left, int top, int[][] grid) { // O(1)
        Set<Integer> visited = new HashSet<>();
        for (int r = top; r < top + 3; r++) {
            for (int c = left; c < left + 3; c++) {
                if (visited.contains(grid[r][c])) {
                    return false;
                }
                if (grid[r][c] < 1 || grid[r][c] > 9) {
                    return false;
                }
                visited.add(grid[r][c]);
            }
        }
        return true;
    }

    private boolean checkRowSum(int[][] prefixSum, int left, int top, int[][] grid) { // O(1)
        int sum = prefixSum[top][left + 3] - prefixSum[top][left];
        for (int r = top + 1; r < top + 3; r++) {
            int curSum = prefixSum[r][left + 3] - prefixSum[r][left];
            if (curSum != sum) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColSum(int[][] prefixSum, int left, int top, int[][] grid) { // O(1)
        // left is prefixSum starting row 
        int sum = prefixSum[left][top + 3] - prefixSum[left][top];
        for (int c = left + 1; c < left + 3; c++) {
            int curSum = prefixSum[c][top + 3] - prefixSum[c][top];
            if (curSum != sum) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(int left, int right, int top, int[][] grid) { // O(1)
        int row = top, col = left;
        int leftToRightSum = 0;
        for (int i = 0; i < 3; i++) {
            leftToRightSum += grid[row][col];
            row += 1;
            col += 1;
        }
        row = top;
        col = right;
        int rightToLeftSum = 0;
        for (int i = 0; i < 3; i++) {
            rightToLeftSum += grid[row][col];
            row += 1;
            col -= 1;
        }
        return leftToRightSum == rightToLeftSum;
        
    }

    private int[][] drawRowPrefixSum(int[][] grid) { // O(rowSize * colSize)
        int rowSize = grid.length, colSize = grid[0].length;
        int[][] rowPrefix = new int[rowSize][colSize + 1];
        for (int r = 0; r < rowSize; r++) {
            for (int c = 1; c <= colSize; c++) {
                rowPrefix[r][c] = rowPrefix[r][c - 1] + grid[r][c - 1];
            }
        }
        return rowPrefix;
    }

    private int[][] drawColPrefixSum(int[][] grid) { // O(rowSize * colSize)
        int rowSize = grid.length, colSize = grid[0].length;
        int[][] colPrefix = new int[colSize][rowSize + 1];
        for (int c = 0; c < colSize; c++) {
            for (int r = 1; r <= rowSize; r++) {
                colPrefix[c][r] = colPrefix[c][r - 1] + grid[r - 1][c];
            }
        }

        return colPrefix;
    }
}
