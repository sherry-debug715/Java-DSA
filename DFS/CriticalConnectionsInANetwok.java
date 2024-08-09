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
        public List<List<Integer>> criticalConnectionsinaNetwork(int n, List<List<Integer>> connections) {
        // step1: form graph 
        List<Integer>[] graph = formGraph(n, connections);
        // step2: sort each edge and add them to a set for O(1) deletion 
        Set<List<Integer>> ans = new HashSet<>();
        for (List<Integer> connect : connections) {
            Collections.sort(connect);
            ans.add(connect);
        }
        // step3: dfs to remove edges with cycles 
        Map<Integer, Integer> distance = new HashMap<>();
        for (int i = 0; i < n; i++) {
            distance.put(i, Integer.MIN_VALUE);
        }
        dfs(0, 0, graph, ans, distance, n);
        return new ArrayList<>(ans);
    }

    private int dfs(int curNode, 
                    int depth, 
                    List<Integer>[] graph, 
                    Set<List<Integer>> ans,  
                    Map<Integer, Integer> distance, 
                    int n) {
        // exit, already visited
        if (distance.get(curNode) >= 0) {
            return distance.get(curNode);
        }

        distance.put(curNode, depth);
        int minDepth = n; // n is the max depth 
        for (int neighbor : graph[curNode]) {
            if (distance.get(neighbor) == depth - 1) { // previous node
                continue;
            }
            int dfsDepth = dfs(neighbor, depth + 1, graph, ans, distance, n);
            if (dfsDepth <= distance.get(curNode)) {
                List<Integer> edge = Arrays.asList(curNode, neighbor);
                Collections.sort(edge);
                ans.remove(edge);
            }

            minDepth = Math.min(minDepth, dfsDepth);
        }
        return minDepth;
    }

    private List[] formGraph(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        // append neighbors 
        for (List<Integer> connect : connections) {
            int server1 = connect.get(0);
            int server2 = connect.get(1);
            graph[server1].add(server2);
            graph[server2].add(server1);
        }
        return graph;
    }
}
