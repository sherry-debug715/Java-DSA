package Graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Map;

// leetcode 2045
//	Time Complexity: O(V + E)
//	Space Complexity: O(V + E)
public class SecondMinTimeToReachDestination {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> graph = formGraph(n, edges);

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 0});
        int[] firstTime = new int[n + 1];
        int[] secondTime = new int[n + 1];
        Arrays.fill(firstTime, Integer.MAX_VALUE);
        Arrays.fill(secondTime, Integer.MAX_VALUE);
        firstTime[1] = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.removeFirst();
            int city = curr[0], curTime = curr[1];
            int nextTime = curTime + time;
            if (curTime / change % 2 == 1) {
                nextTime = (curTime / change + 1) * change + time;
            }

            for (int neighbor : graph.get(city)) {
                // minval 
                if (nextTime < firstTime[neighbor]) {
                    secondTime[neighbor] = firstTime[neighbor];
                    firstTime[neighbor] = nextTime;
                    queue.offer(new int[] {neighbor, nextTime});
                } else if (nextTime > firstTime[neighbor] && nextTime < secondTime[neighbor]) {
                    secondTime[neighbor] = nextTime;
                    queue.offer(new int[] {neighbor, nextTime});
                }
            }

        }
        return secondTime[n] == Integer.MAX_VALUE ? -1 : secondTime[n];
    }

    private Map<Integer, List<Integer>> formGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<Integer>());
        }

        for (int[] e : edges) { // bidirectional
            int p1 = e[0], p2 = e[1];
            graph.get(p1).add(p2);
            graph.get(p2).add(p1);
        }

        return graph;
    }
}
