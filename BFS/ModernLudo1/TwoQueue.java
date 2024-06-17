package BFS.ModernLudo1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TwoQueue {
    public int modernLudo(int length, int[][] connections) {
        if (length == 1) {
            return 1;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        formGraph(length, connections, graph);
        Map<Integer, Integer> distance = new HashMap<>();
        List<Integer> mainQueue = new ArrayList<>();
        distance.put(1, 0);
        mainQueue.add(1);

        while (!mainQueue.isEmpty()) {
            List<Integer> tempQueue = new ArrayList<>();
            // iterate through current level of mainQueue and add flying steps to mainQueue 
            for (int i = 0; i < mainQueue.size(); i++) {
                int curLen = mainQueue.get(i);
                for (int neighbor : graph.get(curLen)) {
                    if (distance.containsKey(neighbor)) {
                        continue;
                    }
                    mainQueue.add(neighbor);
                    distance.put(neighbor, distance.get(curLen));
                }
            }

            // add the next level 
            for (int i = 0; i < mainQueue.size(); i++) {
                int node = mainQueue.get(i);
                int limit = Math.min(node + 7, length + 1);
                for (int nextN = node + 1; nextN < limit; nextN++) {
                    if (distance.containsKey(nextN)) {
                        continue;
                    }
                    tempQueue.add(nextN);
                    distance.put(nextN, distance.get(node) + 1);
                }
            }
            mainQueue = tempQueue;
        }

        return distance.get(length);
    }

    private void formGraph(int length, int[][] connections, Map<Integer, Set<Integer>> graph) {
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<Integer>());
        }

        for (int[] pos: connections) {
            int start = pos[0], end = pos[1];
            graph.get(start).add(end);
        }
    }
}
