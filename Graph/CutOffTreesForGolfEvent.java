package Graph;
// Lintcode problem 1092: https://www.lintcode.com/problem/1092/
// Time: O(M*N)
// Space: O(M*N)
public class CutOffTreesForGolfEvent {
    int[][] POS = {
        {-1, 0},
        {1, 0},
        {0, 1},
        {0, -1}
    };
    
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.get(0) == null || forest.get(0).size() == 0) {
            return -1;
        }

        List<int[]> trees = new ArrayList<>();
        // populate trees with v, r, c 
        for (int r = 0; r < forest.size(); r++) {
            for (int c = 0; c < forest.get(0).size(); c++) {
                int num = forest.get(r).get(c);
                if (num != 0 && num != 1) {
                    trees.add(new int[]{num, r, c});
                }
            }
        }
        // sort tress by value 
        Collections.sort(trees, (a, b) -> Integer.compare(a[0], b[0])); 
        int totalDistance = 0;
        int curR = 0, curC = 0;
        for (int[] tree : trees) {
            int targetR = tree[1], targetC = tree[2];
            int dis = bfs(forest, curR, curC, targetR, targetC);
            if (dis == -1) {
                return -1;
            }
            totalDistance += dis;
            curR = targetR;
            curC = targetC;
        }
        return totalDistance;
    }

    private int bfs(List<List<Integer>> forest, int curR, int curC, int targetR, int targetC) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{curR, curC});
        int distance = 0;
        int rowSize = forest.size(), colSize = forest.get(0).size();
        boolean[][] visited = new boolean[rowSize][colSize];
        visited[curR][curC] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curPos = queue.poll();
                if (curPos[0] == targetR && curPos[1] == targetC) {
                    return distance;
                }
                for (int[] pos : POS) {
                    int newR = pos[0] + curPos[0];
                    int newC = pos[1] + curPos[1];
                    if (newR < 0 || newR >= rowSize || newC < 0 || newC >= colSize) {
                        continue;
                    }
                    if (forest.get(newR).get(newC) == 0 || visited[newR][newC]) {
                        continue;
                    }
                    queue.offer(new int[]{newR, newC});
                    visited[newR][newC] = true;
                }
            }
            distance += 1;
        }
        return -1;
    }
}
