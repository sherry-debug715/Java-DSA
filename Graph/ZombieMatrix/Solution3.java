package Graph.ZombieMatrix;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class GridType {
    static int WALL = 2;
    static int ZOMBIE = 1;
    static int HUMAN = 0;
}

public class Solution3 {
    int[][] POS = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };
    int totalHuman = 0;
    public int zombie(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int colSize = grid[0].length;
        Set<Integer> visited = new HashSet<>(); // documenting already visited cell 
        Queue<Integer> queue = new LinkedList<>(); // zombie cells 
        populateInitialQueue(grid, queue, visited);
        int days = -1;

        while (!queue.isEmpty()) {
            // System.out.println(queue);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int zombie = queue.poll();
                int zr = zombie / colSize, zc = zombie % colSize;
                for (int[] pos : POS) {
                    int newR = zr + pos[0], newC = zc + pos[1];
                    if (valid(newR, newC, visited, grid)) {
                        int newCell = newR * colSize + newC;
                        queue.offer(newCell);
                        visited.add(newCell);
                        totalHuman -= 1;
                    }
                }
            }
            // end for 
            days += 1;
        }
        return totalHuman == 0 ? days : -1;
    }

    private boolean valid(int r, int c, Set<Integer> visited, int[][] grid) {
        if (r < 0 || r >= grid.length) {
            return false;
        }
        if (c < 0 || c >= grid[0].length) {
            return false;
        }
        if (grid[r][c] != GridType.HUMAN) {
            return false;
        }
        if (visited.contains(r * grid[0].length + c)) {
            return false;
        }
        return true;
    }

    private void populateInitialQueue(int[][] grid, 
                                      Queue<Integer> queue, 
                                      Set<Integer> visited) {
        int rowSize = grid.length, colSize = grid[0].length;
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                int cell = r * colSize + c;
                if (grid[r][c] == GridType.ZOMBIE && !visited.contains(cell)) {
                    queue.offer(cell);
                    visited.add(cell);
                }
                if (grid[r][c] == GridType.HUMAN) {
                    totalHuman += 1;
                }
            }
        }
    }
}
