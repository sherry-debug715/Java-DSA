package UnionFind.ConnectingGraph;
// Lintcode 591
public class ConnectingGraph3 {
    int[] fathers = null;
    int connected = 0;
    public ConnectingGraph3(int n) {
        // initialize your data structure here.
        fathers = new int[n + 1];
        connected = n;
        for (int i = 1; i <= n; i++) {
            fathers[i] = i;
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
    
    public void connect(int a, int b) {
        int p1 = searchCompress(a);
        int p2 = searchCompress(b);
        if (p1 != p2) {
            fathers[p1] = p2;
            connected -= 1;
        }
    }

    /**
     * @return: An integer
     */
    public int query() {
        // write your code here
        return connected;
    }
}
