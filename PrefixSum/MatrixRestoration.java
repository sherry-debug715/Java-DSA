package PrefixSum;
// lintcode 1840: https://www.lintcode.com/problem/1840/?fromId=178&_from=collection
// Time: O(m * n);
// Space: O(m * n);
public class MatrixRestoration {
    public int[][] matrixRestoration(int n, int m, int[][] after) {
        int[][] before = new int[n][m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int up = i - 1 >= 0 ? after[i - 1][j] : 0;
                int left = j - 1 >= 0 ? after[i][j - 1] : 0;
                int upperLeft = i - 1 >= 0 && j - 1 >= 0 ? after[i - 1][j - 1] : 0;
                before[i][j] = after[i][j] - up - left + upperLeft;
            }
        }
        return before;
    }
}
// after - > [
//             [1,3],
//             [4,10]
//           ]

// before - > [
//             [1,2],
//             [3,4]
//            ]

// s = 1
// s = 1 + 2
// s = 1 + 3
// s = 1 + 2 + 3 + 4

// before[i][j]=after[i][j]−after[i−1][j]−after[i][j−1]+after[i−1][j−1]