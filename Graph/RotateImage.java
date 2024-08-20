package Graph;
// Leetcode 48 
public class RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[] newOrder = new int[n * m];
        int idx = 0; 
        
        for (int c = 0; c < m; c++) {
            for (int r = n - 1; r >= 0; r--) {
                newOrder[idx++] = matrix[r][c];
            }
        }
        
        // mutate matrix with new order 
        for (int i = 0; i < newOrder.length; i++) {
            int r = i / m, c = i % m;
            matrix[r][c] = newOrder[i];
        }
    }
}
