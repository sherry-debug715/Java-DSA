package Graph;
// leetcode 1905

import java.util.ArrayDeque;
import java.util.Deque;

public class CountSubIslands {
    int[] POS = new int[]{-1, 0, 1, 0, -1};
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        // union all islands from grid1
        int m = grid1.length, n = grid1[0].length;
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid2[r][c] == 1 && !visited[r][c]) {
                    if (grid1[r][c] != 1) {
                        continue;
                    }
                    visited[r][c] = true;
                    if (isSubIsland(r, c, visited, grid2, grid1)) {
                        res += 1;
                    }
                }
            }
        }
        return res;
    }

    private boolean isSubIsland(int row, int col, boolean[][] visited, int[][] grid, int[][] grid1) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[] {row, col});
        boolean subIsland = true;
        
        while (!queue.isEmpty()) {
            int[] currPos = queue.poll();
            int curR = currPos[0], curC = currPos[1];
            for (int i = 0; i < POS.length - 1; i++) {
                int newR = currPos[0] + POS[i];
                int newC = currPos[1] + POS[i + 1];
                if (valid(newR, newC, grid, visited)) { 
                    if (grid1[newR][newC] != 1) {
                        subIsland = false;
                    }
                    queue.offer(new int[]{newR, newC});
                }
            }
        }
        return subIsland;
    }

    private boolean valid(int row, int col, int[][] grid, boolean[][] visited) {
        if (row < 0 || row >= grid.length) {
            return false;
        }
        if (col < 0 || col >= grid[0].length) {
            return false;
        }
        if (grid[row][col] != 1 || visited[row][col]) {
            return false;
        }
        visited[row][col] = true;
        return true;
    }
}
