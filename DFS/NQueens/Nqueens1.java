package DFS.NQueens;
// lintcode 33 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nqueens1 {
    // Time: O(N!) backtracking,  each row has N possibility 
     // Space: O(N!) storage of all possible solutions and the recursive stack.
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> output = new ArrayList<>();
        if (n <= 0) {
            return output;
        }

        search(n, new ArrayList<Integer>(), output);
        return output;
    }

    private void search(int size, List<Integer> cols, List<List<String>> output) {
        int rowIdx = cols.size();
        if (rowIdx == size) {
            output.add(drawChestBoard(cols, size)); //Time:  O(N * N) Space: O(N * N)
            return;
        }

        for (int colIdx = 0; colIdx < size; colIdx ++) {
            if (!isValid(cols, colIdx)) { //Time: O(occupied rows) Space: O(1)
                continue;
            }
            cols.add(colIdx);
            search(size, cols, output); // Space: O(n) stack space, Time: O(N!) each row has N possibility 
            cols.remove(cols.size() - 1);
        }
    }

    private List<String> drawChestBoard(List<Integer> cols, int size) {
        List<String> chestBoard = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            char[] curRow = new char[size];
            Arrays.fill(curRow, '.');
            curRow[cols.get(i)] = 'Q';
            chestBoard.add(new String(curRow));
        }
        return chestBoard;
    }

    private boolean isValid(List<Integer> cols, int colIdx) {
        int row = cols.size();

        for (int occupiedRow = 0; occupiedRow < cols.size(); occupiedRow ++) {
            int occupiedCol = cols.get(occupiedRow);
            if (occupiedCol == colIdx || row + colIdx == occupiedRow + occupiedCol || row - colIdx == occupiedRow - occupiedCol) {
                return false;
            }
        }
        return true;
    }
}
