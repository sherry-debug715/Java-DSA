package DFS;
// Lintcode 1271 
//In Java, because of low stack, when you get 57% passed and then RE, you can think that you have passed the problem.
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CriticalConnectionsInANetwok {
    // Time: O(V + E) where V is the number of vertices (nodes) and E is the number of edges. 
    // The DFS runs in O(V + E) time, and sorting each edge takes O(E log E). 
    // Since sorting happens once during setup, it does not impact the DFS traversal.

    // Space: O(V + E) because you store the adjacency list (O(E)) and auxiliary data such as the distance array (O(V)) and the set of connections (O(E)).

    public List<List<Integer>> criticalConnectionsinaNetwork(int n, List<List<Integer>> connections) {
        // form adjacency list 
        List<Integer>[] graph = formGraph(n, connections);
        // remove noncreditcal connection from connections, use Set for O(1) deletion 
        // returned critical connection has to be sorted 
        Set<List<Integer>> nonCreditcal = new HashSet<>();
        for (List<Integer> c : connections) {
            Collections.sort(c);
            nonCreditcal.add(c);
        }

        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MIN_VALUE);
        dfs(graph, nonCreditcal, distance, 0, 0, n);
        return new ArrayList<>(nonCreditcal);
    }
    // DFS is used to detect cycles in the graph. Any edge part of a cycle is non-critical, 
    //  as its removal would not increase the number of connected components.
    private int dfs(List<Integer>[] graph,
                     Set<List<Integer>> nonCreditcal,
                     int[] distance,
                     int curNode,
                     int curDistance,
                     int n) {
        if (distance[curNode] != Integer.MIN_VALUE) {
            return distance[curNode];
        }
        // document the distance of current node from starting node
        distance[curNode] = curDistance;
        // minDistance references the min distance between current node and all it's neighbor nodes 
        int minDistance = n; 
        // explore all neighbor nodes looking for cycle in graph 
        for (int neighbor : graph[curNode]) {
            // if neighbor is the prev visited node of curNode, continue 
            if (distance[neighbor] + 1 == curDistance) {
                continue;
            }
            int neighborDistance = dfs(graph, nonCreditcal, distance, neighbor, curDistance + 1, n);
            // If neighborDistance is less than or equal to curDistance, 
            // a cycle exists, meaning the edge is non-critical.
            if (neighborDistance <= distance[curNode]) {
                List<Integer> edge = Arrays.asList(neighbor, curNode);
                Collections.sort(edge);
                nonCreditcal.remove(edge);
            }
            minDistance = Math.min(minDistance, neighborDistance);
        } 
        return minDistance;
    }

    private List<Integer>[] formGraph(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (List<Integer> nodes : connections) {
            graph[nodes.get(0)].add(nodes.get(1));
            graph[nodes.get(1)].add(nodes.get(0));
        }
        return graph;
    }
}
