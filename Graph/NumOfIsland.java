package Graph;
// Time: O(M * N)
// Space: O(1)
public class NumOfIsland {
    public int numIslands(boolean[][] grid) {
        // edge case 
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int output = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c]) {
                    dfs(r, c, grid);
                    output += 1;
                }
            }
        }
        return output;
    }

    private void dfs(int row, int col, boolean[][] grid) {
        if (row < 0 || row >= grid.length) {
            return;
        }
        if (col < 0 || col >= grid[0].length) {
            return;
        }
        if (grid[row][col] == false) {
            return;
        }
        grid[row][col] = false;
        dfs(row - 1, col, grid);
        dfs(row + 1, col, grid);
        dfs(row, col + 1, grid);
        dfs(row, col - 1, grid);
    }
}
