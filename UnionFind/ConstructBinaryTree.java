package UnionFind;

import java.util.List;
import java.util.Map;

// lintcode 1813 
// Time: O(n)
// Space: O(n)
public class ConstructBinaryTree {
    class UnionFind {
        Map<String, String> parent;
        int root;
        public UnionFind() {
            parent = new HashMap<>();
            root = 0;
        }

        private String find(String x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                root += 1;
            }
            if (!parent.get(x).equals(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        private void merge(String x, String y) {
            String root1 = find(x);
            String root2 = find(y);
            if (root1.equals(root2)) {
                return;
            }
            parent.put(root2, root1);
            root -= 1;
        }
    }
    public String constructBinaryTree(List<List<String>> pair) {
        int n = pair.size();
        UnionFind uf = new UnionFind();
        Map<String, List<String>> children = new HashMap<>(); // key: parent, val: list of children nodes
        Map<String, String> edgeMap = new HashMap<>(); // key: parent, val: child 
        Map<String, String> parentMap = new HashMap<>(); // key: child, val: parent
        // more children 
        for (List<String> p : pair) {
            String parent = p.get(0);
            String child = p.get(1);
            uf.find(parent);
            uf.find(child);
            uf.merge(parent, child);
            children.putIfAbsent(parent, new ArrayList<String>());
            children.get(parent).add(child);
            if (children.get(parent).size() > 2) {
                return "more children";
            }
        }
        // repeat edge 
        for (List<String> p : pair) {
            String parent = p.get(0);
            String child = p.get(1);
            if (edgeMap.containsKey(parent) && edgeMap.get(parent).equals(child)) {
                return "repeat edge";
            }
            edgeMap.put(parent, child);
        }
        // have cycle: when child parent is itself 
        for (List<String> p : pair) {
            String child = p.get(1);
            String childParent = uf.find(child);
            if (childParent.equals(child)) {
                return "have cycle";
            }
        }
        // more parent 
        for (List<String> p : pair) {
            String parent = p.get(0);
            String child = p.get(1);
            if (parentMap.containsKey(child) && !parentMap.get(child).equals(parent)) {
                return "more parent";
            }
            parentMap.put(child, parent);
        }
        // input error 
        for (List<String> p : pair) {
            String parent = p.get(0);
            String child = p.get(1);
            if (parent.length() > 1 || child.length() > 1) {
                return "input error";
            }
        }
        // tree can have one root 
        if (uf.root > 1) {
            return "input error";
        }
        return "successful";
    }
}
