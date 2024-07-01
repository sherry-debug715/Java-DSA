package UnionFind;

import java.util.Map;

// lintcode 444
public class GraphValidTree2 {
    class UnionFind {
        Map<Integer, Integer> fathers;
        boolean isTree;
        int edgeNum;
        int nodeNum;
        public UnionFind() {
            fathers = new HashMap<>();
            isTree = true;
            edgeNum = 0;
            nodeNum = 0;
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

        private void union(int a, int b) {
            if (!fathers.containsKey(a)) {
                fathers.put(a, a);
                nodeNum += 1;
            }
            if (!fathers.containsKey(b)) {
                fathers.put(b, b);
                nodeNum += 1;
            }
            int parentA = searchCompress(a);
            int parentB = searchCompress(b);
            if (parentA == parentB) {
                isTree = false;
            } else {
                fathers.put(parentA, parentB);
                isTree = true;
                edgeNum += 1;
            }
        }

    }
    

    UnionFind uf = new UnionFind();
    public void addEdge(int a, int b) {
        uf.union(a, b);
    }

    /**
     * @return: check whether these edges make up a valid tree
     */
    public boolean isValidTree() {
        // write your code here
        return uf.isTree && uf.edgeNum + 1 == uf.nodeNum;
    }
}
