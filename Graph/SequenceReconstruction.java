package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SequenceReconstruction {
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if (seqs == null || org == null || seqs.length == 0) {
            return false;
        }
        Map<Integer, Set<Integer>> graph = formGraph(seqs);
        ArrayList<Integer> topoOrder = topologicalSort(graph);
        if (topoOrder == null || topoOrder.size() != org.length){
            return false;
        } 
        for (int i = 0; i < org.length; i++) {
            if (org[i] != topoOrder.get(i)) {
                return false;
            }
        }
        return true;
    }

    private ArrayList topologicalSort(Map<Integer, Set<Integer>> graph) {
        // build the indegree map for topologicalSort 
        Map<Integer, Integer> indegree = getIndegree(graph);
        // start bfs traverse 
        Queue<Integer> queue = new LinkedList<>();
        ArrayList topoOrder = new ArrayList<>();
        // iterate over indegree to find the 0 degree number 
        for (Integer num : indegree.keySet()) {
            if (indegree.get(num) == 0) {
                queue.offer(num);
            }
        } 

        while (!queue.isEmpty()) {
            // at any point, if there are more than one number in queue, it means
            // there are more than 1 topo orders 
            if (queue.size() > 1) {
                return null;
            }
            int nextNum = queue.poll();
            topoOrder.add(nextNum);
            for (int n : graph.get(nextNum)) {
                indegree.put(n, indegree.get(n) - 1);
                if (indegree.get(n) == 0) {
                    queue.offer(n);
                }
            }
        }
        return topoOrder;
    }

    private Map<Integer, Integer> getIndegree(Map<Integer, Set<Integer>> graph) {
        Map<Integer, Integer> indegree = new HashMap<>();
        // iterate over graph and set each key to 0 level 
        for (Integer num : graph.keySet()) {
            indegree.put(num, 0);
        }
        // iterate over graph, neighbor set, if neighbor in indegree, increment the count 
        for (Integer num : graph.keySet()) {
            Set<Integer> neighbors = graph.get(num);
            for (Integer n : neighbors) {
                indegree.put(n, indegree.get(n) + 1);
            }
        }

        return indegree;
    }

    private Map<Integer, Set<Integer>> formGraph(int[][] seqs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        // iterate over seqs give each number a hashSet to hold neighbors 
        for (int[] seq : seqs) {
            for (int i = 0; i < seq.length; i++) {
                graph.putIfAbsent(seq[i], new HashSet<>());
            }
        }
        // add first class neighbor to graph 
        for (int[] seq : seqs) {
            for (int i = 1; i < seq.length; i++) {
                graph.get(seq[i - 1]).add(seq[i]);
            }
        }

        return graph;
    }
}
