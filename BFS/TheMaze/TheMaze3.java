package BFS.TheMaze;
// Lintcode 789

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class MazeType {
    static int WALL = 1;
    static int EMPTY = 0;
}

class Pair {
    int dist;
    String path;
    public Pair(int _dist, String _path) {
        dist = _dist;
        path = _path;
    }

    public boolean lessThanOrEqualTo(Pair p) {
        if (dist != p.dist) {
            return dist < p.dist;
        }
        return path.compareTo(p.path) <= 0;
    }
}

public class TheMaze3 {
    String[] DIRECTION = new String[]{"u", "d", "l", "r"};
    int[] deltaX = new int[]{-1, 1, 0, 0};
    int[] deltaY = new int[]{0, 0, -1, 1};
    
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // edge case 
        if (maze == null || ball == null || hole == null) {
            return "impossible";
        }
        if (maze.length == 0 || maze[0].length == 0 || ball.length == 0 || hole.length == 0) {
            return "impossible";
        }

        int rowSize = maze.length, colSize = maze[0].length;
        Queue<Integer> queue = new LinkedList<>();
        int startPos = ball[0] * colSize + ball[1];
        queue.offer(startPos);
        Map<Integer, Pair> distance = new HashMap<>();
        distance.put(startPos, new Pair(0, ""));

        // bfs 
        while (!queue.isEmpty()) {
            int curPos = queue.poll();
            int curR = curPos / colSize, curC = curPos % colSize;
            Pair curPair = distance.get(curPos);
            // explore all four directions 
            for (int i = 0; i < 4; i++) { // 4 direnctions 
                String d = DIRECTION[i]; // d: current direction 
                // if the previous direction is l, there is no need to go l again 
                if (!curPair.path.isEmpty() && curPair.path.charAt(curPair.path.length() - 1) == d.charAt(0)) {
                    continue;
                }
                int nextPos = kickBall(i, maze, hole[0], hole[1], curR, curC);
                int newR = nextPos / colSize, newC = nextPos % colSize;
                int updatedDist = curPair.dist + Math.abs(newR - curR) + Math.abs(newC - curC); 
                Pair newPair = new Pair(updatedDist, curPair.path + d);
                // if already come across nextPos, see if the distance is shorter 
                if (distance.containsKey(nextPos) && distance.get(nextPos).lessThanOrEqualTo(newPair)) {
                    continue;
                } 
                queue.offer(nextPos);
                distance.put(nextPos, newPair);
            }
        }
        // end while 
        int holePos = hole[0] * colSize + hole[1];
        if (distance.containsKey(holePos)) {
            return distance.get(holePos).path;
        }
        return "impossible";
    }

    private int kickBall(int distIdx, int[][] maze, int holeX, int holeY, int startX, int startY) {
        int rowSize = maze.length, colSize = maze[0].length;
        int dx = deltaX[distIdx], dy = deltaY[distIdx], x = startX, y = startY;
        while ((x != holeX || y != holeY) && isValid(x, y, maze)) {
            x += dx;
            y += dy;
        }
        // x, y could be hole 
        if (x == holeX && y == holeY) {
            return x * colSize + y;
        }

        // return the new distance 
        return (x - dx) * colSize + (y - dy);
    }

    private boolean isValid(int r, int c, int[][] maze) {
        if (r < 0 || r >= maze.length) {
            return false;
        }
        if (c < 0 || c >= maze[0].length) {
            return false;
        }
        return maze[r][c] != MazeType.WALL;
    }
}
