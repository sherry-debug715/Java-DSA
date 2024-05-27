package Graph;
// Time: O(M*N)
// Space: O(M*N)
// Leetcode problem 59: https://leetcode.com/problems/spiral-matrix-ii/
public class SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        int[][] output = new int[n][n];
        int up = 0, left = 0;
        int down = n - 1, right = n - 1;
        int direction = 0;
        int counter = 1;
        while (true) {
            if (direction == 0) { // going right 
                for (int j = left; j < right + 1; j++) {
                    output[up][j] = counter;
                    counter += 1;
                }
                up += 1;
            } else if (direction == 1) { // up to down 
                for (int j = up; j < down + 1; j++) {
                    output[j][right] = counter;
                    counter += 1;
                }
                right -= 1;
            } else if (direction == 2) { // right to left 
                for (int j = right; j >= left; j--) {
                    output[down][j] = counter;
                    counter += 1;
                }
                down -= 1;
            } else { // direction == 3, bottom up
                for (int j = down; j > up - 1; j --) {
                    output[j][left] = counter;
                    counter += 1;
                }
                left += 1;
            }
            direction = (direction + 1) % 4; // 4 for 4 directions;
            if (left > right || up > down) {
                break;
            }
        }
        return output;
    }
}
