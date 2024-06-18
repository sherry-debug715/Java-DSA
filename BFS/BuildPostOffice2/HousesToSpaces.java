package BFS.BuildPostOffice2;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class GridType {
    static int WALL = 2;
    static int HOUSE = 1;
    static int EMPTY = 0;
}
// Time: O(house * m * n)
public class HousesToSpaces {
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
        // distSum is to store the sum of steps from every house to a empty space
        Map<Integer, Integer> distSum = new HashMap<>();
        // key: empty cell; value: the number of houses that could reach the cell.
        Map<Integer, Integer> reachableCount = new HashMap<>();

        int houseCount = 0;
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (grid[r][c] != GridType.HOUSE) {
                    continue;
                }
                bfs(r, c, grid, distSum, reachableCount);
                houseCount += 1;
            }
        }
        // to be edited 
        int minDis = Integer.MAX_VALUE;
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (grid[r][c] == GridType.EMPTY) {
                    int cell = r * colSize + c;
                    if (!distSum.containsKey(cell) || !reachableCount.containsKey(cell)) {
                        continue;
                    }
                    if (reachableCount.get(cell) != houseCount) {
                        continue;
                    }
                    minDis = Math.min(minDis, distSum.get(cell));
                }
            }
        }
        return minDis == Integer.MAX_VALUE ? -1 : minDis;

    }

    private void bfs(int row, 
                     int col, 
                     int[][] grid, 
                     Map<Integer, Integer> distSum,
                     Map<Integer, Integer> reachableCount) {

        int colSize = grid[0].length;
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
                int newCell = newR * colSize + newC;
                if (valid(newR, newC, distance, grid)) {
                    queue.offer(newCell);
                    distance.put(newCell, distance.get(curCell) + 1);
                    if (!reachableCount.containsKey(newCell)) {
                        distSum.put(newCell, 0);
                        reachableCount.put(newCell, 0);
                    }
                    distSum.put(newCell, distSum.get(newCell) + distance.get(newCell));
                    reachableCount.put(newCell, reachableCount.get(newCell) + 1);
                }
            }
        }
    }

    private boolean valid(int r, int c, Map<Integer, Integer> distance, int[][] grid) {
        int rowSize = grid.length, colSize = grid[0].length;
        if (r < 0 || r >= rowSize) return false;
        if (c < 0 || c >= colSize) return false;
        if (distance.containsKey(r * colSize + c)) return false;
        return grid[r][c] == GridType.EMPTY;
    }
}
