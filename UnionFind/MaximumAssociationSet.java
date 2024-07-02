package UnionFind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// lintcode 805
public class MaximumAssociationSet {
    // Time: O(N)
    // Space: O(N)
   class UnionFind {
        Map<String, String> parent;
        Map<String, Integer> size;
        public UnionFind() {
            parent = new HashMap<>();
            size = new HashMap<>();
        }

        private String find(String x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                size.put(x, 1);
            }

            String root = parent.get(x);
            while (!root.equals(parent.get(root))) {
                root = parent.get(root);
            } 

            String fa = parent.get(x);
            String temp = "";
            while (!fa.equals(parent.get(fa))) {
                temp = parent.get(fa);
                parent.put(fa, root);
                fa = temp;
            }
            return root;
        }

        private boolean isConnected(String x, String y) {
            return find(x).equals(find(y));
        }

        private void union(String x, String y) {
            String root1 = find(x);
            String root2 = find(y);
            if (root1.equals(root2)) {
                return;
            }
            parent.put(root1, root2);
            size.put(root2, size.get(root1) + size.get(root2));
        } 

        private int getSize(String x) {
            String root = find(x);
            return size.get(root);
        } 
    }

    public List<String> maximumAssociationSet(String[] listA, String[] listB) {
        // construct the union graph 
        UnionFind uf = new UnionFind();
        int maxSize = 0, n = listA.length;
        String maxRoot = null; 
        for (int i = 0; i < n; i++) {
            uf.union(listA[i], listB[i]);
            int curSize = uf.getSize(listB[i]);
            if (curSize > maxSize) {
                maxSize = curSize;
                maxRoot = uf.find(listB[i]);
            }
        }

        Set<String> output = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (uf.isConnected(listA[i], maxRoot)) {
                output.add(listA[i]);
                output.add(listB[i]);
            }
        }

        return new ArrayList<>(output);

    }
}
