package UnionFind.ConnectedComponentInUndirectedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
// lintcode 431 

public class UnionFind {
    class UnionFind {
        Map<Integer, Integer> parents;
        public UnionFind(Set<Integer> allNodes) {
            parents = new HashMap<>();
            for (int n : allNodes) {
                parents.put(n, n);
            }
        }

        private int find(int x) {
            if (parents.get(x) != x) {
                parents.put(x, find(parents.get(x)));
            }
            return parents.get(x);
        }

        private void union(int x, int y) {
            int p1 = find(x);
            int p2 = find(y);
            if (p1 == p2) {
                return;
            }
            parents.put(p1, p2);
        }
    }
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        // Step1 : get all nodes and initiate unionfind.
        // Time: O(V + E)
        Set<Integer> allNodes = new HashSet<>();
        for (UndirectedGraphNode node : nodes) {
            allNodes.add(node.label);
            for (UndirectedGraphNode neighbor : node.neighbors) {
                allNodes.add(neighbor.label);
            }
        }
        UnionFind uf = new UnionFind(allNodes);

        // Step2: buid connection 
        // Time: O(V + E)
        for (UndirectedGraphNode node : nodes) {
            for (UndirectedGraphNode neighbor : node.neighbors) {
                uf.union(node.label, neighbor.label);
            }
        }

        // Step3: group nodes with same root
        // Time: O(VlogV) 
        Map<Integer, List<Integer>> groupByRoot = new HashMap<>();
        organizeGroup(groupByRoot, uf, allNodes);

        return new ArrayList<>(groupByRoot.values());
    }

    private void organizeGroup(Map<Integer, List<Integer>> groupByRoot,
                               UnionFind uf,
                               Set<Integer> allNodes) {
        for (int nodeVal : allNodes) {
            int root = uf.find(nodeVal);
            groupByRoot.putIfAbsent(root, new ArrayList<Integer>());
            groupByRoot.get(root).add(nodeVal);
        }

        for (int root : groupByRoot.keySet()) {
            Collections.sort(groupByRoot.get(root));
        }
    }
}
