package Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// Leetcode 310 
public class MinimumHeightTrees {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            res.add(0);
            return res;
        }
        List<Integer>[] graph = buildGraph(n, edges); // adjacency list 
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int neighbor : graph[i]) {
                indegree[neighbor] += 1;
            }
        }

        int remainNodes = n;
        Deque<Integer> queue = new ArrayDeque<>();
        // look for the leaf nodes from indegree, leaf nodes should have value of 1 
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 1) {
                queue.addLast(i);
            }
        }

        // bfs 
        while (remainNodes > 2) {
            int size = queue.size();
            remainNodes -= size;
            for (int i = 0; i < size; i++) {
                int node = queue.removeFirst();
                for (int nb : graph[node]) {
                    indegree[nb] -= 1;
                    if (indegree[nb] == 1) {
                        queue.addLast(nb);
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            res.add(queue.removeFirst());
        }

        return res;
    }


    private List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        // undirected nodes 
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        return graph;
    }
}
