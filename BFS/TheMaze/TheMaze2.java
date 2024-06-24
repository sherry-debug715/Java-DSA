package BFS.TheMaze;
// Lintcode 788
public class TheMaze2 {
    class Point {
        int row;
        int col;
        int distance;
        public Point(int _row, int _col, int _distance) {
            row = _row;
            col = _col;
            distance = _distance;
        }
    }

    int[][] POS = {
        {-1, 0},
        {1, 0},
        {0, 1},
        {0, -1}
    };

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return -1;
        }

        int n = maze.length, m = maze[0].length;
        int[][] distance = new int[n][m];
        for (int[] d : distance) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(start[0], start[1], 0));

        while (!queue.isEmpty()) {
            Point curPos = queue.poll();

            for (int[] nextP : POS) {
                int nextR = nextP[0] + curPos.row;
                int nextC = nextP[1] + curPos.col;
                while (isValid(nextR, nextC, maze)) {
                    nextR += nextP[0];
                    nextC += nextP[1];
                }
                // hit wall 
                nextR -= nextP[0];
                nextC -= nextP[1];
                int nextDist = Math.abs(nextR - curPos.row) + Math.abs(nextC - curPos.col) + curPos.distance;
                if (isValid(nextR, nextC, maze) && distance[nextR][nextC] > nextDist) {
                    queue.offer(new Point(nextR, nextC, nextDist));
                    distance[nextR][nextC] = nextDist;
                }
            }
        }
        if (distance[destination[0]][destination[1]] == Integer.MAX_VALUE) {
            return -1;
        }
        return distance[destination[0]][destination[1]];
    }

    private boolean isValid(int row, int col, int[][] maze) {
        if (row < 0 || row >= maze.length) {
            return false;
        }
        if (col < 0 || col >= maze[0].length) {
            return false;
        }
        if (maze[row][col] == 1) {
            return false;
        }
        return true;
    }
}