package DP.Sequntial;
// Lintcode 29 
// Two solution below 
public class InterleavingString {
    // Solution 1
    // Time: O(aSize * bSize)
    // Space: O(bSize)
    public boolean isInterleave(String s1, String s2, String s3) {
        // convert all 3 Strings to char array for faster indexing 
        char[] A = s1.toCharArray();
        char[] B = s2.toCharArray();
        char[] X = s3.toCharArray();

        int targetSize = X.length;
        int aSize = A.length;
        int bSize = B.length;

        // s3 should be a conbination of all chars from s1 and s2;
        if (targetSize != aSize + bSize) {
            return false;
        }

        // dp[i] represents if A[:i + 1] and B[:j + 1] form valid interleaving elements of X[: i + j]
        boolean[] dp = new boolean[bSize + 1]; // use bSize + 1 as size as the total number of columns is bSize + 1
        for (int i = 0; i <= aSize; i++) {
            for (int j = 0; j <= bSize; j++) {
                if (i == 0 && j == 0) {
                    // the 0 th char from s1 and s2 form valid interleaving 
                    dp[j] = true;
                    continue;
                } else if (i == 0) { 
                    // only checks B array 
                    dp[j] = dp[j - 1] && X[i + j - 1] == B[j - 1];
                } else if (j == 0) {
                    // only checks A array 
                    dp[j] = dp[j] && X[i + j - 1] == A[i - 1];
                } else {
                    dp[j] = dp[j - 1] && X[i + j - 1] == B[j - 1] || dp[j] && X[i + j - 1] == A[i - 1];
                }
            }
        }
        return dp[bSize];
    }
    // Solution 2
    // Time: O(aSize * bSize)
    // Space: O(aSize * bSize)
    public boolean solution2 (String s1, String s2, String s3) {
        // convert all 3 Strings to char array for faster indexing 
        char[] A = s1.toCharArray();
        char[] B = s2.toCharArray();
        char[] X = s3.toCharArray();

        int targetSize = X.length;
        int aSize = A.length;
        int bSize = B.length;

        // s3 should be a conbination of all chars from s1 and s2;
        if (targetSize != aSize + bSize) {
            return false;
        }

        // dp[i][j] represents if A[:i + 1] and B[:j + 1] form valid interleaving elements of X[: i + j]
        boolean[][] dp = new boolean[aSize + 1][bSize + 1];
        for (int i = 0; i <= aSize; i++) {
            for (int j = 0; j <= bSize; j++) {
                if (i == 0 && j == 0) {
                    // the 0 th char from s1 and s2 form valid interleaving 
                    dp[i][j] = true;
                    continue;
                }
                
                if (i > 0 && dp[i - 1][j] && X[i + j - 1] == A[i - 1]) {
                    // if A[: i] and B[:j + 1] is valid, and the current interleaving position 
                    // of i and j in X matches A[i - 1], then it's valid 
                    dp[i][j] = true;
                }

                if (j > 0 && dp[i][j - 1] && X[i + j - 1] == B[j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[aSize][bSize];
    }
}
