package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// lintcode 778
// Time: O(rowSize * colSize)
// Space: O(rowSize * colSize)
public class PacificAtlanticWaterFlowBFS {
    static int[][] POS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] matrix;
    int rowSize, colSize;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> output = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return output;
        }
        this.matrix = matrix;
        this.rowSize = matrix.length;
        this.colSize = matrix[0].length;

        boolean[][] reachPacific = new boolean[rowSize][colSize];
        boolean[][] reachAtlantic = new boolean[rowSize][colSize];
        for (int r = 0; r < rowSize; r++) { // column 1 
            bfs(r, 0, reachPacific);
        }

        for (int c = 1; c < colSize; c++) { // row 1
            bfs(0, c, reachPacific);
        }

        for (int r = 0; r < rowSize; r++) { // column last 
            bfs(r, colSize - 1, reachAtlantic);
        }

        for (int c = 0; c < colSize - 1; c++) { // row last
            bfs(rowSize - 1, c, reachAtlantic);
        }

        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (reachPacific[r][c] && reachAtlantic[r][c]) {
                    output.add(Arrays.asList(r, c));
                }
            }
        }
        return output;
    }

    private void bfs(int r, int c, boolean[][] ocean) {
        if (ocean[r][c]) {
            return;
        }
        ocean[r][c] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            for (int[] pos : POS) {
                int newR = curPos[0] + pos[0];
                int newC = curPos[1] + pos[1];
                if (valid(newR, newC, ocean) && matrix[curPos[0]][curPos[1]] <= matrix[newR][newC]) {
                    queue.offer(new int[]{newR, newC});
                    ocean[newR][newC] = true;
                }
            }
        }
    }

    private boolean valid(int r, int c, boolean[][] ocean) {
        if (r < 0 || r >= rowSize) {
            return false;
        }
        if (c < 0 || c >= colSize) {
            return false;
        }
        if (ocean[r][c]) {
            return false;
        }
        return true;
    }
}
