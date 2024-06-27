package UnionFind;

import java.util.Map;

// Lintcode 178
public class GraphValidTree {
    class UnionFind {
        // initialization 
        Map<Integer, Integer> father = new HashMap<>(); 
        public UnionFind(int n) {
            // each node should be connected with itself initially 
            for (int i = 0; i < n; i++) {
                father.put(i, i);
            }
        }

        int compressedFind(int x) {
            int parent = father.get(x);
            while (parent != father.get(parent)) {
                parent = father.get(parent);
            }

            // compress, this will shrink overall look up time to approximately O(1)
            int temp = -1;
            int fa = father.get(x);
            while (fa != father.get(fa)) {
                temp = father.get(fa);
                father.put(fa, parent);
                fa = temp;
            }
            return parent;
        }

        void union(int x, int y) {
            int xParent = compressedFind(x);
            int yParent = compressedFind(y);
            if (xParent != yParent) {
                father.put(xParent, yParent);
            }
        }
    }


    public boolean validTree(int n, int[][] edges) {
        int edgeNum = edges.length;
        if (n - 1 != edgeNum) {
            return false;
        }

        UnionFind uf = new UnionFind(n);

        for (int[] nodes : edges) {
            int parent1 = uf.compressedFind(nodes[0]);
            int parent2 = uf.compressedFind(nodes[1]);
            if (parent1 == parent2) {
                return false;
            }
            uf.union(nodes[0], nodes[1]);
        }
        return true;
    }
}
