package Graph;

import java.util.Comparator;
import java.util.PriorityQueue;
// Leetcode 778 

public class SwimInRisingWater {
    int[][] POS = {
        {0, 1},  // Right
        {1, 0},  // Down
        {0, -1}, // Left
        {-1, 0}  // Up
    };
    
    public int swimInWater(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        queue.offer(new int[]{grid[0][0], 0, 0});
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        int time = 0;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currTime = curr[0], r = curr[1], c = curr[2];
            time = Math.max(time, currTime);
            // System.out.println(grid[r][c]);
            if (r == n - 1 && c == m - 1) {
                return time;
            }
            for (int[] pos : POS) {
                int newR = r + pos[0], newC = c + pos[1];
                if (!valid(newR, newC, visited, grid)) {
                    continue;
                }
                visited[newR][newC] = true;
                queue.offer(new int[]{grid[newR][newC], newR, newC});
            }
        }
        
        return -1;
    }
    
    private boolean valid(int r, int c, boolean[][] visited, int[][] grid) {
        int n = grid.length, m = grid[0].length;
        if (r < 0 || r >= n) {
            return false;
        }
        if (c < 0 || c >= m) {
            return false;
        }
        if (visited[r][c]) {
            return false;
        }
        return true;
    }
}
