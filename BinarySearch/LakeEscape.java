package BinarySearch;

import java.util.ArrayDeque;
import java.util.Deque;

// lintcode 1828
// Time:O(n^2)
// Space: O(n^2)
public class LakeEscape {
        int[][] POS = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };
    public boolean lakeEscape(int sideLength, int[][] lakeGrid, int albertRow, int albertColumn, int kunaRow, int kunaColumn) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{albertRow, albertColumn});
        boolean[][] visited = new boolean[sideLength][sideLength];
        visited[albertRow][albertColumn] = true;

        while (!queue.isEmpty()) {
            int[] curPos = queue.removeFirst();
            int curR = curPos[0], curC = curPos[1];
            for (int[] pos : POS) {
                int[] nextPos = findNextPos(curR, curC, pos[0], pos[1], sideLength, lakeGrid);
                int nextR = nextPos[0], nextC = nextPos[1];
                if (nextR == -1 && nextC == -1) { // when sliding into a hole 
                    continue;
                }
                if (visited[nextR][nextC]) {
                    continue;
                }
                if (lakeGrid[nextR][nextC] == 1) { // only stops when reach a snowbank
                    queue.addLast(new int[]{nextR, nextC});
                }
                visited[nextR][nextC] = true;
            }
        } 
        // if albert can't reach the outer most circle of lake grid, then albert can't make on shore 
        if (!canReachBank(visited, sideLength)) {
            return false;
        }
        // since kuna is on a snowbank for sure, if kuna was on albert's way to the shore, then kuna is rescued
        return visited[kunaRow][kunaColumn];
    }

    private boolean canReachBank(boolean[][] visited, int n) {
        for (int i = 0; i < n; i++) {
            if (visited[0][i] || visited[i][0]) {
                return true;
            }
            if (visited[i][n - 1] || visited[n - 1][i]) {
                return true;
            }
        }
        return false;
    }

    private int[] findNextPos(int curR, int curC, int r, int c, int n, int[][] lakeGrid) {
        while (inbound(curR + r, curC + c, n)) {
            curR += r;
            curC += c;
            if (lakeGrid[curR][curC] == -1) {
                return new int[]{-1, -1};
            }
            if (lakeGrid[curR][curC] == 0) {
                continue;
            }
            break;
        }
        return new int[]{curR, curC};
    }

    private boolean inbound(int r, int c, int n) {
        if (r < 0 || r >= n) {
            return false;
        }
        if (c < 0 || c >= n) {
            return false;
        }
        return true;
    }
}
