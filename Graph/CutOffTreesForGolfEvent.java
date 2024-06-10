package Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// Lintcode problem 1092: https://www.lintcode.com/problem/1092/
// Time: O(M*N)
// Space: O(M*N)
public class CutOffTreesForGolfEvent {
    int[][] POS = {
        {-1, 0},
        {1, 0},
        {0, 1},
        {0, -1}
    };
    
    // Time: O(m * n) ^ 2
    // Space: O(m * n)
    public int cutOffTree(List<List<Integer>> forest) {
        // edge case 
        if (forest == null || forest.size() == 0) {
            return -1;
        }
        int rowSize = forest.size();
        int colSize = forest.get(0).size();

        // sort forest from shortest tree to tallest tree 
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int r = 0; r < rowSize; r++) { // O(rowSize * colSize)
            for (int c = 0; c < colSize; c++) {
                if (forest.get(r).get(c) != 0) {
                    minHeap.add(new int[] {r, c, forest.get(r).get(c)});
                }
            }
        }

        // compute the distance from start to each tree sorted by height 
        int sum = 0;
        int[] start = new int[2];
        while (!minHeap.isEmpty()) { // O(rowSize * colSize) when every cell is a tree 
            int[] curTree = minHeap.remove(); 

            int distance = bfs(curTree, start, forest, rowSize, colSize); // O(rowSize * colSize)
            if (distance < 0) {
                return -1;
            }
            sum += distance;
            start[0] = curTree[0];
            start[1] = curTree[1];
        }
        return sum;
    }
    // returns the shortest distance from start to tree 
    private int bfs(int[] tree, int[] start, List<List<Integer>> forest, int rowSize, int colSize) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rowSize][colSize];
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        int distance = 0;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] curPos = queue.poll();
                if (curPos[0] == tree[0] && curPos[1] == tree[1]) {
                    return distance;
                }
                for (int[] pos : POS) {
                    int newR = curPos[0] + pos[0];
                    int newC = curPos[1] + pos[1];
                    if (newR < 0 || newR >= rowSize 
                        || newC < 0 || newC >= colSize 
                        || visited[newR][newC] 
                        || forest.get(newR).get(newC) == 0) {
                        continue;
                    }
                    visited[newR][newC] = true;
                    queue.offer(new int[]{newR, newC});
                }
            }
            distance += 1;
        }
        return -1;
    }
}
