package Graph.GraphValidTree;

import java.util.LinkedList;
import java.util.Queue;
// Time: O(max(nodes, edges))
// Space: O(N^2)
public class BFS {
    public boolean validTree(int n, int[][] edges) {
        // edge case, if number of edges + 1 != n, then not a valid tree 
        if (edges.length + 1 != n) {
            return false;
        }

        // queue is used to perform bfs 
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        // visited array is used to check for already visited nodes 
        int[] visited = new int[n];
        visited[0] = 1;
        // counter is used to count for visited nodes, it should equal to 
        // n, total number of nodes in the tree, since root 0 is already visited, 
        // counter start from 1. 
        int counter = 1;
        // graph[i][j] represent relationship between two nodes, if graph[0][1] == 1
        // graph[1][0] must be 1 since edges are undirected 
        int[][] graph = new int[n][n];
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0], y = edges[i][1];
            graph[x][y] = 1;
            graph[y][x] = 1;
        }

        while (!queue.isEmpty()) {
            int curRoot = queue.poll();
            // iterate over the entire row to find connected child  
            for (int c = 0; c < n; c++) {
                if (graph[curRoot][c] == 0) {
                    continue;
                } 
                if (visited[c] != 1) {
                    visited[c] = 1;
                    queue.offer(c);
                    counter += 1;
                }
            }
        }

        return counter == n;
    }
}
