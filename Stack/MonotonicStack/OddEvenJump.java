package Stack.MonotonicStack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

// Lintcode 1778 
// Time: O(NlogN)
// Space: O(N)
public class OddEvenJump {
    class Pair {
        int idx;
        int val;
        public Pair(int _idx, int _val) {
            idx = _idx;
            val = _val;
        }
    }

    public int oddEvenJumps(int[] a) {
        int n = a.length;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(i, a[i]);
        }
        // odd sort 
        sortPairs(pairs, -1);
        int[] oddSort = getJumps(pairs);

        // even sort 
        sortPairs(pairs, 1);
        int[] evenSort = getJumps(pairs); 

        // dp[i][0] can we jump to n - 1 when we arrive i by even jumps
        // dp[i][1] can we jump to n - 1 when we arrive i by odd jumps
        boolean[][] dp = new boolean[n][2];
        dp[n - 1][0] = dp[n - 1][1] = true; 
        int ans = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (oddSort[i] != -1) {
                dp[i][0] = dp[oddSort[i]][1];
            }
            if (evenSort[i] != -1) {
                dp[i][1] = dp[evenSort[i]][0];
            }
            if (dp[i][0]) {
                ans += 1;
            }
        }
        return ans;
    }

    private void sortPairs(Pair[] pairs, int prefix) {
        Arrays.sort(pairs, new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                if (p1.val == p2.val) {
                    // sort by index in decsending order 
                    return p2.idx - p1.idx;
                }
                return prefix * (p1.val - p2.val);
            }
        });
    }

    private int[] getJumps(Pair[] pairs) {
        int n = pairs.length;
        int[] jumps = new int[n];
        Stack<Integer> stack = new Stack<>(); // decrease, indexes 

        for (int i = 0; i < n; i++) {
            int idx = pairs[i].idx;
            while(!stack.isEmpty() && idx > stack.peek()) {
                stack.pop();
            }
            stack.push(idx);

            // there is no valid jumping point on the right side, then -1 
            if (stack.size() <= 1) {
                jumps[idx] = -1;
            } else {
                jumps[idx] = stack.get(stack.size() - 2);
            }
        }
        return jumps;
    }
}
