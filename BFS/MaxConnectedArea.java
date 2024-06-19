package BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// lintcode 261 
// Time: O(rowSize * colSize)
// Space: O(N)
public class MaxConnectedArea {
    // 1. find connected area and group them together by index, calculate group size
    // 2. use a hashMap to store each group size, key is index 
    // 3. for every valid 0's that's adjacent to 1's in matrix, calculate the size
    // of combined area if converting the 0 to 1. 
    int[][] POS = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    public int maxArea(int[][] matrix) {
        int index = 1;
        Map<Integer, Integer> groupAreaMap = new HashMap<>(); // index is position of a cell
        Map<Integer, Integer> sizeOfGroups = new HashMap<>(); // key is group index 
        Set<Integer> visited = new HashSet<>(); // track already visited cells 
        int rowSize = matrix.length, colSize = matrix[0].length;
        int maxArea = 0; // need to calculate maxArea when grouping 1s, this case is for a matrix
        // with all 1's.
        // search for connected area, group them, calculate each group size 
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (matrix[r][c] == 1 && !visited.contains(r * colSize + c)) {
                    int area = bfs(matrix, groupAreaMap, visited, r, c, index);                    
                    maxArea = Math.max(maxArea, area);
                    sizeOfGroups.put(index, area);
                    index += 1;
                }
            }
        } 
        // end for 
        // clear visited set, because we need to look for adjacent 1's to a 0  
        visited.clear();
        // look for 0's that's adjancent to group 
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (matrix[r][c] == 0) {
                    int totalArea = 1; // change the 0 to 1.
                    // because a valid neighboring 1 to a 0 could belong to the same group,
                    // use a set to remove duplicates, and compute totalAreaSize outside the loop.
                    Set<Integer> adjacentOneIndex = new HashSet<>();
                    for (int[] pos : POS) {
                        int newR = r + pos[0], newC = c + pos[1];
                        if (valid(newR, newC, matrix, visited)) { // found adjacent 1
                            int newCell = newR * colSize + newC;
                            // find it's index then add to adjacentOneIndex;
                            int idx = groupAreaMap.get(newCell);
                            adjacentOneIndex.add(idx);
                        }
                    }
                    // compute totalArea 
                    for (int idx : adjacentOneIndex) {
                        totalArea += sizeOfGroups.get(idx);
                    }
                    maxArea = Math.max(maxArea, totalArea);
                }
            }
        }
        // END FOR 
        return maxArea;
    }

    private int bfs(int[][] matrix,
                     Map<Integer, Integer> groupAreaMap,
                     Set<Integer> visited,
                     int row,
                     int col,
                     int index) {
        int colSize = matrix[0].length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(row * colSize + col); 
        groupAreaMap.put(row * colSize + col, index);
        visited.add(row * colSize + col);
        int area = 1;
        while (!queue.isEmpty()) {
            int curCell = queue.poll();
            int curR = curCell / colSize, curC = curCell % colSize;
            for (int[] pos : POS) {
                int newR = curR + pos[0], newC = curC + pos[1];
                if (valid(newR, newC, matrix, visited)) {
                    int newCell = newR * colSize + newC;
                    queue.offer(newCell);
                    visited.add(newCell);
                    groupAreaMap.put(newCell, index);
                    area += 1;
                }
            }
        }
        // end while 
        return area;
    }

    private boolean valid(int r, int c, int[][] matrix, Set<Integer> visited) {
        int rowSize = matrix.length, colSize = matrix[0].length;
        if (r < 0 || r >= rowSize) return false;
        if (c < 0 || c >= colSize) return false;
        if (matrix[r][c] == 0) return false;
        if (visited.contains(r * colSize + c)) return false;
        return true;
    }
}
