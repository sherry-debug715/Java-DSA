package Graph.MinimumNumberOfDaysToDisconnectIsland;

import java.util.ArrayDeque;
import java.util.Deque;

public class UnionFind {
    int[] POS = new int[] {-1, 0, 1, 0, -1};
    
    public int minDays(int[][] grid) {
        // Check if grid is already disconnected
        int islandCount = countIslands(grid);
        if (islandCount != 1) {
            return 0;
        }
        
        // Try disconnecting by removing one land cell
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    islandCount = countIslands(grid);
                    if (islandCount != 1) {
                        return 1;
                    }
                    grid[i][j] = 1; // revert the change
                }
            }
        }
        
        // If no single cell removal disconnects the grid, it takes 2 days
        return 2;
    }
    
    private int countIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int islandCount = 0;
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    bfs(r, c, visited, grid);
                    islandCount++;
                }
            }
        }
        
        return islandCount;
    }
    
    private void bfs(int row, int col, boolean[][] visited, int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        visited[row][col] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < POS.length - 1; i++) {
                int newR = curr[0] + POS[i];
                int newC = curr[1] + POS[i + 1];
                if (newR >= 0 && newR < m && newC >= 0 && newC < n && grid[newR][newC] == 1 && !visited[newR][newC]) {
                    queue.offer(new int[] {newR, newC});
                    visited[newR][newC] = true;
                }
            }
        }
    }
}
