```java
import java.util.*;

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int time = 0;
    
    public int minDays(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int count = 0;

        // Count the number of land cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) count++;
            }
        }

        // If there are no land cells, return 0
        if (count == 0) return 0;

        // If there is only one land cell, return 1
        if (count == 1) return 1;

        // Check if the grid is already disconnected
        if (!isConnected(grid)) return 0;

        // Find articulation points
        Set<Integer> articulationPoints = new HashSet<>();
        findArticulationPoints(grid, n, m, articulationPoints);

        // If there is at least one articulation point, return 1
        if (!articulationPoints.isEmpty()) return 1;

        // Otherwise, it takes 2 steps
        return 2;
    }

    private boolean isConnected(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    count++;
                    if (count > 1) return false;
                    dfs(grid, i, j, visited);
                }
            }
        }

        return count == 1;
    }

    private void dfs(int[][] grid, int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        int n = grid.length, m = grid[0].length;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1 && !visited[nx][ny]) {
                dfs(grid, nx, ny, visited);
            }
        }
    }

    private void findArticulationPoints(int[][] grid, int n, int m, Set<Integer> articulationPoints) {
        int[][] disc = new int[n][m]; // discovery time of visited vertices
        int[][] low = new int[n][m]; // earliest visited vertex reachable
        boolean[][] visited = new boolean[n][m];
        int[][] parent = new int[n][m];
        for (int[] row : parent) Arrays.fill(row, -1);

        // Initialize discovery and low arrays
        for (int i = 0; i < n; i++) {
            Arrays.fill(disc[i], -1);
            Arrays.fill(low[i], -1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfsTarjan(grid, i, j, visited, disc, low, parent, articulationPoints);
                }
            }
        }
    }

    private void dfsTarjan(int[][] grid, int u, int v, boolean[][] visited, int[][] disc, int[][] low, int[][] parent, Set<Integer> articulationPoints) {
        visited[u][v] = true;
        disc[u][v] = low[u][v] = ++time;
        int children = 0;

        for (int i = 0; i < 4; i++) {
            int nx = u + dx[i];
            int ny = v + dy[i];

            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == 1) {
                if (!visited[nx][ny]) {
                    children++;
                    parent[nx][ny] = u * grid[0].length + v;
                    dfsTarjan(grid, nx, ny, visited, disc, low, parent, articulationPoints);

                    low[u][v] = Math.min(low[u][v], low[nx][ny]);

                    // If u is root and has more than one child
                    if (parent[u][v] == -1 && children > 1)
                        articulationPoints.add(u * grid[0].length + v);

                    // If u is not root and low value of one of its child is more
                    if (parent[u][v] != -1 && low[nx][ny] >= disc[u][v])
                        articulationPoints.add(u * grid[0].length + v);
                } else if (nx != parent[u][v] / grid[0].length || ny != parent[u][v] % grid[0].length) {
                    low[u][v] = Math.min(low[u][v], disc[nx][ny]);
                }
            }
        }
    }
}

