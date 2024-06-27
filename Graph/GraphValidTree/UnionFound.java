package Graph.GraphValidTree;
// Time: O(N)
// Space: O(N)
// Lintcode 178 
public class UnionFound {
    // initialize father array globally 
    int[] father = null;
    // initialize counter globally
    int counter = 0;
    public boolean validTree(int n, int[][] edges) {
        counter = n;
        int edgeNum = edges.length;
        // if number of edges != total number of nodes - 1, it's not a valid tree 
        if (edgeNum + 1 != n) {
            return false;
        }
        father = new int[n];
        // a node should has connections with itself 
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        // iterate over edges to build connections
        for (int i = 0; i < edgeNum; i++) {
            connect(edges[i][0], edges[i][1]);
        }
        return counter == 1;
    }

    private void connect(int parent, int child) {
        int rootA = find(parent);
        int rootB = find(child);
        // there is no loop 
        if (rootA != rootB) {
            father[rootA] = rootB;
            counter -= 1;
        }
    }

    private int find(int node) {
        if (father[node] == node) {
            return node;
        }
        return father[node] = find(father[node]);
    }
}
