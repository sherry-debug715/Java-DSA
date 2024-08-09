package MinSpanningTree;

import java.sql.Connection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// Lintcode 629 
// Time: 2601ms
// Space: 25.46mb

class Connection {
   public String city1, city2;
   public int cost;
   public Connection(String city1, String city2, int cost) {
       this.city1 = city1;
       this.city2 = city2;
       this.cost = cost;
   }
 }
 
public class MinimumSpanningTree {
        class UnionFind {
        Map<String, String> parents;
        public UnionFind() {
            parents = new HashMap<>();
        }

        public void insert(String city) {
            if (parents.containsKey(city)) {
                return;
            }
            parents.put(city, city);
        } 

        public String find(String x) {
            if (!x.equals(parents.get(x))) {
                parents.put(x, find(parents.get(x)));
            }
            return parents.get(x);
        }

        public void union(String x, String y) {
            String root1 = find(x);
            String root2 = find(y);
            if (!root1.equals(root2)) {
                parents.put(root1, root2);
            }
        }

        public boolean sameUnion(String x, String y) {
            String root1 = find(x);
            String root2 = find(y);
            return root1.equals(root2);
        }
    }
    // Time: O(m) + O(mlogm) + O(m),  n == number of citys and m == edges 
    // Space: O(n) 
    public List<Connection> lowestCost(List<Connection> connections) {
        // Step1: build a unionfind 
        UnionFind uf = new UnionFind();
        for (Connection c : connections) { // O(m) 
            uf.insert(c.city1);
            uf.insert(c.city2);
        }
        // Step2: sort connections by 
        // 1) cost;
        // 2) if same cost, sort by city1 
        // 3) if not above, sort by city2 
        // O(mlogn)
        Collections.sort(connections, new Comparator<>() {
            public int compare(Connection a, Connection b) {
                if (a.cost != b.cost) {
                    return a.cost - b.cost;
                }
                if (!a.city1.equals(b.city1)) {
                    return a.city1.compareTo(b.city1);
                }
                return a.city2.compareTo(b.city2);
            }
        });
        // Step 3: form min spanning tree 
        int nodes = uf.parents.size();
        int curEdges = 0;
        List<Connection> mst = new LinkedList<>();
        // O(m)
        for (Connection c : connections) {
            if (curEdges + 1 == nodes) {
                return mst;
            }
            // if there is a cycle, continue 
            if (uf.sameUnion(c.city1, c.city2)) {
                continue;
            }
            uf.union(c.city1, c.city2);
            mst.add(c);
            curEdges += 1;
        }
        return new LinkedList<>();
    }
}
