package BFS;

public class FriendCircles {
    public int findCircleNum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }

        int n = m.length;
        boolean[] visited = new boolean[n];
        int output = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                visited[i] = true;
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && m[cur][j] == 1) {
                            queue.offer(j);
                            visited[j] = true;
                        } 
                    }
                }
                output += 1;

            }
        }
        return output;
    }
}
