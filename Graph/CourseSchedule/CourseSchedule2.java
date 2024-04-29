package Graph.CourseSchedule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] topoOrder = new int[numCourses];
        Map<Integer, HashSet<Integer>> graph = formGraph(numCourses, prerequisites);
        Map<Integer, Integer> indegrees = formDegree(graph); 

        // bfs 
        Queue<Integer> queue = new LinkedList<>();
        for (int c : indegrees.keySet()) {
            if (indegrees.get(c) == 0) {
                queue.offer(c);
            }
        }

        int idx = 0;
        while (!queue.isEmpty()) {
            int curCourse = queue.poll();
            topoOrder[idx++] = curCourse;
            for (int n : graph.get(curCourse)) {
                indegrees.put(n, indegrees.get(n) - 1);
                if (indegrees.get(n) == 0) {
                    queue.offer(n);
                }
            }
        }

        // if can't finsh all courses 
        if (idx != numCourses) {
            return new int[0];
        }
        return topoOrder;
    }

    private Map<Integer, Integer> formDegree(Map<Integer, HashSet<Integer>> graph) {
        Map<Integer, Integer> indegrees = new HashMap<>();

        for (int c : graph.keySet()) {
            indegrees.putIfAbsent(c, 0);
            for (int n : graph.get(c)) {
                indegrees.put(n, indegrees.getOrDefault(n, 0) + 1);
            }
        }

        return indegrees;
    }

    private Map<Integer, HashSet<Integer>> formGraph(int num, int[][] prerequisites) {
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();
        for (int n = 0; n < num; n++) {
            graph.putIfAbsent(n, new HashSet());
        }

        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
        }
        return graph;
    }
}
