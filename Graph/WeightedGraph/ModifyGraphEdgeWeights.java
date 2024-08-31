package Graph.WeightedGraph;

import java.util.Comparator;
import java.util.List;

// Leetcode 2699
// Time: (O(E + V)logV)
// Space: O(E + V)
public class ModifyGraphEdgeWeights {
        public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        // step1: create adjacency list of the graph 
        List<int[]>[] graph = formGraph(n, edges);
        int[][] distances = new int[n][2];

        // Step2: create distances array where distances[0] represent the shortest distance from source to destination 
        // on the first dijkstra run, distances[1] is the second shortest distance from source to destination after the 
        // second dijkstra run after modifying the differences of target and shortest distance from the first dijakstra run
        distances[source][0] = distances[source][1] = 0;
        for (int i = 0; i < n; i++) {
            if (i == source) {
                continue;
            }
            distances[i][0] = distances[i][1] = Integer.MAX_VALUE;
        }

        // Step3: first dijkstra run 
        dijkstraRun(graph, distances, edges, source, 0, 0);
        // after switch all -1 to 1 (the min weight), if the distance to destination is greater than target, then there is no way 
        int diff = target - distances[destination][0];
        if (diff < 0) {
            return new int[][]{};
        }
        // step4: second dijkstra run
        dijkstraRun(graph, distances, edges, source, 1, diff);
        if (distances[destination][1] < target) {
            return new int[][]{};
        }
        for (int[] edge : edges) {
            if (edge[2] == -1) {
                edge[2] = 1;
            }
        }
        return edges;
    }

    private void dijkstraRun(List<int[]>[] graph,
                             int[][] distances,
                             int[][] edges,
                             int source, 
                             int curRun,
                             int diff) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeap.offer(new int[]{source, 0});

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int node = curr[0], dist = curr[1];
            if (dist > distances[node][curRun]) {
                continue;
            }
            for (int[] neighbor : graph[node]) {
                int neighborNode = neighbor[0], neighborIdx = neighbor[1];
                int weight = edges[neighborIdx][2];
                if (weight == -1) {
                    weight = 1;
                }

                if (curRun == 1 && edges[neighborIdx][2] == -1) {
                    int newWeight = diff + distances[neighborNode][0] - distances[node][1];
                    if (newWeight > weight) {
                        edges[neighborIdx][2] = weight = newWeight;
                    }
                }

                if (distances[neighborNode][curRun] > distances[node][curRun] + weight) {
                    distances[neighborNode][curRun] = distances[node][curRun] + weight;
                    minHeap.offer(new int[]{neighborNode, distances[neighborNode][curRun]});
                }
            }
        }
    }

    private List<int[]>[] formGraph(int n, int[][] edges) {
        // Each entry in the adjacency list will contain two pieces of information: the neighboring node and the index of the edge in the original edge list.
        // This allows us to quickly look up and modify edge weights as needed.
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0], node2 = edges[i][1];
            graph[node1].add(new int[]{node2, i});
            graph[node2].add(new int[]{node1, i});
        }
        return graph;
    }
}
