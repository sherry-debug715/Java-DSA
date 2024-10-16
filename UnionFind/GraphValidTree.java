package UnionFind;

import java.util.Map;

// Lintcode 178
public class GraphValidTree {
    class UnionFind {
        int[] children;
        public UnionFind(int n) {
            children = new int[n];
            for (int i = 0; i < n; i++) {
                children[i] = i;
            }
        }

        public int find(int x) {
            if (children[x] != x) {
                children[x] = find(children[x]);
            }
            return children[x];
        }

        public boolean union(int x, int y) {
            int root1 = find(x);
            int root2 = find(y);
            if (root1 != root2) {
                children[root1] = root2;
                return true;
            }
            return false;
        }
    }
    public boolean validTree(int n, int[][] edges) {
        if (n > 1 && edges.length == 0) {
            return false;
        }
        if (edges.length + 1 != n) {
            return false;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] e : edges) {
            if (!uf.union(e[0], e[1])) {
                return false;
            }
        }
        return true;
    }
}
