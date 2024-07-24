package PriorityQueue;
// lintcode 364

import java.util.Comparator;
import java.util.PriorityQueue;
// Time: O(nmlog(nm))
// Space: O(nm);
public class TrappingRainWater2 {
    class Grid {
        int x;
        int y;
        int h;
        public Grid(int _x, int _y, int _h) {
            x = _x;
            y = _y;
            h = _h;
        }
    }
    int[] POS = new int[]{-1, 0, 1, 0, -1};

    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int n = heights.length, m = heights[0].length;
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<Grid> minHeap = new PriorityQueue<>(
            new Comparator<Grid>() {
                public int compare(Grid a, Grid b) {
                    return a.h - b.h;
                }
            }
        );
        // Time: O(nlog(n)
        // add left and right cols to minHeap 
        for (int r = 0; r < n; r ++) {
            minHeap.offer(new Grid(r, 0, heights[r][0]));
            minHeap.offer(new Grid(r, m - 1, heights[r][m - 1]));
            visited[r][0] = true;
            visited[r][m - 1] = true;
        }

        // add first and last rows to minHeap 
        for (int c = 0; c < m; c ++) {
            minHeap.offer(new Grid(0, c, heights[0][c]));
            minHeap.offer(new Grid(n - 1, c, heights[n - 1][c]));
            visited[0][c] = true;
            visited[n - 1][c] = true;
        }
        
        int water = 0;
        while (!minHeap.isEmpty()) {
            Grid curLow = minHeap.poll();
            int r = curLow.x, c = curLow.y;
            for (int i = 0; i < POS.length - 1; i++) {
                int nr = r + POS[i], nc = c + POS[i + 1];
                if (!valid(nr, nc, visited, n, m)) {
                    continue;
                }
                water += Math.max(heights[r][c] - heights[nr][nc], 0);
                heights[nr][nc] = Math.max(heights[r][c], heights[nr][nc]);
                minHeap.offer(new Grid(nr, nc, heights[nr][nc]));
            }
        }
        return water;
    }

    private boolean valid(int r, int c, boolean[][] visited, int n, int m) {
        if (r < 0 || r >= n) return false;
        if (c < 0 || c >= m) return false;
        if (visited[r][c]) return false;
        visited[r][c] = true;
        return true;
    }
}
