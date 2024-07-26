package Graph;

import java.util.Arrays;

// leetcode 1334
// Time: O(N^3)
// Space: O(N^2)
public class GraphWithWeight {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] graph = formGraph(n, edges);
        // floyd-warshall algorithm; 
        for (int k = 0; k < n; k++) { // O(N^3)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ( graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }

        int res = -1, minNeighbors = n; // Start with the maximum possible number of neighbors
        for (int city = 0; city < n; city++) {
            int reachableCities = countNeighbor(graph[city], distanceThreshold);
            // If equal, choose the city with the greatest index
            if (reachableCities <= minNeighbors) {
                minNeighbors = reachableCities;
                res = city;
            }
        }
        return res;
    }

    private int countNeighbor(int[] distances, int distLimit) {
        int qualified = 0;
        for (int distance : distances) {
            if (distance != 0 && distance <= distLimit) {
                qualified += 1;
            }
        }
        return qualified;
    }

    private int[][] formGraph(int n, int[][] edges) { // O(N)
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) { 
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0; // Distance to self is zero
        }

        for (int[] e : edges) {
            int city1 = e[0], city2 = e[1], dist = e[2];
            graph[city1][city2] = dist;
            graph[city2][city1] = dist;
        }

        return graph;
    }
}
