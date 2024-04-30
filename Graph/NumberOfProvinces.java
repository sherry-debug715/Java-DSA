package Graph;
// Leetcode problem 547: https://leetcode.com/problems/number-of-provinces/description/
public class NumberOfProvinces {
    int counter = 0;
    int[] union = null;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length, colSize = isConnected[0].length;
        union = new int[n];
        counter = n;
        for (int i = 0; i < n; i++) {
            union[i] = i;
        }
        
        // build connection 
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < colSize; col++) {
                if (isConnected[row][col] == 1) {
                    connect(row, col);
                }
            }
        }
        return counter;
    }  
    
    private void connect(int row, int col) {
        if (row == col) {
            return;
        }
        
        int findRow = findIdx(row);
        int findCol = findIdx(col);
        if (findRow != findCol) {
            union[findRow] = findCol;
            counter -= 1;
        }
    }
    
    private int findIdx(int idx) {
        if (union[idx] == idx) {
            return idx;
        }
        
        return union[idx] = findIdx(union[idx]);
    }
}
