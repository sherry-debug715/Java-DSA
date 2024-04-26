package Graph;

import java.util.LinkedList;
import java.util.Queue;

class Distance {
    int row;
    int col;
    int distance;
    public Distance(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}

public class KnightShortestPath {
    int[] deltaX = {1, 1, 2, 2, -1, -1, -2, -2};
    int[] deltaY = {2, -2, 1, -1, 2, -2, 1, -1};

    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        // edge case, 1. if source is a barrier
        if (grid[source.x][source.y] == true) {
            return -1;
        }
        // edge case 2. if destination is a barrier
        if (grid[destination.x][destination.y] == true) {
            return -1;
        }
        // bfs to explore shortest path 
        Queue<Distance> queue = new LinkedList<>();
        // add source to queue to start 
        queue.offer(new Distance(source.x, source.y, 0));

        while (!queue.isEmpty()) {
            Distance curNode = queue.poll();
            // if already at destination, return distance
            if (curNode.row == destination.x && curNode.col == destination.y) {
                return curNode.distance;
            }
            // else keep on exploring, i references indexes from deltaX and deltaY
            for (int i = 0; i < 8; i++) {
                int newRow = curNode.row + deltaX[i];
                int newCol = curNode.col + deltaY[i];
                if (inBound(newRow, newCol, grid)) {
                    queue.offer(new Distance(newRow, newCol, curNode.distance + 1));
                }
            }
        }
        return -1;
    }

    private boolean inBound(int row, int col, boolean[][] grid) {
        if (row < 0 || row >= grid.length) {
            return false;
        }
        if (col < 0 || col >= grid[0].length) {
            return false;
        }
        if (grid[row][col]) {
            return false;
        }
        grid[row][col] = true;
        return true;
    }
}
