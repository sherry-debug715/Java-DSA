package UnionFind.ConnectedComponentInUndirectedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
// lintcode 431 
// Time: 751ms
// Space: 29.71 mb
public class Bfs {
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        List<List<Integer>> output = new ArrayList<>();

        if (nodes == null) {
            return output;
        }

        Set<UndirectedGraphNode> visited = new HashSet<>();

        for (UndirectedGraphNode node : nodes) {
            ArrayList<Integer> curGraph = new ArrayList<>();
            Queue<UndirectedGraphNode> queue = new LinkedList<>();
            if (!visited.contains(node)) {
                queue.offer(node);
                visited.add(node);
                bfs(queue, curGraph, visited);
                Collections.sort(curGraph);
                output.add(curGraph);
            }
        }
        return output;
    }

    private void bfs(Queue<UndirectedGraphNode> queue,
                     ArrayList<Integer> curGraph,
                     Set<UndirectedGraphNode> visited) {
        while (!queue.isEmpty()) {
            UndirectedGraphNode curNode = queue.poll();
            curGraph.add(curNode.label);
            for (UndirectedGraphNode neighborNode: curNode.neighbors) {
                if (!visited.contains(neighborNode)) {
                    visited.add(neighborNode);
                    queue.offer(neighborNode);
                }
            }
        }
    }
}
