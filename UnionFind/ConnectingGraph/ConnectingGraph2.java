package UnionFind.ConnectingGraph;
// lintcode 590
public class ConnectingGraph2 {
    class UnionFind {
        int[] fathers;
        int[] setSize;
        
        public UnionFind(int n) {
            fathers = new int[n + 1];
            setSize = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                fathers[i] = i;
                setSize[i] = 1;
            }
        }

        private int searchCompress(int x) {
            int parent = fathers[x];
            while (parent != fathers[parent]) {
                parent = fathers[parent];
            }

            int fa = fathers[x];
            int temp = -1;
            while (fa != fathers[fa]) {
                temp = fathers[fa];
                fathers[fa] = parent;
                fa = temp;
            }
            return parent;
        }

        private void union(int x, int y) {
            int p1 = searchCompress(x);
            int p2 = searchCompress(y);
            if (p1 == p2) {
                return;
            }
            fathers[p1] = p2;
            setSize[p2] += setSize[p1];
        }

        private int getSize(int x) {
            int parent = searchCompress(x);
            return setSize[parent];
        }

    }

    /*
    * @param n: An integer
    */
    UnionFind uf;
    public ConnectingGraph2(int n) {
        uf = new UnionFind(n);
    }
    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        int p1 = uf.searchCompress(a);
        int p2 = uf.searchCompress(b);
        if (p1 == p2) {
            return;
        }
        uf.union(a, b);
    }

    /*
     * @param a: An integer
     * @return: An integer
     */
    public int query(int a) {
        return uf.getSize(a);
    }
}
