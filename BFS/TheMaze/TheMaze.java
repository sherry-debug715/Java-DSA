package BFS.TheMaze;

public class TheMaze {
    int[][] POS = {
        {-1, 0},
        {1, 0},
        {0, 1},
        {0, -1}
    };

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return false;
        }
        if (maze[start[0]][start[1]] == 1) {
            return false;
        }
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            if (curPos[0] == destination[0] && curPos[1] == destination[1]) {
                return true;
            }
            for (int[] nextPos : POS) {
                int newR = curPos[0] + nextPos[0];
                int newC = curPos[1] + nextPos[1];
                while (isValid(newR, newC, maze)) {
                    newR += nextPos[0];
                    newC += nextPos[1];
                }
                // hit wall, retrack a row and col 
                newR -= nextPos[0];
                newC -= nextPos[1];
                if (isValid(newR, newC, maze) && !visited[newR][newC]) {
                    queue.offer(new int[]{newR, newC});
                    visited[newR][newC] = true;
                }
            }
        }
        return false;
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
