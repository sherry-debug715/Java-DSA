package DFS.NQueens;
// Lintcode 34 

import java.util.ArrayList;
import java.util.List;

public class Nqueens2 {
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }

        return search(new ArrayList<Integer>(), n);
    }

    private int search(List<Integer> cols, int n) {
        int sum = 0;
        int rowIdx = cols.size();
        if (rowIdx == n) {
            return 1;
        }

        for (int colIdx = 0; colIdx < n; colIdx++) { // for row of rowIdx, check every column to see if it's valid
            if (!isValid(cols, colIdx)) {
                continue;
            }
            cols.add(colIdx);
            sum += search(cols, n);
            cols.remove(cols.size() - 1);
        }
        return sum;
    }

    private boolean isValid(List<Integer> cols, int col) {
        int row = cols.size();
        for (int occupiedRow = 0; occupiedRow < cols.size(); occupiedRow++) {
            int occupiedCol = cols.get(occupiedRow);
            // row + col and row - col is to check the cross attack 
            if (col == occupiedCol || row + col == occupiedRow + occupiedCol || row - col == occupiedRow - occupiedCol) {
                return false;
            }
        }
        return true;
    }
}
