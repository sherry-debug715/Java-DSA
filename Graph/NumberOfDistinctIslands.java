package Graph;

import java.util.HashSet;
import java.util.Set;

public class NumberOfDistinctIslands {
    int[] POS = new int[] {-1, 0, 1, 0, -1};
    boolean[][] visited = null;
    String islandShape = "";
    int sr = 0, sc = 0; // the coordinates of the upper left corner of each island. 
    int rowSize;
    int colSize;

    public int numberofDistinctIslands(int[][] grid) {
        rowSize = grid.length;
        colSize = grid[0].length; 
        visited = new boolean[rowSize][colSize];
        Set<String> distinctLands = new HashSet<>();

        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (grid[r][c] == GridType.ISLAND && !visited[r][c]) {
                    sr = r;
                    sc = c;
                    islandShape = "";
                    dfs(r, c, grid);
                    visited[r][c] = true;
                    distinctLands.add(islandShape);
                }
            }
        }
        return distinctLands.size();
    }

    private void dfs(int r, int c, int[][] grid) {
        // visited[r][c] = true;
        islandShape += (r - sr) + " " + (c - sc);
        for (int i = 0; i < POS.length - 1; i++) {
            int newR = r + POS[i];
            int newC = c + POS[i + 1];
            if (newR >= 0 && newR < rowSize && newC >= 0 && newC < colSize && !visited[newR][newC] && grid[newR][newC] == GridType.ISLAND) {
                visited[newR][newC] = true;
                dfs(newR, newC, grid);
            }
        }
    }
}
