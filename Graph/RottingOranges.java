package Graph;
// Leetcode 994: https://leetcode.com/problems/rotting-oranges/
// Time: O(m * n)
// Space: O(N)
public class RottingOranges {
    class Point {
        int x;
        int y;
        int d;
        public Point(int _x, int _y, int _d) {
            x = _x;
            y = _y;
            d = _d;
        }
    }
    
    int[][] POS = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };
    
    int freshCount = 0;
    public int orangesRotting(int[][] grid) {
        Queue<Point> queue = new LinkedList<>();
        populateQueue(grid, queue);
        int rowSize = grid.length, colSize = grid[0].length;
        // bfs and count down freshCount 
        if (freshCount == 0) {
            return 0;
        }
        while(!queue.isEmpty()) {
            Point curRotton = queue.poll();
            for (int[] newPos : POS) {
                int newR = curRotton.x + newPos[0];
                int newC = curRotton.y + newPos[1];
                if (newR < 0 || newR >= rowSize) continue;
                if (newC < 0 || newC >= colSize) continue;
                if (grid[newR][newC] != 1) continue;
                queue.add(new Point(newR, newC, curRotton.d + 1));
                grid[newR][newC] = 2;
                freshCount -= 1;
                if (freshCount == 0) {
                    return curRotton.d + 1;
                }
            }
        }
        return -1;
    }
    
    private void populateQueue(int[][] grid, Queue<Point> queue) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new Point(r, c, 0));
                }
                if (grid[r][c] == 1) {
                    freshCount += 1;
                }
            }
        }
    }
}
