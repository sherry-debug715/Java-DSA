package BFS.ModernLudo1;
// Lintcode 1565 
// Time: O(M + N)
// Space: O(length)
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class OneQueue {
    public int modernLudo(int length, int[][] connections) {
        if (length == 1) {
            return 1;
        }
        Map<Integer, Integer> connectMap = new HashMap<>();
        Map<Integer, Integer> distance = new HashMap<>();
        for (int[] pos : connections) {
            connectMap.put(pos[0], pos[1]);
        }

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        distance.put(1, 0);
        while (!queue.isEmpty()) {
            int curLen = queue.poll();
            if (curLen == length) {
                return distance.get(curLen);
            }
            // else 
            for (int i = 1; i < 7; i++) {
                int newLen = curLen + i;
                if (distance.containsKey(newLen)) {
                    continue;
                }
                // flying
                int flyingLen = newLen;
                while (connectMap.containsKey(flyingLen)) {
                    flyingLen = connectMap.get(flyingLen);
                    queue.offer(flyingLen);
                    distance.put(flyingLen, distance.get(curLen) + 1);
                }
                queue.offer(newLen);
                distance.put(newLen, distance.get(curLen) + 1);
            }
        }
        return -1;
    }
}
