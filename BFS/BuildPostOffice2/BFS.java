package BFS.BuildPostOffice2;
// lintcode 573 

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class GridType {
    static int WALL = 2;
    static int HOUSE = 1;
    static int EMPTY = 0;
}

public class BFS {
        int[][] POS = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    public int shortestDistance(int[][] grid) {
        if (grid == null || grid[0].length == 0) {
            return -1;
        }
        int rowSize = grid.length;
        int colSize = grid[0].length;
        // compute the distance from every EMPTY to houses 
        int minDistance = Integer.MAX_VALUE;
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (grid[r][c] == GridType.EMPTY) {
                    Map<Integer, Integer> distance = bfs(r, c, grid, colSize);
                    int distanceSum = countDistanceSum(distance, grid);
                    minDistance = Math.min(minDistance, distanceSum);
                }
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private int countDistanceSum(Map<Integer, Integer> distance,
                                 int[][] grid) {
        int sum = 0;
        int rowSize = grid.length, colSize = grid[0].length;
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (grid[r][c] == GridType.HOUSE) {
                    int key = r * colSize + c;
                    if (!distance.containsKey(key)) {
                        return Integer.MAX_VALUE;
                    }
                    sum += distance.get(key);
                }
            }
        }
        return sum;
    }

    private Map<Integer, Integer> bfs(int row, int col, int[][] grid, int colSize) {
        Map<Integer, Integer> distance = new HashMap<>();
        distance.put(row * colSize + col, 0);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(row * colSize + col);

        while (!queue.isEmpty()) {
            int curCell = queue.poll();
            int curR = curCell / colSize, curC = curCell % colSize;
            for (int[] pos: POS) {
                int newR = curR + pos[0];
                int newC = curC + pos[1];
                if (valid(newR, newC, distance, grid)) {
                    // only add if it's not a house 
                    if (grid[newR][newC] != GridType.HOUSE) {
                        queue.offer(newR * colSize + newC);
                    }
                    distance.put(newR * colSize + newC, distance.get(curCell) + 1);
                }
            }
        }
        // end while 
        return distance;
    }

    private boolean valid(int r, int c, Map<Integer, Integer> distance, int[][] grid) {
        int rowSize = grid.length, colSize = grid[0].length;
        if (r < 0 || r >= rowSize) return false;
        if (c < 0 || c >= colSize) return false;
        if (distance.containsKey(r * colSize + c)) return false;
        if (grid[r][c] == GridType.WALL) return false;
        return grid[r][c] == GridType.HOUSE || grid[r][c] == GridType.EMPTY;
    }
}
