package UnionFind;

import java.util.Arrays;

// Lintcode 1396
// Time: O(rowSize * colSize)
// Space: O(1), for both arrays initialized in fixed length.
public class SetUnion {
    class UnionFind {
        int[] parents;
        int[] visited; 
        public UnionFind(int n) { // O(N)
            parents = new int[1001];
            visited = new int[100001]; 
            Arrays.fill(visited, -1);
            for (int i = 0; i < 1001; i++) {
                parents[i] = i;
            }
        }

        private int find(int x) { // O(1)
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        private void merge(int x, int y) { // O(1)
            int root1 = find(x);
            int root2 = find(y);
            if (root1 != root2) {
                parents[root1] = root2;
            }
        }
    }

    public int setUnion(int[][] sets) {
        // step 1: initialize unionFind 
        UnionFind uf = new UnionFind(sets.length);

        // Step 2: build connections 
        // O(rowSize * colSize)
        for (int r = 0; r < sets.length; r++) { // use row number as id 
            for (int c = 0; c < sets[r].length; c++) {
                int n = sets[r][c];
                if (uf.visited[n] != -1) {
                    uf.merge(uf.visited[n], r);
                    uf.visited[n] = uf.find(r);
                } else {
                    uf.visited[n] = uf.find(r);
                }
            }
        }
        // Step 3: add up all the not connected rows 
        // O(N)
        int output = 0;
        for (int i = 0; i < sets.length; i++) {
            if (uf.parents[i] == i) {
                output += 1;
            }
        }
        return output;
    }
}
