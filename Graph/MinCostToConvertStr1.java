package Graph;

import java.util.Arrays;

// leetcode 2976
// Time: O(n) + O(1) + O(M)
// Space: O(m)
public class MinCostToConvertStr1 {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] graph = formGraph(original, changed, cost);
        // floyd-warshall algorithm
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }

        long res = 0L;
        int n = source.length();
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();

        for (int i = 0; i < n; i++) {
            if (graph[s[i] - 'a'][t[i] - 'a'] == Integer.MAX_VALUE){
                return -1;
            }
            if (s[i] != t[i]) {
                int costToChange = graph[s[i] - 'a'][t[i] - 'a'];
                if (costToChange == Integer.MAX_VALUE) {
                    return -1L;  // Impossible to change s[i] to t[i]
                }
                res += (long) costToChange;
            }
        }
        return res;
    }

    private int[][] formGraph(char[] original, char[] changed, int[] cost) {
        int[][] graph = new int[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0;
        }

        int n = original.length;
        for (int i = 0; i < n; i++) {
            graph[original[i] - 'a'][changed[i] - 'a'] = Math.min(cost[i], graph[original[i] - 'a'][changed[i] - 'a']);
        }
        return graph;
    }
}
