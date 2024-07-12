package DP.Sequntial.RussianDollEnvelopes;

public class ON^2 {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(a[1], b[1]);
            }
        });

        int n = envelopes.length;
        int[] dp = new int[n];
        int maxLen = 1;
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            int[] iPos = envelopes[i];
            for (int j = 0; j < i; j++) {
                int[] jPos = envelopes[j]; 
                if (iPos[0] > jPos[0] && iPos[1] > jPos[1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
        }

        return maxLen;
    }
}
