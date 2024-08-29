package UnionFind;
// leetcode 947
// Time: O(N)
// Space: O(1)
public class MostStonesRemovedWithSameRowOrCol {
    class UnionFind {
        int[] parents;
        int unionCount;
        public UnionFind() {
            parents = new int[20004]; // 1 - 10001 is reserved for row, 10002 - 20004 is reserved for col
            unionCount = 0;
        }

        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public void union(int row, int col) {
            if (parents[row] == 0) {
                parents[row] = row;
                unionCount += 1;
            }
            if (parents[col] == 0) {
                parents[col] = col;
                unionCount += 1;
            }
            int root1 = find(row);
            int root2 = find(col);
            if (root1 != root2) {
                parents[root1] = root2;
                unionCount -= 1;
            }
        }
    }

    public int removeStones(int[][] stones) {
        // connect connected rows and cols through unionFind 
        UnionFind uf = new UnionFind();
        for (int[] pos : stones) { // O(N)
            int row = pos[0] + 1, col = pos[1] + 10002;
            uf.union(row, col);
        }
        return stones.length - uf.unionCount;
    }
}
