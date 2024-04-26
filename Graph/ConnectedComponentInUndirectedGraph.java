package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
// Time: O(N);
// Space: O(N);
public class ConnectedComponentInUndirectedGraph {
    // iterate over list of nodes, for each node that's not in the visited set
    // perform a bfs traverse to collect connected graph. 
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        List<List<Integer>> output = new ArrayList<>();
        if (nodes == null || nodes.size() == 0) {
            return output;
        }

        // visited is used to check for loops 
        Set<UndirectedGraphNode> visited = new HashSet<>();

        for (UndirectedGraphNode node : nodes) {
            List<Integer> graph = new ArrayList<>();
            Queue<UndirectedGraphNode> queue = new LinkedList<>();
            if (visited.contains(node)) {
                continue;
            }
            queue.offer(node);
            visited.add(node);
            // bfs to traverse through current graph 
            while (!queue.isEmpty()) {
                UndirectedGraphNode curNode = queue.poll();
                graph.add(curNode.label);

                for (UndirectedGraphNode nei : curNode.neighbors) {
                    if (visited.contains(nei)) {
                        continue;
                    }
                    visited.add(nei);
                    queue.offer(nei);
                }
            }
            // add current graph to output 
            Collections.sort(graph);
            output.add(graph);
        }

        return output; 
    }
}
