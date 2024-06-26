package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// lintcode 1469 
// Time: o(n)
// Space: O(n)
public class LongestPathOnTheTree {
    // 1. form undirected graph 
    // 2. bfs to return a list of tree nodes by each level 
    // 3. compute longest path.
    public int longestPath(int n, int[] starts, int[] ends, int[] lens) {
        HashMap<Integer, Integer>[] graph = formGraph(starts, ends, lens, n); // O(N)
        List<Integer> levelOrder = bfs(graph, n); // O(N)
        return findMaxPath(graph, levelOrder, n); // O(N)
    }

    private int findMaxPath(HashMap<Integer, Integer>[] graph, List<Integer> levelOrder, int len) {
        int res = Integer.MIN_VALUE;
        Map<Integer, Integer> longestLeafToRoot = new HashMap<>();

        for (int i = len - 1; i >= 0; i--) {
            int maxPath = Integer.MIN_VALUE;
            int secondMaxPath = Integer.MIN_VALUE;
            int curNode = levelOrder.get(i);
            for (int parent : graph[curNode].keySet()) {
                // 打擂台
                if (!longestLeafToRoot.containsKey(parent)) { // when curNode is leaf nodes
                    continue; 
                }
                if (graph[curNode].get(parent) + longestLeafToRoot.get(parent) > maxPath) {
                    secondMaxPath = maxPath;
                    maxPath = graph[curNode].get(parent) + longestLeafToRoot.get(parent);
                } else if (graph[curNode].get(parent) + longestLeafToRoot.get(parent) > secondMaxPath) {
                    secondMaxPath = graph[curNode].get(parent) + longestLeafToRoot.get(parent);
                }
            }
            longestLeafToRoot.put(curNode, Math.max(maxPath, 0)); // 0 is for leaf nodes 
            res = Math.max(res, longestLeafToRoot.get(curNode));
            if (secondMaxPath != Integer.MIN_VALUE) {
                res = Math.max(res, maxPath + secondMaxPath);
            }
        }
        return res;
    }

    private List<Integer> bfs(HashMap<Integer, Integer>[] graph, int n) {
        boolean[] visited = new boolean[n];
        List<Integer> queue = new ArrayList<>();
        visited[0] = true;
        queue.add(0);
        int idx = 0;
        while (idx < queue.size()) {
            int curr = queue.get(idx);
            for (int key : graph[curr].keySet()) {
                if (visited[key]) {
                    continue;
                }
                visited[key] = true;
                queue.add(key);
            }
            idx += 1;
        }

        return queue;
    }

    private HashMap<Integer, Integer>[] formGraph(int[] starts, int[] ends, int[] lens, int n) {
        HashMap<Integer, Integer>[] graph = new HashMap[n];
        for (int i = 0; i < n; i++) { // O(N)
            graph[i] = new HashMap<>();
        }

        for (int i = 0; i < n - 1; i++) { // O(n)
            int start = starts[i];
            int end = ends[i];
            int dis = lens[i];
            graph[start].put(end, dis);
            graph[end].put(start, dis);
        }
        
        return graph;
    }
}
