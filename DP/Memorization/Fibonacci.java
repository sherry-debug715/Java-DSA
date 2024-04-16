package DP.Memorization;

public class Fibonacci {
    public int fibonacci(int n) {
        if (n < 1) {
            return -1;
        }

        int[] memo = new int[n + 1];
        memo[1] = 0;
        memo[2] = 1;
        return _fibonacci(n, memo);
    }

    private int _fibonacci(int n, int[] memo) {
        if (n == 1 || n == 2) return n - 1;
        if (memo[n] != 0) {
            return memo[n];
        }

        memo[n] = _fibonacci(n - 1, memo) + _fibonacci(n - 2, memo);
        return memo[n];

    }
}
