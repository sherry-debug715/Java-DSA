import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public class Solution {
        /**
         * @param n: An integer
         * @param edges: a list of undirected edges
         * @return: true if it's a valid tree, or false
         */
        private int[] father = null;
        private int count = 0;
        private int find(int k) {
            if (father[k] == k) {
                return k;
            }
            return father[k]=find(father[k]);
        }
        private void connect(int a, int b) {
           int roota = find(a),rootb = find(b);
           if (roota != rootb) {
               father[roota] = rootb;
               count--;
           }
       }
        public boolean validTree(int n, int[][] edges) {
            int m = edges.length;
            if (n != m + 1) {
                return false;
            }
            
            count = n;
            father = new int[n];
            for (int i = 0; i < n; ++i) {
                father[i] = i;
            }
            
            for (int i = 0; i < m; ++i) {
                connect(edges[i][0], edges[i][1]);
            }
            return count == 1;
        }
    }
}


