public class ShortestPathFastAl {
    /**
     * @param length: the length of board
     * @param connections: the connections of the positions
     * @return: the minimum steps to reach the end
     */
    public int modernLudo(int length, int[][] connections) {
        if (length == 1) {
            return 1;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        formGraph(length, connections, graph);
        Map<Integer, Integer> distance = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            distance.put(i, Integer.MAX_VALUE);
        }
        Deque<Integer> queue = new ArrayDeque<>();
        distance.put(1, 0);
        queue.add(1);

        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            // update flying distance
            for (int neighbor : graph.get(curNode)) {
                if (distance.get(curNode)< distance.get(neighbor)) {
                    distance.put(neighbor, distance.get(curNode));
                    queue.offer(neighbor);
                }
            }

            for (int newNode = curNode + 1; newNode <= Math.min(curNode + 6, length); newNode++) {
                if (distance.get(curNode) + 1 < distance.get(newNode)) {
                    distance.put(newNode, distance.get(curNode) + 1);
                    queue.offer(newNode);
                }
            } 
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