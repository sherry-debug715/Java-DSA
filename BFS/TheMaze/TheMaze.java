package BFS.TheMaze;

public class TheMaze {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return false;
        }
        if (maze[start[0]][start[1]] == 1) {
            return false;
        }
        int[] POS = new int[]{-1, 0, 1, 0, -1};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            // roll to 4 directions 
            for (int i = 0; i < POS.length - 1; i++) {
                int newR = curPos[0] + POS[i], newC = curPos[1] + POS[i + 1];
                while (valid(newR, newC, maze)) {
                    newR += POS[i];
                    newC += POS[i + 1];
                }
                // revert back one step 
                newR -= POS[i];
                newC -= POS[i + 1];
                if (valid(newR, newC, maze) && !visited[newR][newC]) {
                    if (newR == destination[0] && newC == destination[1]) {
                        return true;
                    }
                    queue.offer(new int[] {newR, newC});
                    visited[newR][newC] = true;
                }

            }
        }
        return false;
     }


     private boolean valid(int row, int col, int[][] maze) {
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
