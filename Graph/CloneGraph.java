package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class UndirectedGraphNode {
        int label;
        ArrayList<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }
// Time: O(N)
// Space: O(N)
public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // if graph is empty 
        if (node == null) {
            return null;
        }

        Map<UndirectedGraphNode, UndirectedGraphNode> graph = formGraph(node);
        // iterate over graph to form connections 
        for (Map.Entry<UndirectedGraphNode, UndirectedGraphNode> entry: graph.entrySet()) {
            UndirectedGraphNode oldNode = entry.getKey();
            UndirectedGraphNode newNode = entry.getValue();

            for (UndirectedGraphNode neighbor : oldNode.neighbors) {
                newNode.neighbors.add(graph.get(neighbor));
            }
        }

        return graph.get(node);
    }

    private Map<UndirectedGraphNode, UndirectedGraphNode> formGraph(UndirectedGraphNode node) {
        Map<UndirectedGraphNode, UndirectedGraphNode> graph = new HashMap<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();
        // bfs 
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode oldNode = queue.poll();
            UndirectedGraphNode newNode = new UndirectedGraphNode(oldNode.label);
            graph.put(oldNode, newNode);
            for (UndirectedGraphNode n : oldNode.neighbors) {
                if (!visited.contains(n)) {
                    queue.offer(n);
                    visited.add(n);
                }
            }
        }
        return graph;
    }
}
