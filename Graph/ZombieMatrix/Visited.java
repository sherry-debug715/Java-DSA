package Graph.ZombieMatrix;

import java.util.LinkedList;
import java.util.Queue;

public class Visited {
    class Location {
        int row;
        int col;
        int days;
        public Location(int _row, int _col, int _days) {
            row = _row;
            col = _col;
            days = _days;
        }
    }

    int peopleCount = 0;
        public int zombie(int[][] grid) {
        // edge case 
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int rowSize = grid.length, colSize = grid[0].length; 
        // use visited to check for duplicates
        boolean[][] visited = new boolean[rowSize][colSize];
        Queue<Location> queue = new LinkedList<>();
        // populate queue with zombie location while counting people 
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (grid[r][c] == 0) {
                    peopleCount += 1;
                } else if (grid[r][c] == 1) {
                    queue.offer(new Location(r, c, 0));
                }
            }
        }

        if (peopleCount == 0) {
            return -1;
        }
        int[] rows = {0, 0, -1, 1};
        int[] cols = {1, -1, 0, 0};
        // bfs
        while (!queue.isEmpty()) {
            Location curZombie = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = curZombie.row + rows[i];
                int nextCol = curZombie.col + cols[i];
                if (!valid(nextRow, nextCol, visited, grid)) {
                    continue;
                }
                visited[nextRow][nextCol] = true;
                peopleCount -= 1;
                // check if all people are turned 
                if (peopleCount == 0) {
                    return curZombie.days + 1;
                }
                queue.offer(new Location(nextRow, nextCol, curZombie.days + 1));
            }
        }
        return -1;
    }

    private boolean valid(int row, int col, boolean[][] visited, int[][] grid) {
        if (row < 0 || row >= grid.length) return false;
        if (col < 0 || col >= grid[0].length) return false;
        if (visited[row][col]) return false;
        if (grid[row][col] != 0) return false;
        return true;
    }
}
