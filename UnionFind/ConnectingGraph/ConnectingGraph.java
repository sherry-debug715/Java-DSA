package UnionFind.ConnectingGraph;

import java.util.Map;

// lintcode 589
// Time: O(n) initially, for every search, the time will reduce down to roughly O(1)
// Space: O(n) where n is the number of points stored in fathers
public class ConnectingGraph {
    class UnionFind {
        Map<Integer, Integer> fathers = new HashMap<>();
        public UnionFind(int n) {
            for (int i = 1; i <= n; i++) {
                fathers.put(i, i);
            }
        }

        private int searchCompress(int x) {
            int parent = fathers.get(x);
            while (parent != fathers.get(parent)) {
                parent = fathers.get(parent);
            }

            int fa = fathers.get(x);
            int temp = -1;
            while (fa != fathers.get(fa)) {
                temp = fathers.get(fa);
                fathers.put(fa, parent);
                fa = temp;
            }
            return parent;
        }

        private void union(int x, int y) {
            int p1 = searchCompress(x);
            int p2 = searchCompress(y);
            if (p1 != p2) {
                fathers.put(p1, p2);
            }
        }

        private boolean isConnected(int x, int y) {
            int p1 = searchCompress(x);
            int p2 = searchCompress(y);
            return p1 == p2;
        }
    }
    /*
    * @param n: An integer
    */
    
    UnionFind uf;
    public ConnectingGraph(int n) {
        uf = new UnionFind(n);
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        uf.union(a, b);
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {
        // write your code here
        return uf.isConnected(a, b);
    }
}
