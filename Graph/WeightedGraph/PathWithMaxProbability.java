package Graph.WeightedGraph;

import java.util.List;
import java.util.Map;

// Leetcode 1514
public class PathWithMaxProbability {
        public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // Create a graph as an adjacency list
        List<Map<Integer, Double>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashMap<>());
        }
        
        // Populate the graph
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            double prob = succProb[i];
            graph.get(u).put(v, prob);
            graph.get(v).put(u, prob);
        }
        
        // Max heap to store the maximum probability and the corresponding node
        PriorityQueue<double[]> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        maxHeap.offer(new double[] {1.0, start_node});
        
        // Array to store the maximum probability to reach each node
        double[] maxProb = new double[n];
        maxProb[start_node] = 1.0;
        
        while (!maxHeap.isEmpty()) {
            double[] curr = maxHeap.poll();
            double currProb = curr[0];
            int currNode = (int) curr[1];
            
            // If we've reached the end node, return the probability
            if (currNode == end_node) {
                return currProb;
            }
            
            // Iterate over adjacent nodes
            for (Map.Entry<Integer, Double> neighbor : graph.get(currNode).entrySet()) {
                int nextNode = neighbor.getKey();
                double edgeProb = neighbor.getValue();
                double newProb = currProb * edgeProb;
                
                // If the new probability is higher than the previously known probability
                if (newProb > maxProb[nextNode]) {
                    maxProb[nextNode] = newProb;
                    maxHeap.offer(new double[] {newProb, nextNode});
                }
            }
        }
        
        // If end_node is unreachable, return 0
        return 0.0;
    }
}
