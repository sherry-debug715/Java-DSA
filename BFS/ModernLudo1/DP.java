package BFS.ModernLudo1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DP {
    public int modernLudo(int length, int[][] connections) {
        if (length == 1) {
            return 1;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        formGraph(length, connections, graph);
        int[] dp = new int[length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[length] = 0;
        // dp[i] is the min distance from length to i 
        for (int i = length - 1; i > 0; i--) {
            int limit = Math.min(i + 7, length + 1);
            for (int j = i + 1; j < limit; j++) {
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }
            for (int neighbor : graph.get(i)) {
                dp[i] = Math.min(dp[i], dp[neighbor]);
            }
        }
        return dp[1];
    }

    private void formGraph(int length, int[][] connections, Map<Integer, Set<Integer>> graph) {
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<Integer>());
        }

        for (int[] pos: connections) {
            int start = pos[0], end = pos[1];
            graph.get(start).add(end);
        }
    }
}
