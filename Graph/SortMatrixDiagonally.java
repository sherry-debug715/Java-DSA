package Graph;
// Leetcode problem 1329: https://leetcode.com/problems/sort-the-matrix-diagonally/
// Time: O(n * m * log(n));
// space:O(n * m)
public class SortMatrixDiagonally {
    public int[][] diagonalSort(int[][] mat) {
        Map<Integer, Queue<Integer>> diagonalMap = new HashMap<>();
        // map key: col - row, value: numbers from each diagono 
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[0].length; c++) {
                diagonalMap.putIfAbsent((c - r), new PriorityQueue<Integer>());
                diagonalMap.get(c - r).add(mat[r][c]);
            }
        }
        
        // put new sorted value back to mat 
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[0].length; c++) {
                mat[r][c] = diagonalMap.get(c - r).remove();
            }
        }
        
        return mat;  
    }
