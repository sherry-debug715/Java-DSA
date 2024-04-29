package Graph.CourseSchedule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
// Lintcode problem 615: https://www.lintcode.com/problem/615/?fromId=161&_from=collection
// Time: O(V + E)
// Space: O(N)
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return false;
        }

        Map<Integer, HashSet<Integer>> graph = formGraph(numCourses, prerequisites);
        Map<Integer, Integer> indegrees = formDegree(graph);
        Queue<Integer> queue = new LinkedList<>();
        // if not one course needs to be taken first return false 
        if (!indegrees.containsValue(0)) {
            return false;
        }
        // iterate over indegrees to loop for 0 degree course 
        for (int course : indegrees.keySet()) {
            if (indegrees.get(course) == 0) {
                queue.offer(course);
            }
        }

        // bfs 
        int courseCounter = 0;
        while (!queue.isEmpty()) {
            int curCourse = queue.poll();
            courseCounter += 1;
            // iterate over the neighbor courses of curCourse 
            for (int c : graph.get(curCourse)) {
                indegrees.put(c, indegrees.get(c) - 1);
                if (indegrees.get(c) == 0) {
                    queue.offer(c);
                }
            }
        }
        // if there are courses left not studied, return false 
        return courseCounter == numCourses;
    }

    private Map<Integer, HashSet<Integer>> formGraph(int numCourses,
                                                     int[][] prerequisites) {
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            graph.putIfAbsent(i, new HashSet());
        }

        for (int[] c : prerequisites) {
            graph.get(c[1]).add(c[0]);
        }
        return graph;
    }

    private  Map<Integer, Integer> formDegree(Map<Integer, HashSet<Integer>> graph) {
        Map<Integer, Integer> indegrees = new HashMap<>();
        for (int key : graph.keySet()) {
            indegrees.putIfAbsent(key, 0);
            for (int n : graph.get(key)) {
                indegrees.putIfAbsent(n, 0);
                indegrees.put(n, indegrees.get(n) + 1);
            }
        }
        return indegrees;

    }
}
