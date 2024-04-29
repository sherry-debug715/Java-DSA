package Graph.KnightShortestPath2;

import java.util.LinkedList;
import java.util.Queue;

public class Bidirection {
    class Point {
        int row;
        int col;
        public Point(int _row, int _col) {
            row = _row;
            col = _col;
        }
    }

    int[][] FORWARD_POS = {
        {1, 2},
        {-1, 2},
        {2, 1},
        {-2, 1}
    };

    int[][] BACKWARD_POS = {
        {-1, -2},
        {1, -2},
        {2, -1},
        {-2, -1}
    };


    public int shortestPath2(boolean[][] grid) {
        // edge case 
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int rowSize = grid.length;
        int colSize = grid[0].length;
        // if start or destination is blocked 
        if (grid[0][0]|| grid[rowSize - 1][colSize - 1]) {
            return -1;
        }

        Queue<Point> forwardQueue = new LinkedList<>();
        Queue<Point> backwardQueue = new LinkedList<>();
        forwardQueue.offer(new Point(0, 0));
        backwardQueue.offer(new Point(rowSize - 1, colSize - 1));

        boolean[][] forwardVisited = new boolean[rowSize][colSize];
        boolean[][] backwardVisited = new boolean[rowSize][colSize];
        forwardVisited[0][0] = true;
        backwardVisited[rowSize - 1][colSize - 1] = true;

        int distance = 0;
        while (!forwardQueue.isEmpty() && !backwardQueue.isEmpty()) {
            distance += 1;
            // check forward queue, if pos is in backward visited 
            if (extendQueue(forwardQueue, forwardVisited, backwardVisited, grid, FORWARD_POS)) {
                return distance;
            }

            distance += 1;
            if (extendQueue(backwardQueue, backwardVisited, forwardVisited, grid, BACKWARD_POS)) {
                return distance;
            }
        }
        return -1;
    }

    private boolean extendQueue(Queue<Point> queue, 
                                boolean[][] visited,
                                boolean[][] otherVisited, 
                                boolean[][] grid, 
                                int[][] direction) {
        int queueSize = queue.size();
        for (int i = 0; i < queueSize; i++) {
            Point curPos = queue.poll();
            // found joint 
            // else find next movement 
            for (int[] pos : direction) {
                int newRow = curPos.row + pos[0];
                int newCol = curPos.col + pos[1];
                if (!isValid(newRow, newCol, grid, visited)) {
                    continue;
                }
                if (otherVisited[newRow][newCol]) {
                    return true;
                }

                queue.offer(new Point(newRow, newCol));
                visited[newRow][newCol] = true;
                
            }
        }
        return false;
    }

    private boolean isValid(int row, int col, boolean[][] grid, boolean[][] visited) {
        if (row < 0 || row >= grid.length) {
            return false;
        }
        if (col < 0 || col >= grid[0].length) {
            return false;
        }
        if (grid[row][col]) {
            return false;
        }
        if (visited[row][col]) {
            return false;
        }
        return true;
    }
}
